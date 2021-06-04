public class MyArrayList<T> implements MyList<T> {
    private Object[] data;
    private int cursor;
    private int capacity;
    private static final int FACTOR = 5;

    public MyArrayList() {
        capacity = FACTOR;
        data = new Object[capacity];
        cursor = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    @Override
    public void add(T obj) {
        if (cursor == capacity - 1) {
            growArray();
        }
        data[cursor++] = obj;
    }

    private void growArray() {
        capacity += FACTOR;
        Object[] newData = new Object[capacity];
        System.arraycopy(data, 0, newData, 0, cursor);
        data = newData;
    }

    @Override
    public void removeAt(int index) {
        checkIndex(index);
        for (int i = index; i < cursor - 1; i++) {
            data[i] = data[i + 1];
        }
        cursor--;
    }

    @Override
    public boolean remove(T obj) {
        int index = indexOf(obj);
        if (index != -1) {
            for (int i = index; i < cursor - 1; i++) {
                data[i] = data[i + 1];
            }
            cursor--;
            return true;
        } else {
            return false;
        }

    }

    private int indexOf(T obj) {
        for (int i = 0; i < cursor; i++) {
            if (data[i] == obj) {
                return i;
            }
            if (data[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T pop() {
        T obj = (T) data[cursor - 1];
        Object[] newPopData = new Object[capacity - 1];
        System.arraycopy(data, 0, newPopData, 0, cursor - 1);
        data = newPopData;
        cursor--;
        return obj;
    }

    @Override
    public int size() {
        return cursor;
    }

    @Override
    public boolean contains(T obj) {
        return indexOf(obj) != -1;
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index >= cursor) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < cursor; i++) {
            result.append(data[i]);
            if (i < cursor - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
}
