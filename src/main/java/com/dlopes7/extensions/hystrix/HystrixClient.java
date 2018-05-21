package com.dlopes7.extensions.hystrix;

import com.appdynamics.extensions.conf.MonitorConfiguration;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.MessageEvent;
import com.singularity.ee.agent.systemagent.api.MetricWriter;


/**
 * Created by dlopes on 5/20/18.
 */
public class HystrixClient implements EventHandler {

    EventSource eventSource;

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

        JsonObject jsonObject = new JsonParser().parse(messageEvent.getData()).getAsJsonObject();
        switch (jsonObject.get("type").getAsString()){

            case "HystrixCommand":
                Gson gson = new Gson();
                HystrixCommandModel hystrixCommandModel = gson.fromJson(messageEvent.getData(), HystrixCommandModel.class);
                printHystrixCommand(hystrixCommandModel);
                break;

            case "HystrixThreadPool":
                eventSource.close();
                break;
        }



       // System.out.println(event + " - " + messageEvent.getData());

    }


    public void onComment(String comment) throws Exception {
        System.out.println("onComment");
    }


    public void onError(Throwable t) {
        System.out.println("onError: " + t);

    }

    public void printHystrixCommand(HystrixCommandModel hystrixCommandModel){

        StringBuilder baseString = new StringBuilder(configuration.getMetricPrefix());
        baseString.append(HystrixMonitorTask.METRIC_SEPARATOR)
                .append(hystrixCommandModel.getGroup())
                .append(HystrixMonitorTask.METRIC_SEPARATOR)
                .append(hystrixCommandModel.getName())
        .append(HystrixMonitorTask.METRIC_SEPARATOR);



        printMetric(new String(baseString) + "errorCount" , hystrixCommandModel.getErrorCount().toString());
        printMetric(new String(baseString) + "requestCount", hystrixCommandModel.getRequestCount().toString());


    }

    private void printMetric(String metricName, String metricValue){
        System.out.println(metricName + ": " + metricValue);
        configuration.getMetricWriter().printMetric(metricName,
                metricValue,
                MetricWriter.METRIC_AGGREGATION_TYPE_AVERAGE,
                MetricWriter.METRIC_TIME_ROLLUP_TYPE_AVERAGE,
                MetricWriter.METRIC_CLUSTER_ROLLUP_TYPE_COLLECTIVE);

    }


}
