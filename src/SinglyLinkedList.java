public class SinglyLinkedList<T> implements MyList<T> {
    Node<T> head;
    Node<T> tail;
    int size;

    @Override
    public T get(int index) {
        checkIndex(index);
        Node<T> tempNode = head;
        for (int i = 0; i < index; i++) {
            tempNode = tempNode.next;
        }
        return tempNode.value;
    }

    @Override
    public void add(T obj) {
        Node<T> tempNode = new Node<>(obj);
        if (head == null) {
            head = tempNode;
            tail = tempNode;
            size++;
            return;
        }
        tail.next = tempNode;
        tail = tempNode;
        size++;
    }

    @Override
    public void removeAt(int index) {
        checkIndex(index);
        Node<T> tempNode = head;
        if (index == 0) {
            head = tempNode.next;
            size--;
            return;
        }
        for (int i = 0; i < index - 1; i++) {
            tempNode = tempNode.next;
        }
        if (index == size - 1) {
            tempNode.next = tail;
            tail = null;
            size--;
            return;
        }
        tempNode.next = tempNode.next.next;
        size--;
    }

    @Override
    public boolean remove(T obj) {
        Node<T> tempNode = head;
        while (tempNode.next != null) {
            if (tempNode.next.value == obj) {
                tempNode.next = tempNode.next.next;
                size--;
                return true;
            } else if (tempNode.next.value.equals(obj)) {
                tempNode = tempNode.next.next;
                size--;
                return true;
            }
            tempNode = tempNode.next;
        }
        return false;
    }

    @Override
    public T pop() {
        T obj = tail.value;
        Node<T> tempNode = head;
        for (int i = 0; i < size - 2; i++) {
            tempNode = tempNode.next;
        }
        tempNode.next = null;
        tail = tempNode;
        size--;
        return obj;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(T obj) {
        Node<T> tempNode = head;
        while (tempNode != null) {
            if (tempNode.value == obj) {
                return true;
            } else if (tempNode.value.equals(obj)) {
                return true;
            }
            tempNode = tempNode.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.value);
            if (i < size - 1) {
                result.append(", ");
            }
            current = current.next;
        }
        result.append("]");
        return result.toString();
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Invalid index input");
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index can't exceed the size");
        }
    }

}
