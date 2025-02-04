package com.profile.protection.admin.service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class KeysServiceRegistry {

    @Bulkhead(name = "controller", type = Bulkhead.Type.THREADPOOL)
    @CircuitBreaker(name = "myService", fallbackMethod = "circuitBreakerFallback")
    @Retry(name = "myService", fallbackMethod = "retryFallback")
    public String process(String forDataType) {
        return "sleeping";
    }

    // Fallback method for CircuitBreaker
    public String circuitBreakerFallback(Throwable t) {
        return "Circuit breaker fallback: " + t.getMessage();
    }

    // Fallback method for Retry
    public String retryFallback(Throwable t) {
        return "Retry fallback: " + t.getMessage();
    }

}
