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
        assertNotEquals(null, test.removeLast());
        assertNotEquals(null, test.removeFirst());
    }


}
