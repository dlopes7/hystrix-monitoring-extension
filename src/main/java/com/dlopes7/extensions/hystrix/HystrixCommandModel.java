package com.dlopes7.extensions.hystrix;


import com.google.gson.Gson;

/**
 * Created by dlopes on 5/21/18.
 */
public class HystrixCommandModel {

    private String type;
    private String name;
    private String group;

    private Long currentTime;
    private Boolean isCircuitBreakerOpen;
    private Long errorPercentage;

    private Long errorCount;
    private Long requestCount;
    private Long rollingCountBadRequests;
    private Long rollingCountCollapsedRequests;
    private Long rollingCountEmit;
    private Long rollingCountExceptionsThrown;
    private Long rollingCountFailure;
    private Long rollingCountFallbackEmit;
    private Long rollingCountFallbackFailure;
    private Long rollingCountFallbackMissing;
    private Long rollingCountFallbackRejection;
    private Long rollingCountFallbackSuccess;
    private Long rollingCountResponsesFromCache;
    private Long rollingCountSemaphoreRejected;
    private Long rollingCountShortCircuited;
    private Long rollingCountSuccess;
    private Long rollingCountThreadPoolRejected;
    private Long rollingCountTimeout;
    private Long currentConcurrentExecutionCount;
    private Long rollingMaxConcurrentExecutionCount;
    private Long latencyExecute_mean;

    public Long getLatencyTotal_mean() {
        return latencyTotal_mean;
    }

    public void setLatencyTotal_mean(Long latencyTotal_mean) {
        this.latencyTotal_mean = latencyTotal_mean;
    }

    private Long latencyTotal_mean;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Long currentTime) {
        this.currentTime = currentTime;
    }

    public Boolean getCircuitBreakerOpen() {
        return isCircuitBreakerOpen;
    }

    public void setCircuitBreakerOpen(Boolean circuitBreakerOpen) {
        isCircuitBreakerOpen = circuitBreakerOpen;
    }

    public Long getErrorPercentage() {
        return errorPercentage;
    }

    public void setErrorPercentage(Long errorPercentage) {
        this.errorPercentage = errorPercentage;
    }

    public Long getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Long errorCount) {
        this.errorCount = errorCount;
    }

    public Long getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Long requestCount) {
        this.requestCount = requestCount;
    }

    public Long getRollingCountBadRequests() {
        return rollingCountBadRequests;
    }

    public void setRollingCountBadRequests(Long rollingCountBadRequests) {
        this.rollingCountBadRequests = rollingCountBadRequests;
    }

    public Long getRollingCountCollapsedRequests() {
        return rollingCountCollapsedRequests;
    }

    public void setRollingCountCollapsedRequests(Long rollingCountCollapsedRequests) {
        this.rollingCountCollapsedRequests = rollingCountCollapsedRequests;
    }

    public Long getRollingCountEmit() {
        return rollingCountEmit;
    }

    public void setRollingCountEmit(Long rollingCountEmit) {
        this.rollingCountEmit = rollingCountEmit;
    }

    public Long getRollingCountExceptionsThrown() {
        return rollingCountExceptionsThrown;
    }

    public void setRollingCountExceptionsThrown(Long rollingCountExceptionsThrown) {
        this.rollingCountExceptionsThrown = rollingCountExceptionsThrown;
    }

    public Long getRollingCountFailure() {
        return rollingCountFailure;
    }

    public void setRollingCountFailure(Long rollingCountFailure) {
        this.rollingCountFailure = rollingCountFailure;
    }

    public Long getRollingCountFallbackEmit() {
        return rollingCountFallbackEmit;
    }

    public void setRollingCountFallbackEmit(Long rollingCountFallbackEmit) {
        this.rollingCountFallbackEmit = rollingCountFallbackEmit;
    }

    public Long getRollingCountFallbackFailure() {
        return rollingCountFallbackFailure;
    }

    public void setRollingCountFallbackFailure(Long rollingCountFallbackFailure) {
        this.rollingCountFallbackFailure = rollingCountFallbackFailure;
    }

    public Long getRollingCountFallbackMissing() {
        return rollingCountFallbackMissing;
    }

    public void setRollingCountFallbackMissing(Long rollingCountFallbackMissing) {
        this.rollingCountFallbackMissing = rollingCountFallbackMissing;
    }

    public Long getRollingCountFallbackRejection() {
        return rollingCountFallbackRejection;
    }

    public void setRollingCountFallbackRejection(Long rollingCountFallbackRejection) {
        this.rollingCountFallbackRejection = rollingCountFallbackRejection;
    }

    public Long getRollingCountFallbackSuccess() {
        return rollingCountFallbackSuccess;
    }

    public void setRollingCountFallbackSuccess(Long rollingCountFallbackSuccess) {
        this.rollingCountFallbackSuccess = rollingCountFallbackSuccess;
    }

    public Long getRollingCountResponsesFromCache() {
        return rollingCountResponsesFromCache;
    }

    public void setRollingCountResponsesFromCache(Long rollingCountResponsesFromCache) {
        this.rollingCountResponsesFromCache = rollingCountResponsesFromCache;
    }

    public Long getRollingCountSemaphoreRejected() {
        return rollingCountSemaphoreRejected;
    }

    public void setRollingCountSemaphoreRejected(Long rollingCountSemaphoreRejected) {
        this.rollingCountSemaphoreRejected = rollingCountSemaphoreRejected;
    }

    public Long getRollingCountShortCircuited() {
        return rollingCountShortCircuited;
    }

    public void setRollingCountShortCircuited(Long rollingCountShortCircuited) {
        this.rollingCountShortCircuited = rollingCountShortCircuited;
    }

    public Long getRollingCountSuccess() {
        return rollingCountSuccess;
    }

    public void setRollingCountSuccess(Long rollingCountSuccess) {
        this.rollingCountSuccess = rollingCountSuccess;
    }

    public Long getRollingCountThreadPoolRejected() {
        return rollingCountThreadPoolRejected;
    }

    public void setRollingCountThreadPoolRejected(Long rollingCountThreadPoolRejected) {
        this.rollingCountThreadPoolRejected = rollingCountThreadPoolRejected;
    }

    public Long getRollingCountTimeout() {
        return rollingCountTimeout;
    }

    public void setRollingCountTimeout(Long rollingCountTimeout) {
        this.rollingCountTimeout = rollingCountTimeout;
    }

    public Long getCurrentConcurrentExecutionCount() {
        return currentConcurrentExecutionCount;
    }

    public void setCurrentConcurrentExecutionCount(Long currentConcurrentExecutionCount) {
        this.currentConcurrentExecutionCount = currentConcurrentExecutionCount;
    }

    public Long getRollingMaxConcurrentExecutionCount() {
        return rollingMaxConcurrentExecutionCount;
    }

    public void setRollingMaxConcurrentExecutionCount(Long rollingMaxConcurrentExecutionCount) {
        this.rollingMaxConcurrentExecutionCount = rollingMaxConcurrentExecutionCount;
    }

    public Long getLatencyExecute_mean() {
        return latencyExecute_mean;
    }

    public void setLatencyExecute_mean(Long latencyExecute_mean) {
        this.latencyExecute_mean = latencyExecute_mean;
    }



}
