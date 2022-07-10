package com.gajoi.scheduler.cache;
import java.io.Serializable;
import java.time.Duration;

public class EhcacheValue<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private T object;

    private long timeToLive;

    public EhcacheValue(T theObject, Duration theDuration) {
        object = theObject;
        timeToLive = theDuration.getSeconds();
    }

    public Duration getTimeToLiveDuration() {
        return Duration.ofSeconds(timeToLive);
    }

    public T getObject() {
        return object;
    }

    public void setObject(T theObject) {
        object = theObject;
    }

    public long getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(long theTimeToLive) {
        timeToLive = theTimeToLive;
    }

}
