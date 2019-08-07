public class LinkedListDeque<type> {
    private int size;
    private LinkNode last;
    private LinkNode sentinel;

    // Constructor of an empty list. Sentinel and last should point to the same object.
    public LinkedListDeque (){
        size = 0;
        sentinel = new LinkNode();
        last = sentinel;
        last.next = sentinel; // Make the list circular
        sentinel.prev = last;
    }

    public LinkedListDeque (LinkedListDeque<type> other){
        size = 0;
        sentinel = new LinkNode();
        last = sentinel;
        last.next = sentinel;
        sentinel.prev = last;
        int i = 0;
        while (other.get(i) != null){
            this.addLast(other.get(i));
            i++;
        }
    }

    public void addFirst(type x){
        sentinel.next = new LinkNode(x,sentinel.next,sentinel);
        if (isEmpty()){
            last = sentinel.next;  // If the size is one, the pointer last should move.
        }
        size += 1;
    }

    public void addLast(type x){
        last.next = new LinkNode(x,sentinel,last);
        last = last.next;
        size += 1;
    }

    public boolean isEmpty(){
        return sentinel == last;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        LinkNode p = sentinel.next;
        while (p.next != sentinel) {
            System.out.print(p.item);
            System.out.print(' ');
            p = p.next;
        }
        System.out.print(p.item);
        System.out.println();
    }

    public type removeFirst(){
        LinkNode first = sentinel.next;
        type element = first.item;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        if (size == 1){
            last = sentinel;   // If the size of list is one, the last pointer should move to sentinel
        }
        size --;
        return element;
    }

    public type removeLast(){
        type element =  last.item;
        last = last.prev;
        last.next = sentinel;
        size --;
        return element;
    }

    public type get(int i){
        LinkNode p = sentinel;
        while (p.next != sentinel && i >= 0){
            p = p.next;
            i --;
        }
        if (i >= 0){
            return null;
        }
        else{
            return p.item;
        }
    }

    private type getRecursiveHelper(int i, LinkNode p){
        if (p.next == sentinel && i >= 0){
            return null;
        }
        else if (i < 0){
            return p.item;
        } else{
            return getRecursiveHelper(i-1,p.next);
        }
    }
    public type getRecursive(int i){
        return getRecursiveHelper(i,sentinel);
    }

    public class LinkNode{
        public type item;
        public LinkNode next;
        public LinkNode prev;

        public LinkNode(){
            item = null;
            next = null;
            prev = null;
        }

        public LinkNode (type x, LinkNode n, LinkNode p){
            item = x;
            next = n;
            prev = p;
        }
    }


}
