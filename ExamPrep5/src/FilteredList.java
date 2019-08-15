import java.util.*;
public class FilteredList<T> implements Iterable<T> {
    private List<T> data;
    private Predicate<T> filter;

    public FilteredList(List<T> l, Predicate<T> filter){
        this.data = l;
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator(){
        return new FilterIterator(this.data);
    }

    private class FilterIterator implements Iterator<T> {
        private int index;
        private List<T> list;

        public FilterIterator(List<T> data) {
            index = 0;
            list = data;
        }

        @Override
        public boolean hasNext(){
            return index < list.size();
        }

        @Override
        public T next(){
            while (!filter.test(list.get(index))){
                index ++;
            }
            T returnItem = list.get(index);
            index++;
            return returnItem;
        }
    }
}
