public interface Deque<T> {
    default public boolean isEmpty(){
        return size() == 0;
    };

    public void addFirst(T x);
    public void addLast(T x);
    public T removeFirst();
    public T removeLast();
    public T get(int i);
    public int size();
    public void printDeque();
}
