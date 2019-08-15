import java.util.*;
public class IteratorOfIterators implements Iterator<Integer> {
    private int times;
    private List<Iterator<Integer>> ls;

    public IteratorOfIterators(List<Iterator<Integer>> a){
        times = 0;
        ls = a;
    }

    @Override
    public boolean hasNext() {
        int i = 0;
        while (!ls.get(times % ls.size()).hasNext() && i < ls.size()) {
            times++;
            i++;
        }
        if (i == ls.size()){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Integer next() {
        Integer returnItem = ls.get(times % ls.size()).next();
        times++;
        return returnItem;
    }
}
