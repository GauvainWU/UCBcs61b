import edu.princeton.cs.algs4.Queue;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;
public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<Integer> test = new Queue<>();
        Random rd = new Random();
        for (int j = 0; j < 10; ++j) {
            for (int i = 0; i < 1000; ++i) {
                test.enqueue(rd.nextInt(10));
            }
            assertTrue(isSorted(QuickSort.quickSort(test)));
        }
    }

    @Test
    public void testMergeSort() {
        Queue<Double> test = new Queue<>();
        Random rd = new Random();
        for (int j = 0; j < 10; ++j) {
            for (int i = 0; i < 1000; ++i) {
                test.enqueue(rd.nextDouble());
            }
            assertTrue(isSorted(MergeSort.mergeSort(test)));
        }
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
