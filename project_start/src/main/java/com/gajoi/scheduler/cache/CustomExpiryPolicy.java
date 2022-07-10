package com.gajoi.scheduler.cache;

import java.time.Duration;

import java.util.function.Supplier;

import org.ehcache.expiry.ExpiryPolicy;

public class CustomExpiryPolicy<K, V extends EhcacheValue<?>> implements ExpiryPolicy<K, V> {

    public CustomExpiryPolicy() {

    }

    @Override
    public Duration getExpiryForCreation(K theKey, V theValue) {
        return theValue.getTimeToLiveDuration();
    }

    @Override
    public Duration getExpiryForAccess(K theKey, Supplier<? extends V> theValue) {
        return null;
    }

    @Override
    public Duration getExpiryForUpdate(K theKey, Supplier<? extends V> theOldValue, V theNewValue) {
        return theNewValue.getTimeToLiveDuration();
    }
}

