package com.dlopes7.extensions.hystrix;


import com.appdynamics.extensions.conf.MonitorConfiguration;
import com.appdynamics.extensions.util.MetricWriteHelper;
import com.appdynamics.extensions.util.MetricWriteHelperFactory;
import com.singularity.ee.agent.systemagent.api.AManagedMonitor;
import com.singularity.ee.agent.systemagent.api.TaskExecutionContext;
import com.singularity.ee.agent.systemagent.api.TaskOutput;
import com.singularity.ee.agent.systemagent.api.exception.TaskExecutionException;
import org.apache.log4j.Logger;
import java.util.List;
import java.util.Map;

/**
 * Created by dlopes on 5/20/18.
 */
public class HystrixMonitor extends AManagedMonitor {

    private static final Logger logger = Logger.getLogger(HystrixMonitor.class);
    private static final String METRIC_PREFIX = "Custom Metrics|Hystrix|";
    private static final String CONFIG_ARG = "config-file";

    private boolean initialized;
    private MonitorConfiguration configuration;

    public HystrixMonitor() {
        System.out.println(logVersion());
    }

    public TaskOutput execute(Map<String, String> taskArguments, TaskExecutionContext arg1) throws TaskExecutionException {
        logVersion();
        if (!initialized) {
            initialize(taskArguments);
        }
        logger.debug("The raw arguments are " + taskArguments);
        configuration.executeTask();
        logger.info("Hystrix Monitor run completed successfully.");
        return new TaskOutput("Hystrix Monitor run completed successfully.");
    }

    private void initialize(Map<String, String> taskArgs) {
        if(!initialized) {

            logger.debug("Initializing Hystrix  Monitor configuration");

            final String configFilePath = taskArgs.get(CONFIG_ARG);

            MetricWriteHelper metricWriteHelper = MetricWriteHelperFactory.create(this);
            MonitorConfiguration conf = new MonitorConfiguration(METRIC_PREFIX, new TaskRunnable(), metricWriteHelper);

            conf.setConfigYml(configFilePath);
            conf.checkIfInitialized(MonitorConfiguration.ConfItem.CONFIG_YML, MonitorConfiguration.ConfItem.EXECUTOR_SERVICE, MonitorConfiguration.ConfItem.HTTP_CLIENT, MonitorConfiguration.ConfItem.METRIC_PREFIX, MonitorConfiguration.ConfItem.METRIC_WRITE_HELPER);
            this.configuration = conf;
            initialized = true;

        }
    }

    private class TaskRunnable implements Runnable {
        public void run() {
            Map<String, ?> config = configuration.getConfigYml();
            if(config != null) {
                List<Map> endpoints = (List) config.get("servers");

                if(endpoints != null && !endpoints.isEmpty()) {
                    for (Map endpoint : endpoints) {
                        HystrixMonitorTask task;
                        task = new HystrixMonitorTask(configuration, endpoint);
                        configuration.getExecutorService().execute(task);

                    }
                } else {
                    logger.error("There are no servers configured");
                }
            } else {
                logger.error("The config.yml is not loaded due to errors.The task will not run");
            }

        }
    }



    private String logVersion() {
        String msg = "Using Monitor Version [" + getImplementationVersion() + "]";
        logger.info(msg);
        return msg;
    }

    public static String getImplementationVersion() {
        return AManagedMonitor.class.getPackage().getImplementationTitle();
    }



}
