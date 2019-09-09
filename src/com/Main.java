package com;

import com.arc.cache.lru.FlipCacheLRU;
import com.arc.exceptions.NotFoundException;
import com.arc.exceptions.NullKeyException;

public class Main {

    public static void main(String[] args) {
        try {
            FlipCacheLRU<String, Integer> flipCacheLRU = new FlipCacheLRU(2);
            flipCacheLRU.put("1", 1);
            flipCacheLRU.put("2", 2);
            flipCacheLRU.get("1");
            flipCacheLRU.put("3", 3);
            flipCacheLRU.put(null, 3);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (NullKeyException e) {
            e.printStackTrace();
        }
    }
}
