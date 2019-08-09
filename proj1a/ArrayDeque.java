/**
 * Project 1a ArrayDeque: Implementation of linked list based on array
 * @author jiahua wu
 */

public class ArrayDeque<T> {
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
        nextFirst = 2;
    }

    /**
     * Copy data and useful attributes.
     * @param other the list to be copied.
     */
    public ArrayDeque(ArrayDeque<T> other) {
        int otherLength = other.data.length;
        data = (T[]) new Object[otherLength];
        size = other.size;
        nextFirst = other.nextFirst;
        System.arraycopy(other.data, 0, data, 0, otherLength);
    }

    /**
     *   Function that transfers list index (starting from nextFirst + 1) to the index in the data array.
     * @param i index in list.
     * @param length length of the data array
      */
    private int convertToArrayIndex(int i, int length) {
        return (nextFirst + 1 + i + length) % length;
    }

    private boolean isFull() {
        return size == data.length;
    }

    /**
     * Modify the array to be a given new length.
     * @param newSize the new size(length) of the array
     */
    private void resize(int newSize){
        T[] newArray = (T[]) new Object[newSize];
        /* Situation of size increasing.(When the array is full and we add element)*/
        if (convertToArrayIndex(0, data.length) + size > data.length) {
            /** Copy the elements starting from 0 (list index) to the end of the array*/
            int zeroToEndLength = data.length - convertToArrayIndex(0, data.length);
            System.arraycopy(data, convertToArrayIndex(0, data.length),
                    newArray, newArray.length - zeroToEndLength, zeroToEndLength);
            /**Copy the elements starting from 0 (array index) to end (list index)*/
            System.arraycopy(data, 0, newArray, 0, convertToArrayIndex(size, data.length));
            /* Case of size decreasing. (last element does not circulate back to the front)*/
        } else {
            System.arraycopy(data, convertToArrayIndex(0, data.length), newArray, 0, size);
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
    public void addFirst(T x) {
        if (isFull()) {
            upSize();
        }
        data[convertToArrayIndex(-1, data.length)] = x;
        nextFirst--;
        size++;
    }

    public void addLast(T x) {
        if (isFull()) {
            upSize();
        }
        data[convertToArrayIndex(size, data.length)] = x;
        size++;
    }

    public T removeFirst() {
        nextFirst++;
        size--;
        T returnItem = data[convertToArrayIndex(-1, data.length)];
        data[convertToArrayIndex(-1, data.length)] = null;
        if (size == data.length / 2){
            downSize();
        }
        return returnItem;
    }

    public T removeLast() {
        size--;
        T returnItem = data[convertToArrayIndex(size, data.length)];
        data[convertToArrayIndex(size, data.length)] = null;
        if (size == data.length / 2 && size >= 8){
            downSize();
        }
        return returnItem;
    }

    public T get(int i) {
        return data[convertToArrayIndex(i, data.length)];
    }

    public int size() {
        return size;
    }

    public void printDeque(){
        for (int i = 0; i < size; ++i) {
            System.out.print(data[convertToArrayIndex(i, data.length)]);
            System.out.print(' ');
        }
        System.out.println();
    }
}
