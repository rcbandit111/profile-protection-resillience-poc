package com.profile.protection.admin.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    // Test for circuitBreaker

    @TimeLimiter(name = "controller")
    @CircuitBreaker(name = "myService", fallbackMethod = "circuitBreakerFallback")
    @Retry(name = "myService", fallbackMethod = "retryFallback")
    public void process() {
        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

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
