import edu.princeton.cs.algs4.BST;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V> {
    private Node root;

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node (K ke, V val, Node left, Node right) {
            this.key = ke;
            this.value = val;
            this.left = left;
            this.right = right;
        }
    }


    public BSTMap () {
        root = null;
    }

    /** Removes all of the mappings from this map. */
    public void clear() {
        root = null;
    }

    /** Associates the specified value with the specified key in this map. */
    private Node putHelper(K key, V val, Node n) {
        if (n == null) {
            return new Node(key, val, null, null);
        } else if (key.compareTo(n.key) < 0) {
            n.left = putHelper(key, val, n.left);
        } else if (key.compareTo(n.key) > 0) {
            n.right = putHelper(key, val, n.right);
        }
        return n;
    }

    public void put(K key, V val) {
        root = putHelper(key, val, root);
    }

    /** Returns true if this map contains a mapping for the specified key. */
    private boolean containsKeyHelper(K key, Node n) {
        if (n == null) {
            return false;
        } else if (n.key.compareTo(key) == 0) {
            return true;
        } else if (key.compareTo(n.key) > 0) {
            return containsKeyHelper(key, n.right);
        } else if (key.compareTo(n.key) < 0) {
            return containsKeyHelper(key, n.left);
        }
        return false;
    }

    public boolean containsKey(K key) {
        return containsKeyHelper(key, root);
    }

    /** Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    private V getHelper(K key, Node n) {
        if (n == null) {
            return null;
        } else if (n.key.compareTo(key) == 0) {
            return n.value;
        } else if (key.compareTo(n.key) > 0) {
            return getHelper(key, n.right);
        } else if (key.compareTo(n.key) < 0) {
            return getHelper(key, n.left);
        }
        return null;
    }
    public V get(K key) {
        return getHelper(key, root);
    }

    /* Returns the number of key-value mappings in this map. */
    private int sizeHelper (Node n) {
        if (n == null) {
            return 0;
        } else {
            return 1 + sizeHelper(n.left) + sizeHelper(n.right);
        }
    }
    public int size() {
        return sizeHelper(root);
    }

    /** Returns a Set view of the keys contained in this map. */
    private void keySetHelper(Set<K> s, Node n) {
        if (n == null) {
            return;
        } else {
            s.add(n.key);
            keySetHelper(s, n.left);
            keySetHelper(s, n.right);
        }
    }
    public Set<K> keySet() {
        Set<K> result = new HashSet<>();
        keySetHelper(result, root);
        return result;
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    private Node findNode(K key, Node n) {
        if (n == null) {
            return null;
        } else if (n.key.compareTo(key) == 0) {
            return n;
        } else if (key.compareTo(n.key) > 0) {
            return findNode(key, n.right);
        } else if (key.compareTo(n.key) < 0) {
            return findNode(key, n.left);
        }
        return null;
    }
    public V remove(K key) {
        throw new UnsupportedOperationException();
    };

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    };

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
