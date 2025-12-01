package bg.sofia.uni.fmi.mjt.pipeline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {
    private final Cache cache = new Cache();

    @Test
    void testCacheValueAndGetCachedValue() {
        cache.cacheValue("key1", "value1");
        assertEquals("value1", cache.getCachedValue("key1"));
    }

    @Test
    void testCacheValueThrowsOnNullKey() {
        assertThrows(IllegalArgumentException.class, () -> cache.cacheValue(null, "value"));
    }

    @Test
    void testCacheValueThrowsOnNullValue() {
        assertThrows(IllegalArgumentException.class, () -> cache.cacheValue("key", null));
    }

    @Test
    void testGetCachedValueThrowsOnNullKey() {
        assertThrows(IllegalArgumentException.class, () -> cache.getCachedValue(null));
    }

    @Test
    void testContainsKey() {
        cache.cacheValue("key", "value");
        assertTrue(cache.containsKey("key"));
        assertFalse(cache.containsKey("missing"));
    }

    @Test
    void testContainsKeyThrowsOnNullKey() {
        assertThrows(IllegalArgumentException.class, () -> cache.containsKey(null));
    }

    @Test
    void testClearEmptiesCache() {
        cache.cacheValue("k", "v");
        cache.clear();
        assertTrue(cache.isEmpty());
    }

    @Test
    void testIsEmpty() {
        assertTrue(cache.isEmpty());
        cache.cacheValue("k", "v");
        assertFalse(cache.isEmpty());
    }

    @Test
    void testOverwriteValue() {
        cache.cacheValue("k", "v1");
        cache.cacheValue("k", "v2");
        assertEquals("v2", cache.getCachedValue("k"));
    }
}