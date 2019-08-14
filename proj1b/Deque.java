public interface Deque<T> {
    default boolean isEmpty() {
        return size() == 0;
    };
    void addFirst(T x);
    void addLast(T x);
    T removeFirst();
    T removeLast();
    T get(int i);
    int size();
    void printDeque();
}
