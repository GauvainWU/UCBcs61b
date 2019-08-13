/**
 * Project 1a ArrayDeque: Implementation of linked list based on array
 * @author jiahua wu
 */

public class ArrayDeque<T> implements Deque<T>{
    private T[] data;
    private int size;
    private int nextFirst;

    /**
     * Constructor of an empty list.
     * Note that nextLast is the index of the last node with respect to nextFirst (Always 1)
     */
    public ArrayDeque() {
        data = (T[]) new Object[8];
        size = 0;
        nextFirst = -1;
    }

    /**
     * Copy data and useful attributes.
     * @param other the list to be copied.
     */
//    public ArrayDeque(ArrayDeque<T> other) {
//        int otherLength = other.data.length;
//        data = (T[]) new Object[otherLength];
//        size = other.size;
//        nextFirst = other.nextFirst;
//        System.arraycopy(other.data, 0, data, 0, otherLength);
//    }

    /**
     *   Function that transfers list index (starting from nextFirst + 1) to the index in the data array.
     * @param i index in list.
      */
    private int convertToArrayIndex(int i) {
        if (Math.abs(nextFirst) > data.length){
            nextFirst += data.length;
        }
        int initPos = nextFirst + 1 + i;
        return (initPos + data.length) % data.length;
    }

    private boolean isFull() {
        return size == data.length;
    }

    /**
     * Modify the array to be a given new length.
     * @param newSize the new size(length) of the array
     */
    private void resize(int newSize) {
        T[] newArray = (T[]) new Object[newSize];
        /* Situation of circular list(last element return to the first [0 1 null null 1 2])*/
        if (convertToArrayIndex(0) + size > data.length) {
            /** Copy the elements starting from 0 (list index) to the end of the array*/
            int zeroToEndLength = data.length - convertToArrayIndex(0);
            System.arraycopy(data, convertToArrayIndex(0),
                    newArray, newArray.length - zeroToEndLength, zeroToEndLength);
            /**Copy the elements starting from 0 (array index) to end (list index)*/
            System.arraycopy(data, 0, newArray, 0, convertToArrayIndex(size));
            /* When nextFirst is positive, it needs to be increased/decreased. Otherwise it can just keep negative.
            * (positive means non circular , negative means circular)*/
            if (nextFirst + 1 > 0) {
                nextFirst += (newSize - data.length);
            }
            /* Case of non circular ([0 1 2 3 null null])*/
        } else {
            System.arraycopy(data, convertToArrayIndex(0), newArray, 0, size);
            nextFirst = -1;
        }
        data = newArray;
    }

    private void upSize() {
        resize(data.length * 2);
    }

    private void downSize() {
        resize(data.length / 2);
    }

    /**
     *     List index always varies from 0 to size - 1. Thus addFirst adds element at -1 and addLast adds element at
     *     size.
      */
    @Override
    public void addFirst(T x) {
        if(isFull()) {
            upSize();
        }
        if (convertToArrayIndex(-1) < 0){
            System.out.println(nextFirst);
            this.printDeque();
        }
        data[convertToArrayIndex(-1)] = x;
        nextFirst--;
        size++;
    }

    @Override
    public void addLast(T x) {
        if(isFull()) {
            upSize();
        }
        data[convertToArrayIndex(size)] = x;
        size++;
    }

    @Override
    public T removeFirst() {
        if(isEmpty()) {
            return null;
        }
        nextFirst++;
        size--;
        T returnItem = data[convertToArrayIndex(-1)];
        data[convertToArrayIndex(-1)] = null;
        if (size == data.length / 2 && size >= 8) {
            downSize();
        }
        return returnItem;
    }

    @Override
    public T removeLast() {
        if(isEmpty()) {
            return null;
        }
        size--;
        T returnItem = data[convertToArrayIndex(size)];
        data[convertToArrayIndex(size)] = null;
        if (size == data.length / 2 && size >= 8) {
            downSize();
        }
        return returnItem;
    }

    @Override
    public T get(int i) {
        return data[convertToArrayIndex(i)];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; ++i) {
            System.out.print(data[convertToArrayIndex(i)]);
            System.out.print(' ');
        }
        System.out.println();
    }
}
