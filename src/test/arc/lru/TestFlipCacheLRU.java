package test.arc.lru;

import com.arc.cache.lru.FlipCacheLRU;
import com.arc.exceptions.NotFoundException;
import com.arc.exceptions.NullKeyException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class TestFlipCacheLRU {
    FlipCacheLRU<String, Integer> flipCacheLRU;

    @Before()
    public void setUp() {
        flipCacheLRU = new FlipCacheLRU<>(4);
    }

    @Test(expected = NullKeyException.class)
    public void testPutNull() throws NullKeyException {
        flipCacheLRU.put(null, 12);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNonPresentKey() throws NotFoundException, NullKeyException {
        flipCacheLRU.get("Not");
    }

    @Test(expected = NullKeyException.class)
    public void testGetNullKey() throws NotFoundException, NullKeyException {
        flipCacheLRU.get(null);
    }

    @Test
    public void testPutWithFullCapacity() throws NullKeyException {
        flipCacheLRU.put("First", 1);
        flipCacheLRU.put("Second", 2);
        flipCacheLRU.put("Third", 3);
        flipCacheLRU.put("Fourth", 4);
        flipCacheLRU.put("Fifth", 5);
        assertEquals(flipCacheLRU.getQueue().getLast(), "Second");
    }

    @Test
    public void testGet() throws NullKeyException, NotFoundException {
        flipCacheLRU.put("First", 1);
        flipCacheLRU.put("Second", 2);
        flipCacheLRU.put("Third", 3);
        flipCacheLRU.put("Fourth", 4);
        flipCacheLRU.get("First");

        assertEquals(flipCacheLRU.getQueue().getFirst(), "First");
    }

    @After
    public void destroy() {
        flipCacheLRU = null;
    }

}
