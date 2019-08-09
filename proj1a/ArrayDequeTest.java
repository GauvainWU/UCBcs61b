import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void addRemoveTest() {
        ArrayDeque<Integer> test = new ArrayDeque<Integer>();
        for (int i = 0; i < 7; i++) {
            test.addLast(i);
        }
        assertEquals((int) 0, (int) test.get(0));
    }

    @Test
    public void test2() {
        ArrayDeque<Integer> test = new ArrayDeque<Integer>();
        for (int i = 0; i < 9; i++) {
            test.addLast(i);
        }
        assertNotEquals(null, test.removeFirst());
        assertNotEquals(null, test.removeLast());
    }

    @Test
    public void test3() {
        ArrayDeque<Integer> test = new ArrayDeque<Integer>();
        test.addLast(0);
        test.get(0);
        test.removeLast();
        test.addLast(3);
        test.addFirst(4);
        test.addFirst(5);
        test.addFirst(6);
        test.addLast(7);
        test.addLast(8);
        test.addFirst(9);
        test.addLast(10);
        test.addFirst(11);
        assertNotEquals(test.removeLast(),null);
    }

    @Test
    public void test4() {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        for (int i = 2; i > -1 ; i--) {
            test.addFirst(i);
        }
        for (int i = 3; i < 8; i++){
            test.addLast(i);
        }
        test.addLast(9);
        assertNotEquals(test.removeLast(), null);
    }

    @Test
    public void test5() {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        for (int i = 0; i < 10; i++){
            test.addFirst(0);
        }
        for (int i = 0; i < 10; i++){
            assertNotEquals(null, test.removeFirst());
        }
    }
}
