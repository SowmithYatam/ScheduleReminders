package com.gajoi.scheduler.cache;

import com.gajoi.scheduler.model.ReminderTO;
import jakarta.enterprise.context.RequestScoped;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.event.EventFiring;
import org.ehcache.event.EventOrdering;
import org.ehcache.event.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.EnumSet;

@RequestScoped
public class CacheManagerImpl {
    Logger logger = LoggerFactory.getLogger(CacheManagerImpl.class.toString());
    CustomExpiryPolicy<String,EhcacheValue<?>> expiryPolicy = new CustomExpiryPolicy<String,EhcacheValue<?>>();

    /*CacheEventListenerConfigurationBuilder cacheEventListenerConfiguration = CacheEventListenerConfigurationBuilder
                .newEventListenerConfiguration(listener, EventType.EXPIRED, EventType.EVICTED)
            .unordered().asynchronous();*/

    @SuppressWarnings("unchecked")
    Class<EhcacheValue<?>> myEhcacheValue = (Class<EhcacheValue<?>>)(Class<?>)EhcacheValue.class;


    final CacheManager manager = CacheManagerBuilder.newCacheManagerBuilder()
            .withCache("ReminderCache",
                    CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, myEhcacheValue, ResourcePoolsBuilder.heap(10000))
                            .withExpiry(expiryPolicy)
            ).build(true);

    static Cache<String, EhcacheValue<?>> cache = null;

    public CacheManagerImpl(){
        logger.info("bro...logger is working");

        if(cache==null){
            cache = manager.getCache("ReminderCache", String.class, myEhcacheValue);
            CacheListener listener = new CacheListener();
            cache.getRuntimeConfiguration().registerCacheEventListener(listener, EventOrdering.UNORDERED, EventFiring.SYNCHRONOUS, EnumSet
                    .of(EventType.EXPIRED, EventType.CREATED, EventType.UPDATED, EventType.REMOVED));
        }
    }


    public static <T> T get(String key) {
        EhcacheValue<T> ehcacheValue = (EhcacheValue<T>)cache.get(key);
        return (T) (ehcacheValue!=null?ehcacheValue.getObject():null);
    }

    public static <T extends Object> T put(String key, T value) {
        if (value != null) {
            ReminderTO to = (ReminderTO) value;
            LocalDateTime sendTime = to.getSendTime();
            LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(sendTime,now);
            long seconds = Math.abs(duration.getSeconds());
            EhcacheValue<T> ehcacheValue = new EhcacheValue<T>(value, Duration.of(seconds, ChronoUnit.SECONDS));
            cache.put(key, ehcacheValue);
            return value;
        } else {
            return null;
        }
    }

    public void remove(String key){
        cache.remove(key);
    }

}

