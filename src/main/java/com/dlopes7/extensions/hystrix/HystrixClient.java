package com.dlopes7.extensions.hystrix;

import com.appdynamics.extensions.conf.MonitorConfiguration;
import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.MessageEvent;
import com.singularity.ee.agent.systemagent.api.MetricWriter;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by dlopes on 5/20/18.
 */
public class HystrixClient implements EventHandler {

    public HystrixClient(){
        this.lastEventType = "";
    }

    EventSource eventSource;
    String lastEventType;

    public MonitorConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(MonitorConfiguration configuration) {
        this.configuration = configuration;
    }

    MonitorConfiguration configuration;

    public void setEventSource(EventSource eventSource){
        this.eventSource = eventSource;
    }

    public EventSource getEventSource(){
        return this.getEventSource();
    }


    public void onOpen() throws Exception {
        System.out.println("onOpen");
    }


    public void onClosed() throws Exception {
        System.out.println("onClosed");
    }


    public void onMessage(String event, MessageEvent messageEvent) throws Exception {

        JSONObject jsonObject = new JSONObject(messageEvent.getData());

        if (jsonObject.getString("type").equals("HystrixCommand") && this.lastEventType.equals("HystrixThreadPool")){
            eventSource.close();
        }
        else {


            StringBuilder baseString = new StringBuilder(configuration.getMetricPrefix());
            baseString.append(HystrixMonitorTask.METRIC_SEPARATOR)
                    .append(jsonObject.getString("type"))
                    .append(HystrixMonitorTask.METRIC_SEPARATOR);

            if (jsonObject.has("group")) {
                baseString.append(jsonObject.getString("group"))
                        .append(HystrixMonitorTask.METRIC_SEPARATOR);
            }

            baseString.append(jsonObject.getString("name"))
                    .append(HystrixMonitorTask.METRIC_SEPARATOR);

            populateMetrics(jsonObject, baseString.toString());

        }
        this.lastEventType = jsonObject.getString("type");
    }


    public void onComment(String comment) throws Exception {
        System.out.println("onComment");
    }


    public void onError(Throwable t) {
        System.out.println("onError: " + t);

    }

    public void populateMetrics(JSONObject jsonObject, String baseString){

        Map<String, String> metricsMap = new HashMap<String, String>();

        Iterator<String> keys = jsonObject.keys();

        String metricName;
        String metricValue = null;

        while (keys.hasNext()){

            String key = (String)keys.next();
            metricName = baseString + key;
            Object value = jsonObject.get(key);

            if ( value instanceof JSONObject ) {
                populateMetrics((JSONObject) value, metricName + "_");

            }else if (value instanceof String){
                // Ignore, we can't reliable convert a string to a metric

            }else if (value instanceof Boolean) {

                metricValue = (Boolean)value ? "1" : "0";

            } else if (value == null || value == "null"){
                // Ignore
            }else {

                metricValue = value.toString();
            }

            if (metricValue != null) {
                printMetric(metricName, metricValue);
            }
            metricValue = null;

        }

    }

    private void printMetric(String metricName, String metricValue){

        // if(metricName.contains("requestCount")) {
            System.out.println(metricName + ": " + metricValue);
        //}
        configuration.getMetricWriter().printMetric(metricName,
                metricValue,
                MetricWriter.METRIC_AGGREGATION_TYPE_AVERAGE,
                MetricWriter.METRIC_TIME_ROLLUP_TYPE_AVERAGE,
                MetricWriter.METRIC_CLUSTER_ROLLUP_TYPE_COLLECTIVE);

    }
}
