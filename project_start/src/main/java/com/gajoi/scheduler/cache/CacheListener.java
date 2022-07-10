package com.gajoi.scheduler.cache;

import com.gajoi.scheduler.message.SenderConfig;
import com.gajoi.scheduler.model.ReminderTO;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.ehcache.event.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheListener implements CacheEventListener<Object, Object> {

    static SenderConfig config = new SenderConfig();
    Logger logger = LoggerFactory.getLogger(CacheListener.class.toString());

    @Override
    public void onEvent(CacheEvent<? extends Object, ? extends Object>  cacheEvent) {
        logger.info(cacheEvent.getType().toString());
        if(cacheEvent.getType().equals(EventType.EXPIRED)||cacheEvent.getType().equals(EventType.CREATED)){
            sendMessage(cacheEvent);
        }
    }
    public static <T> T sendMessage(CacheEvent cacheEvent) {
        EhcacheValue<T> ehcacheValue = (EhcacheValue<T>)cacheEvent.getNewValue();
        ReminderTO to = (ReminderTO) (ehcacheValue!=null?ehcacheValue.getObject():null);
        config.sendMessage(to.getTitle(),to.getMessage(),to.getPhoneNumber());
        return null;
    }
}
