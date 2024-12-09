package model.academic;

import java.io.Serial;

/**
 * model.academic.Pair class to store key-value pairs
 * @param <K> - key
 * @param <V> - value
 */
public class Pair<K, V> implements java.io.Serializable {
    private K key;
    private V value;

    @Serial
    private static final long serialVersionUID = 1L;

    public Pair() {
        this.key = null;
        this.value = null;
    }
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
