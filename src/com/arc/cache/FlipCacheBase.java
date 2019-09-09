package com.arc.cache;

import com.arc.exceptions.NotFoundException;
import com.arc.exceptions.NullKeyException;

import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public abstract class FlipCacheBase<K, V> {

    private Map<K, V> cacheStorage;
    private Deque<K> queue;

    public FlipCacheBase(Integer cacheSize) {
        cacheStorage = new ConcurrentHashMap<>(cacheSize);
        queue = new LinkedBlockingDeque<>(cacheSize);
    }

    public abstract V get(K key) throws NotFoundException, NullKeyException;

    public abstract void remove(K key) throws NotFoundException;

    public abstract void put(K key, V value) throws NullKeyException;

    public Map<K, V> getCacheStorage() {
        return cacheStorage;
    }

    public Deque<K> getQueue() {
        return queue;
    }
}
