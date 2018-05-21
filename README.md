# AppDynamics Hystrix Monitoring Extension

## Use Case

Hystrix is a powerful framework developed by Netflix. It is widely used for cloud native applications and microservices.
 It servers as a shield to protect a microservice application from components failing.
 
 The metrics are obtained by querying the [hystrix-metrics-event-stream](https://github.com/Netflix/Hystrix/tree/master/hystrix-contrib/hystrix-metrics-event-stream).
 
## Prerequisites

AppDynamics Machine Agent

## Installation

1. Clone the repo and run `mvn clean install` from the hystrix-monitoring-extension folder
    * Alternative you can download the code from `Releases`
2. Unzip `target/HystrixMonitorExtension-X.X.X.zip` into the machineagent/monitors/ folder
3. Edit the `config.yml` file to point to your hystrix metric streams
4. Restart the Machine Agent

## Metrics

Every `numeric` and `boolean` metric reported for every `HystrixCommand` and `HystrixThreadPool`.



 
 