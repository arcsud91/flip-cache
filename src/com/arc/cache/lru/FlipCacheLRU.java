package com.arc.cache.lru;

import com.arc.cache.FlipCacheBase;
import com.arc.exceptions.NotFoundException;
import com.arc.exceptions.NullKeyException;
import java.util.Iterator;

public class FlipCacheLRU<K, V> extends FlipCacheBase<K, V> {

    private Integer size;

    public FlipCacheLRU(Integer size) {
        super(size);
        this.size = size;
    }

    @Override
    public V get(K key) throws NotFoundException, NullKeyException{
        if (key != null ) {
            if (getCacheStorage().containsKey(key)) {
                Iterator<K> queueItr = getQueue().iterator();
                V val = getCacheStorage().get(key);
                while(queueItr.hasNext()) {
                    K qKey = queueItr.next();
                    if (qKey.equals(key)) {
                        getQueue().remove(key);
                        getQueue().addFirst(key);
                        break;
                    }
                }
                return val;
            } else {
                StringBuilder sb = new StringBuilder("The Key ");
                sb.append(key);
                sb.append(" is not present in the cache");
                throw new NotFoundException(sb.toString());
            }
        } else {
            throw new NullKeyException("Null key is not allowed in the Cache");
        }

    }

    @Override
    public void remove(K key) throws NotFoundException{
        if (getCacheStorage().containsKey(key)) {
            Iterator<K> queueItr = getQueue().iterator();
            while(queueItr.hasNext()) {
                K qKey = queueItr.next();
                if (qKey.equals(key)) {
                    getCacheStorage().remove(key);
                    getQueue().remove(key);
                    break;
                }
            }
        } else {
            StringBuilder sb = new StringBuilder("The Key ");
            sb.append(key);
            sb.append(" is not present in the cache");
            throw new NotFoundException(sb.toString());
        }
    }

    @Override
    public void put(K key, V value) throws NullKeyException {
        if (key != null) {
            System.out.println("Adding Key: " + key + " value: " + value);
            try {
                getQueue().addFirst(key);
            } catch (IllegalStateException ex) {
                K leastUsedKey = getQueue().getLast();
                getQueue().remove(leastUsedKey);
                getCacheStorage().remove(leastUsedKey);
                getQueue().addFirst(key);
            }
            getCacheStorage().put(key, value);
            System.out.println( getCacheStorage());
            System.out.println( getQueue());
        } else {
            throw new NullKeyException("Null key is not allowed in the cache");
        }

    }
}
