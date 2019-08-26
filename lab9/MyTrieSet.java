import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MyTrieSet implements TrieSet61B {
    private Node root;

    public MyTrieSet() {
        root = new Node();
    }

    private class Node {
        private HashSet<Node> next;
        private boolean isEnd;
        private Character ch;

        public Node(Character ch, boolean isEnd, HashSet<Node> next) {
            this.ch = ch;
            this.isEnd = isEnd;
            this.next = next;
        }

        public Node() {
            this(null, false, new HashSet<>());
        }

    }

    /** Clears all items out of Trie */
    public void clear() {
        root.next = new HashSet<>();
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    public boolean contains(String key) {
        return containsHelper(key, root, 0);
    }

    public boolean containsHelper(String key, Node n, int i) {
        boolean result = false;
        if (n.next.isEmpty()) {
            return false;
        }
        for (Node child : n.next) {
            if (child.ch == key.charAt(i)) {
                if (child.isEnd && i == key.length() - 1) {
                    return true;
                } else if (!child.isEnd && i == key.length() - 1) {
                    return false;
                }
                result |= containsHelper(key, child, i + 1);
            }
        }
        return result;
    }

    /** Inserts string KEY into Trie */
    public void add(String key) {
        addHelper(key, root, 0);
    }

    void addHelper(String key, Node n, int i) {
        char ch = key.charAt(i);
        boolean inSet = false;

        // character of key already in the set
        for (Node child : n.next) {
            if (child.ch == ch) {
                inSet = true;
                if (i == key.length() - 1){
                    child.isEnd = true;
                } else {
                    addHelper(key, child, i + 1);
                }
            }
        }

        // Character of key not in set
        if(!inSet) {
            if (i == key.length() - 1) {
                n.next.add(new Node(ch, true, new HashSet<>()));
            } else {
                n.next.add(new Node(ch, false, new HashSet<>()));
                for (Node child : n.next) {
                    if (child.ch == ch) {
                        addHelper(key, child, i + 1);
                    }
                }
            }
        }
    }

    /** Returns a list of all words that start with PREFIX */
    public List<String> keysWithPrefix(String prefix) {
        HashSet<Node> s = new HashSet<>();
        ArrayList<String> l = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();
        getPrefixNode(prefix, s, root, 0);
        for (Node n : s) {
            l.addAll(getStringsStartingAt(n));
        }
        for (int i = 0; i < l.size(); ++i) {
            result.add(prefix + l.get(i));
        }
        l.clear();
        return result;
    }

    private void getPrefixNode(String prefix, HashSet<Node> s, Node n, int i) {
        if (!n.next.isEmpty()) {
            for (Node child : n.next) {
                if (child.ch == prefix.charAt(i)) {
                    if (i == prefix.length() - 1) {
                        s.add(child);
                    } else {
                        getPrefixNode(prefix, s, child, i + 1);
                    }
                }
            }
        }
    }

    private List<String> getStringsStartingAt(Node n) {
        ArrayList<String> l = new ArrayList<String>();
        getStringsStartingAt(n, "", l);
        return l;
    }

    private void getStringsStartingAt(Node n, String result, List<String> l) {
        if(n.isEnd) {
            l.add(result);
        }
        for (Node child : n.next) {
            String childString = result;
            childString += child.ch;
            getStringsStartingAt(child, childString, l);
        }
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}
