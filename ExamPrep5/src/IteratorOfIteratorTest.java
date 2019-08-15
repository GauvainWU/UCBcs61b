import java.util.*;
public class IteratorOfIteratorTest {
    public static void main(String[] args) {
        ArrayList<Integer> l1 = new ArrayList<>();
        ArrayList<Integer> l2 = new ArrayList<>();
        ArrayList<Integer> l3 = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            l1.add(i);
            l2.add(i + 3);
            l3.add(i + 6);
            l3.add(i + 9);
        }
        ArrayList<Iterator<Integer>> a = new ArrayList<>();
        a.add(l1.iterator());
        a.add(l2.iterator());
        a.add(l3.iterator());
        for (IteratorOfIterators ii = new IteratorOfIterators(a); ii.hasNext();){
            System.out.println(ii.next());
        }
    }

}
