package bearmaps;

import org.junit.Test;

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
}
