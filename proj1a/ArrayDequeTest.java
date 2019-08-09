public class ArrayDequeTest {

    public static void addRemoveTest() {
        ArrayDeque<Integer> test = new ArrayDeque<Integer>();
        for (int i = 0; i < 10; i++) {
            test.addFirst(i);
            test.addLast(i);
        }
        ArrayDeque<Integer> test2 = new ArrayDeque<Integer>(test);
        test2.printDeque();
        System.out.println(test.size());
        for (int i = 0; i < 10; ++i){
            test.removeFirst();
            test.removeLast();
        }
        test.printDeque();
        System.out.println(test.size());
    }

    public static void main(String[] args) {
        addRemoveTest();
    }

}
