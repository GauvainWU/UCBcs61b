package synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Return the capacity of a queue
     */
    @Override
    public int capacity() {
        return rb.length;
    }

    /**
     * Return the number of items currently in the queue
     */
    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer overflow");
        }
        rb[last % rb.length] = x;
        last++;
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        T returnItem = rb[first % rb.length];
        rb[first % rb.length] = null;
        first++;
        fillCount--;
        return returnItem;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        return rb[first % rb.length];
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.

    @Override
    public Iterator<T> iterator() {
        return new ARBIterator();
    }

    private class ARBIterator implements Iterator<T> {
        private int index;

        public ARBIterator() {
            index = first;
        }

        public boolean hasNext() {
            return index < last;
        }

        public T next() {
            T returnItem = rb[index % rb.length];
            index++;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other.getClass() != this.getClass()) {
            return false;
        }
        if (((ArrayRingBuffer<T>) other).fillCount() != this.fillCount()){
            return false;
        }
        for (int i = 0; i < this.fillCount(); ++i) {
            if (((ArrayRingBuffer<T>) other).rb[i] != this.rb[i]){
                return false;
            }
        }
        return true;
    }
}

