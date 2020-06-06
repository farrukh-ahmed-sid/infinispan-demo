package org.example;

import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntriesEvicted;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryExpired;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryModified;
import org.infinispan.notifications.cachelistener.event.CacheEntriesEvictedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryExpiredEvent;
import org.infinispan.notifications.cachelistener.event.CacheEntryModifiedEvent;

@Listener
public class AppListener {

    @CacheEntryCreated
    public void entryCreated(CacheEntryCreatedEvent<String, String> event) {
        if (!event.isPre())
            System.out.printf("Added entry: %s\n", event.getKey() +": "+ event.getValue());
    }

    @CacheEntryModified
    public void entryModified(CacheEntryModifiedEvent<String, String> event) {
        if (event.isPre())
            System.out.printf("Modified pre entry: %s\n", event.getKey() +": "+ event.getValue());
        else
            System.out.printf("Modified entry: %s\n", event.getKey() +": "+ event.getValue());
    }

    @CacheEntriesEvicted
    public void entryEvicted(CacheEntriesEvictedEvent<String, String> event){
        System.out.printf("Evicted entries: %s\n", event.getEntries());
    }

    @CacheEntryExpired
    public void entryExpired(CacheEntryExpiredEvent<String, String> event) {
        System.out.printf("Expired entry: %s\n", event.getKey() +": "+ event.getValue());
    }
}
