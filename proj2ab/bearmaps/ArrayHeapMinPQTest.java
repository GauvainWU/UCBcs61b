package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    @Test
    public void testAddRemove() {
        ArrayHeapMinPQ<String> test = new ArrayHeapMinPQ<>();
        test.add("a",0.1);
        test.add("b", 0.2);
        test.add("c",0.3);
        assertEquals("a", test.removeSmallest());
        test.changePriority("c", 0.15);
        assertEquals("c",test.removeSmallest());
        assertTrue(test.contains("b"));
        assertFalse(test.contains("c"));
        assertEquals("b", test.removeSmallest());
        assertNull(test.removeSmallest());
    }

    @Test
    public void testAdd() {
        ArrayHeapMinPQ<String> test = new ArrayHeapMinPQ<>();
        test.add("d", 0.4);
        test.add("e", 0.5);
        test.add("a",0.1);
        test.add("b", 0.2);
        test.add("c",0.3);
        assertEquals("a", test.removeSmallest());
        test.changePriority("e", 0.1);
        assertEquals("e", test.removeSmallest());
        assertEquals("b",test.removeSmallest());
        assertEquals("c",test.removeSmallest());
        assertEquals("d",test.removeSmallest());
    }

    @Test
    public void testAddSame() {
        ArrayHeapMinPQ<String> test = new ArrayHeapMinPQ<>();
        test.add("d", 0.4);
        test.add("e", 0.5);
        test.add("a",0.1);
        test.add("b", 0.2);
        test.add("c",0.3);
        test.add("f",0.1);
        test.removeSmallest();
        test.removeSmallest();
        assertEquals("b",test.removeSmallest());
    }

    @Test
    public void multipleRandomTests() {
        for (int i = 0; i < 1000; i++) {
            randomTest(100);
        }
    }

    private void randomTest(int num) {
        Random rd = new Random();
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> naive = new NaiveMinPQ<>();
        for (int i = 0; i < num; i++) {
            int item = i;
            double priority = rd.nextDouble();
            test.add(item,priority);
            naive.add(item, priority);
            assertEquals(test.getSmallest(), naive.getSmallest());
        }

        for (int i = 1; i < num; ++i) {
            double newPriority = rd.nextDouble();
            test.changePriority(i, newPriority);
            naive.changePriority(i, newPriority);
            assertEquals(test.getSmallest(), naive.getSmallest());
        }
//        test.printAllPriority();
        for (int i = 0; i < num; ++i) {
            assertEquals(test.removeSmallest(), naive.removeSmallest());
        }
    }
}
