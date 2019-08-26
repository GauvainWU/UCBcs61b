import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private HashSet<K> keySet;
    private ULLMap[] buckets;
    private final double loadFactorConstant;

    /**
     * Constructors
     */
    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
       this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.keySet = new HashSet<>();
        this.buckets = new ULLMap[initialSize];
        for (int i = 0; i < this.buckets.length; ++i) {
            this.buckets[i] = new ULLMap<K,V>();
        }
        this.loadFactorConstant = loadFactor;
    }

    /** Removes all of the mappings from this map. */
    public void clear() {
        this.keySet.clear();
        for (ULLMap um : buckets) {
            um.clear();
        }
    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        return keySet.contains(key);
    }

    /**
     * Returns hashcode for an object
     */
    private int MyHashCode(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % buckets.length;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        return (V) buckets[MyHashCode(key)].get(key);
    }

    /** Returns the number of key-value mappings in this map. */
    public int size() {
        return keySet.size();
    }

    /**
     *
     * @return load factor of the table
     */
    private double loadFactor() {
        return size() / this.buckets.length;
    }

    /**
     * Resize the array of ullmap to maintain the loadFactor
     */

    private void resize() {
        ULLMap[] newArray = new ULLMap[buckets.length * 2];
        for (int i = 0; i < newArray.length; ++i) {
            newArray[i] = new ULLMap<K,V>();
        }
        System.arraycopy(this.buckets, 0, newArray, 0, this.buckets.length);
        this.buckets = newArray;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value) {
        if (loadFactor() > loadFactorConstant) {
            resize();
        }
        this.keySet.add(key);
        this.buckets[MyHashCode(key)].put(key, value);
    }

    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        return this.keySet;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key) {
        keySet.remove(key);
        return (V) this.buckets[MyHashCode(key)].remove(key);
    }

    @Override
    public Iterator<K> iterator() {
        return keySet.iterator();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    };
}
