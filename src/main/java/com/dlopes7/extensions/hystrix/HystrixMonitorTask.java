package com.dlopes7.extensions.hystrix;

import com.appdynamics.extensions.conf.MonitorConfiguration;
import com.appdynamics.extensions.http.UrlBuilder;
import com.google.common.base.Strings;
import com.launchdarkly.eventsource.EventSource;
import org.apache.log4j.Logger;

import java.net.URI;
import java.util.Map;

/**
 * Created by dlopes on 5/20/18.
 */
public class HystrixMonitorTask implements Runnable {

    private static final Logger logger = Logger.getLogger(HystrixMonitorTask.class);
    public static final String METRIC_SEPARATOR = "|";

    private MonitorConfiguration configuration;
    private Map endpoint;

    public HystrixMonitorTask(MonitorConfiguration configuration, Map endpoint) {
        this.configuration = configuration;
        this.endpoint = endpoint;

    }

    public void run() {
        long startTime = System.currentTimeMillis();


        String displayName = (String) endpoint.get("displayName");
        try {
            if (!Strings.isNullOrEmpty(displayName)) {
                logger.debug("Fetching metrics for the server " + displayName);
                fetchMetrics(displayName);
            }
        } catch (Exception e) {
            String msg = "Exception while running the Hystrix Client task in the server " + displayName;
            logger.error(msg, e);
            configuration.getMetricWriter().registerError(msg, e);
        } finally {
            long endTime = System.currentTimeMillis() - startTime;
            logger.debug("Hystrix Monitor thread for server " + displayName + " ended. Time taken is " + endTime);
        }

    }

    private void fetchMetrics(String displayName){

        try {
            HystrixClient eventHandler = new HystrixClient();

            UrlBuilder urlBuilder = UrlBuilder.fromYmlServerConfig(endpoint);
            String url = urlBuilder.build();
            EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));

            EventSource eventSource = builder.build();
            eventSource.setReconnectionTimeMs(3000);

            eventHandler.setEventSource(eventSource);
            eventHandler.setConfiguration(configuration);

            eventSource.start();



        }catch(Exception ex){
            ex.printStackTrace();
        }

    }



}
