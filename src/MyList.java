public interface MyList<T> {
    T get(int index);

    void add(T obj);

    void removeAt(int index);

    boolean remove(T obj);

    T pop();

    int size();

    boolean contains(T obj);
}
