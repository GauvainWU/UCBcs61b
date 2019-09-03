package bearmaps.proj2ab;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private PriorityNode[] nodes;
    private int size;
    private HashMap<T, Integer> keyPosMap;

    public ArrayHeapMinPQ() {
        nodes = new ArrayHeapMinPQ.PriorityNode[8];
        size = 0;
        keyPosMap = new HashMap<>();
    }

    private void resize() {
        PriorityNode[] newArray = new ArrayHeapMinPQ.PriorityNode[nodes.length * 2];
        System.arraycopy(nodes, 0, newArray, 0, nodes.length);
        nodes = newArray;
    }

    private int parent(int n) {
        return n / 2;
    }

    private int leftChild(int n) {
        return 2 * n;
    }

    private int rightChild(int n) {
        return 2 * n + 1;
    }

    private void swap(int a, int b) {
        keyPosMap.put(nodes[a].getItem(),b);
        keyPosMap.put(nodes[b].getItem(),a);
        PriorityNode tmp = nodes[a];
        nodes[a] = nodes[b];
        nodes[b] = tmp;
    }

    private void sink(int current) {
        if (leftChild(current) <= size && nodes[current].compareTo(nodes[leftChild(current)]) > 0) {
            swap(current,leftChild(current));
            if (rightChild(current) <= size && nodes[current].compareTo(nodes[rightChild(current)]) > 0) {
                swap(current, rightChild(current));
                sink(rightChild(current));
            }
            sink(leftChild(current));
        } else if (rightChild(current) <= size && nodes[current].compareTo(nodes[rightChild(current)]) > 0) {
            swap(current,rightChild(current));
            if (nodes[current].compareTo(nodes[leftChild(current)]) > 0) {
                swap(current, leftChild(current));
                sink(leftChild(current));
            }
            sink(rightChild(current));
        }
    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentExceptionb if item is already present.
     * You may assume that item is never null. */
    public void add(T item, double priority) {
        if (this.contains(item)) {
            throw new IllegalArgumentException("Item already in the PQ");
        }
        size++;
        if (size == nodes.length) {
            resize();
        }
        nodes[size] = new PriorityNode(item, priority);
        keyPosMap.put(item, size);
        adjust(size);
    }

    private void adjust(int current) {
        if (current <= 1) {
            return;
        }
        if (nodes[current].compareTo(nodes[parent(current)]) <= 0) {
            swap(current, parent(current));
        }
        adjust(parent(current));
    }

    /* Returns true if the PQ contains the given item. */
    public boolean contains(T item) {
        return keyPosMap.containsKey(item);
    }

    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T getSmallest() {
        return nodes[1].getItem();
    }



    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T removeSmallest() {
        if (size == 0) {
            return null;
        }
        T returnItem = nodes[1].getItem();
        swap(1, size);
        keyPosMap.remove(returnItem);
        size--;
        sink(1);
        nodes[size + 1] = null;
        return returnItem;
    }

    /* Returns the number of items in the PQ. */
    public int size() {
        return size;
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException();
        }
        int position = keyPosMap.get(item);
        double oldPriority = nodes[position].getPriority();
        nodes[position].setPriority(priority);
        if (priority > oldPriority) {
            sink(position);
        } else {
            adjust(position);
        }
    }

    public void printAllPriority() {
        for (PriorityNode p : nodes) {
            if (p == null) {
                continue;
            }
            System.out.print(String.format("%3f", p.priority) + " ");
        }
        System.out.println();
    }

    private class PriorityNode implements Comparable<PriorityNode> {
        private T item;
        private double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((PriorityNode) o).getItem().equals(getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }
}
