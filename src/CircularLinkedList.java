public class CircularLinkedList<T> implements MyList<T> {
    Node<T> head;
    Node<T> tail;
    int size;


    @Override
    public T get(int index) {
        checkIndex(index);
        if (index < size / 2) {
            Node<T> tempNode = head;
            for (int i = 0; i < index; i++) {
                tempNode = tempNode.next;
            }
            return tempNode.value;
        } else {
            Node<T> tempNode = tail;
            for (int i = size - 1; i < index; i--) {
                tempNode = tempNode.previous;
            }
            return tempNode.value;
        }
    }

    @Override
    public void add(T obj) {
        Node<T> tempNode = new Node<>(obj);
        if (head == null) {
            head = tempNode;
            size++;
            return;
        }
        if (tail == null) {
            tail = tempNode;
            head.next = tail;
            tail.previous = head;
            size++;
            return;
        }
        tail.next = tempNode;
        tempNode.previous = tail;
        tail = tempNode;
        tail.next = head;
        head.previous = tail;
        size++;
    }

    @Override
    public void removeAt(int index) {
        checkIndex(index);
        if (index == 0) {
            Node<T> tempNode = head;
            head = tempNode.next;
            head.previous = tail;
            size--;
        }
        if (index < size / 2) {
            Node<T> tempNode = head;
            for (int i = 0; i < index - 1; i++) {
                tempNode = tempNode.next;
            }
            tempNode.next = tempNode.next.next;
            head.previous = tail;
            tail.next = head;
            size--;
        }
        if (index == size - 1) {
            Node<T> tempNode = tail;
            tail = tempNode.previous;
            head.previous = tail;
            tail.next = head;
            size--;
        }
        if (index > size / 2) {
            System.out.println("es ashxateci");
            Node<T> tempNode = tail;
            for (int i = size - 1; i >= index; i--) {
                tempNode = tempNode.previous;
            }
            tempNode.previous.next = tempNode.next;
            head.previous = tail;
            tail.next = head;
            size--;
        }

    }

    @Override
    public boolean remove(T obj) {
        Node<T> tempNode = head;
        while (tempNode.next != tail) {
            if (tempNode.next.value == obj) {
                tempNode.next = tempNode.next.next;
                head.previous = tail;
                tail.next = head;
                size--;
                return true;
            } else if (tempNode.next.value.equals(obj)) {
                tempNode = tempNode.next.next;
                head.previous = tail;
                tail.next = head;
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
        tempNode.next = head;
        tail = tempNode;
        head.previous = tail;
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
        while (tempNode != tail) {
            if (tempNode.value == obj) {
                return true;
            } else if (tempNode.value.equals(obj)) {
                return true;
            }
            tempNode = tempNode.next;
        }
        return false;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node<T> current = head;
        for (int i = 0; i < size; i++) {
            if (current == null) {
                continue;
            }
            result.append(current.value);
            if (i < size - 1) {
                result.append(", ");
            }
            current = current.next;
        }
        result.append("]");
        return result.toString();
    }
}
