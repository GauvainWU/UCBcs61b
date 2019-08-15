import java.util.*;
public class FilterListTest {
    public static void main(String[] args) {
        ArrayList<Integer> test = new ArrayList<Integer>();
        OddFilter of = new OddFilter();
        for (int i = 0; i < 6; i++) {
            test.add(i);
        }
        FilteredList fl = new FilteredList(test, of);
        for (Object i : fl){
            System.out.println(i);
        }
    }

}
