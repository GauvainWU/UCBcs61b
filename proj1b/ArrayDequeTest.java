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

    @Test
    public void test6() {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        test.addLast(0);
        test.addFirst(1);
        test.addFirst(2);
        test.removeFirst();
        test.addLast(4);
        test.addFirst(5);
        test.addLast(6);
        test.get(4);
        test.removeFirst();
        test.addFirst(9);
        test.addFirst(10);
        test.removeLast();
        test.addFirst(12);
        test.addLast(13);
        test.addFirst(14);
        test.addFirst(15);
        test.removeFirst();
        test.get(6);
        test.get(0);
        test.addFirst(19);
        test.addFirst(20);
        test.addFirst(21);
        assertNotEquals(null, test.get(8));
    }

    @Test
    public void test7() {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        test.addLast(0);
        test.addLast(1);
        test.addFirst(2);
        test.addLast(3);
        test.removeFirst();
        test.addLast(5);
        test.removeFirst();
        test.removeFirst();
        test.get(0);
        test.get(0);
        test.removeFirst();
        test.addLast(11);
        test.removeFirst();
        test.addFirst(13);
        test.addFirst(14);
        test.addLast(15);
        test.addFirst(16);
        test.removeFirst();
        test.get(2);
        test.removeFirst();
        test.addLast(20);
        test.addLast(21);
        test.addFirst(22);
        assertNotEquals(null, test.get(2));
        for (int i = 0; i < 6; ++i){
            test.removeFirst();
        }
    }

    @Test
    public void test8(){
        ArrayDeque<Integer> test = new ArrayDeque<>();
        test.addLast(0);
        test.get(0);
        test.addLast(2);
        test.addLast(3);
        test.addLast(4);
        test.addLast(5);
        test.addLast(6);
        test.addLast(7);
        test.addLast(8);
        test.removeLast();
        test.removeLast();
        test.addLast(11);
        test.addFirst(12);
        test.addFirst(13);
        test.addLast(14);
        test.removeLast();
        test.removeLast();
        test.get(0);
        test.addLast(18);
        test.addLast(19);
        assertEquals((int) 5, (int) test.get(6));
    }

    @Test
    public void test9(){
        ArrayDeque<Integer> test = new ArrayDeque<>();
        for (int i = 0; i < 18; ++i){
            test.addFirst(i);
        }
        assertEquals((int) 0, (int) test.get(17));
    }

    @Test
    public void addFirstRemoveLastTest(){
        ArrayDeque<Integer> test = new ArrayDeque<>();
        test.addFirst(0);
        test.addFirst(1);
        test.addFirst(2);
        test.addFirst(3);
        test.addFirst(4);
        test.addFirst(6);
        test.addFirst(7);
        test.addFirst(8);
        test.addFirst(9);
        test.addFirst(10);
        test.addFirst(11);
        test.addFirst(12);
        test.addFirst(13);
        test.addFirst(14);
        test.addFirst(15);
        test.addFirst(16);
        test.addFirst(17);
        test.removeLast();
        test.addFirst(19);
        test.addFirst(20);
        test.addFirst(21);
        test.addFirst(22);
        test.removeLast();
        test.addFirst(24);
        test.addFirst(26);
        test.addFirst(27);
        test.addFirst(28);
        test.addFirst(29);
        test.addFirst(30);
        test.addFirst(31);
        test.addFirst(32);
        test.addFirst(33);
        test.addFirst(34);
        test.addFirst(35);
        test.addFirst(36);
        test.addFirst(37);
        test.addFirst(38);
        test.addFirst(39);
        test.addFirst(40);
        test.addFirst(41);
        test.addFirst(42);
        test.addFirst(43);
        test.addFirst(44);
        test.addFirst(45);
        test.removeLast();
        test.addFirst(47);
        test.addFirst(48);
        test.addFirst(49);
        test.addFirst(0);
        test.addFirst(1);
        test.addFirst(2);
        test.addFirst(3);
        test.addFirst(4);
        test.addFirst(6);
        test.addFirst(7);
        test.addFirst(8);
        test.addFirst(9);
        test.addFirst(10);
        test.addFirst(11);
        test.addFirst(12);
        test.addFirst(13);
        test.addFirst(14);
        test.addFirst(15);
        test.addFirst(16);
        test.addFirst(17);
        test.removeLast();
        test.addFirst(19);
        test.addFirst(20);
        test.addFirst(21);
        test.addFirst(22);
        test.removeLast();
        test.addFirst(24);
        test.addFirst(26);
        test.addFirst(27);
        test.addFirst(28);
        test.addFirst(29);
        test.addFirst(30);
        test.addFirst(31);
        test.addFirst(32);
        test.addFirst(33);
        test.addFirst(34);
        test.addFirst(35);
        test.addFirst(36);
        test.addFirst(37);
        test.addFirst(38);
        test.addFirst(39);
        test.addFirst(40);
        test.addFirst(41);
        test.addFirst(42);
        test.addFirst(43);
        test.addFirst(44);
        test.addFirst(45);
        test.removeLast();
        test.addFirst(47);
        test.addFirst(48);
        test.addFirst(49);
        test.addFirst(0);
        test.addFirst(1);
        test.addFirst(2);
        test.addFirst(3);
        test.addFirst(4);
        test.addFirst(6);
        test.addFirst(7);
        test.addFirst(8);
        test.addFirst(9);
        test.addFirst(10);
        test.addFirst(11);
        test.addFirst(12);
        test.addFirst(13);
        test.addFirst(14);
        test.addFirst(15);
        test.addFirst(16);
        test.addFirst(17);
        test.removeLast();
        test.addFirst(19);
        test.addFirst(20);
        test.addFirst(21);
        test.addFirst(22);
        test.removeLast();
        test.addFirst(24);
        test.addFirst(26);
        test.addFirst(27);
        test.addFirst(28);
        test.addFirst(29);
        test.addFirst(30);
        test.addFirst(31);
        test.addFirst(32);
        test.addFirst(33);
        test.addFirst(34);
        test.addFirst(35);
        test.addFirst(36);
        test.addFirst(37);
        test.addFirst(38);
        test.addFirst(39);
        test.addFirst(40);
        test.addFirst(41);
        test.addFirst(42);
        test.addFirst(43);
        test.addFirst(44);
        test.addFirst(45);
        test.removeLast();
        test.addFirst(47);
        test.addFirst(48);
        test.addFirst(49);
        test.addFirst(0);
        test.addFirst(1);
        test.addFirst(2);
        test.addFirst(3);
        test.addFirst(4);
        test.addFirst(6);
        test.addFirst(7);
        test.addFirst(8);
        test.addFirst(9);
        test.addFirst(10);
        test.addFirst(11);
        test.addFirst(12);
        test.addFirst(13);
        test.addFirst(14);
        test.addFirst(15);
        test.addFirst(16);
        test.addFirst(17);
        test.removeLast();
        test.addFirst(19);
        test.addFirst(20);
        test.addFirst(21);
        test.addFirst(22);
        test.removeLast();
        test.addFirst(24);
        test.addFirst(26);
        test.addFirst(27);
        test.addFirst(28);
        test.addFirst(29);
        test.addFirst(30);
        test.addFirst(31);
        test.addFirst(32);
        test.addFirst(33);
        test.addFirst(34);
        test.addFirst(35);
        test.addFirst(36);
        test.addFirst(37);
        test.addFirst(38);
        test.addFirst(39);
        test.addFirst(40);
        test.addFirst(41);
        test.addFirst(42);
        test.addFirst(43);
        test.addFirst(44);
        test.addFirst(45);
        test.removeLast();
        test.addFirst(47);
        test.addFirst(48);
        test.addFirst(49);
    }
}
