/**
 * Project 1 LinkedListDeque:
 * Implementation of linked list based on object types.
 * @author jiahua wu
 */

/**
 * Generic class for linked list
 * @param <T> type of the content of the list.
 */
public class LinkedListDeque<T> {
    private int size;
    private LinkNode last;
    private LinkNode sentinel;
    /** Constructor of an empty list.
     * Sentinel and last should point to the same object and the list is made circular.
     * */
    public LinkedListDeque() {
        size = 0;
        sentinel = new LinkNode();
        last = sentinel;
        last.next = sentinel;
        sentinel.prev = last;
    }

//    public LinkedListDeque(LinkedListDeque<T> other) {
//        size = 0;
//        sentinel = new LinkNode();
//        last = sentinel;
//        last.next = sentinel;
//        sentinel.prev = last;
//        int i = 0;
//        while(other.get(i) != null) {
//            this.addLast(other.get(i));
//            i++;
//        }
//    }

    /**
     * Add node to the head of the list.
     * Note that if the size is one, the pointer last should move.
     * @param x element to be added.
     */
    public void addFirst(T x) {
        sentinel.next = new LinkNode(x, sentinel.next, sentinel);
        sentinel.next.next.prev = sentinel.next;
        if(isEmpty()) {
            last = sentinel.next;
        }
        size += 1;
    }

    public void addLast(T x) {
        last.next = new LinkNode(x, sentinel, last);
        last = last.next;
        size += 1;
    }

    public boolean isEmpty() {
        return sentinel == last;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        LinkNode p = sentinel.next;
        while(p.next != sentinel) {
            System.out.print(p.item);
            System.out.print(' ');
            p = p.next;
        }
        System.out.print(p.item);
        System.out.println();
    }

    /**
     * Remove the first element of the list.
     * Note that if the size of list is one, the last pointer should point to sentinel
     * @return the item of the node being removed
     */
    public T removeFirst() {
        LinkNode first = sentinel.next;
        T element = first.item;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        if(size == 1) {
            last = sentinel;
        }
        size--;
        return element;
    }

    public T removeLast() {
        T element =  last.item;
        last = last.prev;
        last.next = sentinel;
        size--;
        return element;
    }

    public T get(int i) {
        LinkNode p = sentinel;
        while(p.next != sentinel && i >= 0) {
            p = p.next;
            i--;
        }
        if(i >= 0) {
            return null;
        } else {
            return p.item;
        }
    }

    private T getRecursiveHelper(int i, LinkNode p) {
        if(p.next == sentinel && i >= 0){
            return null;
        } else if(i < 0) {
            return p.item;
        } else {
            return getRecursiveHelper(i - 1, p.next);
        }
    }
    public T getRecursive(int i) {
        return getRecursiveHelper(i, sentinel);
    }

    private class LinkNode {
        public T item;
        public LinkNode next;
        public LinkNode prev;

        public LinkNode() {
            item = null;
            next = null;
            prev = null;
        }

        public LinkNode(T x, LinkNode n, LinkNode p) {
            item = x;
            next = n;
            prev = p;
        }
    }
}
