# flip-cache

Flip cache is an in-memory cache which helps defining a key-value cache of a fixed size with user defined data types for Key and Value and different eviction policies.

**src/com/arc/cache/FlipCacheBase.java** is a base class with generic methods.

**flip-cache/src/com/arc/cache/lru/FlipCacheLRU.java** is imlementation of the cache with LRU eviction policy
