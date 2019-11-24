package ua.edu.ucu.datastructures;

public final class ImmutableLinkedList implements ImmutableList {

    private Node head;
    private int size;

    private static class Node {
        private Object value;
        private Node next = null;

        public Node(Object val) {
            this.value = val;
        }
    }

    public ImmutableLinkedList() {

        head = null;
        size = 0;
    }

    private void insertEnd(Object obj) {
        Node objNode = new Node(obj);
        if (head == null) {
            head = objNode;
        }
        else {
            Node pointer = head;
            while (pointer.next != null) {
                pointer = pointer.next;
            }

            pointer.next = objNode;
        }
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        return this.add(size, e);
    }



    @Override
    public ImmutableLinkedList add(int index, Object e) {

        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(
                    "LinkedList Index Out Of Range");
        }

        ImmutableLinkedList newInstance = new ImmutableLinkedList();
        Node pointer = head;
        for (int nodes = 0; nodes < index; nodes++) {
            newInstance.insertEnd(pointer.value);
            newInstance.size++;
            pointer = pointer.next;
        }
        newInstance.insertEnd(e);
        newInstance.size++;
        while (pointer != null) {
            newInstance.insertEnd(pointer.value);
            newInstance.size++;
            pointer = pointer.next;
        }
        return newInstance;
    }



    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return this.addAll(size, c);
    }



    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {

        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException(
                    "LinkedList Index Out Of Range");
        }

        ImmutableLinkedList newInstance = new ImmutableLinkedList();
        Node pointer = head;
        for (int nodes = 0; nodes < index; nodes++) {
            newInstance = newInstance.add(pointer.value);
            pointer = pointer.next;
        }
        for (Object obj: c) {
            newInstance = newInstance.add(obj);
        }
        while (pointer != null) {
            newInstance = newInstance.add(pointer.value);
            pointer = pointer.next;
        }
        return newInstance;
    }



    @Override
    public Object get(int index) {

        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(
                    "LinkedList Index Out Of Range");
        }
        Node pointer = head;
        for (int i = 0; i < index; i++) {
            pointer = pointer.next;
        }
        return pointer.value;
    }



    @Override
    public ImmutableLinkedList remove(int index) {

        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(
                    "LinkedList Index Out Of Range");
        }

        Node pointer = head;
        ImmutableLinkedList newInstance = new ImmutableLinkedList();
        for (int i = 0; i < index; i++) {
            newInstance = newInstance.add(pointer.value);
            pointer = pointer.next;
        }
        pointer = pointer.next;
        while (pointer != null) {
            newInstance = newInstance.add(pointer.value);
            pointer = pointer.next;
        }
        return newInstance;
    }



    @Override
    public ImmutableLinkedList set(int index, Object e) {

        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(
                    "LinkedList Index Out Of Range");
        }
        ImmutableLinkedList removed = this.remove(index);
        return removed.add(index, e);
    }



    @Override
    public int indexOf(Object e) {

        Node pointer = head;
        int i = 0;
        while (pointer != null) {
            if (e == null || pointer.value == null) {
                if (pointer.value == e) {
                    return i;
                }
            }
            else {
                if (pointer.value.equals(e)) {
                    return i;
                }
            }
            pointer = pointer.next;
            i++;
        }
        return -1;
    }



    @Override
    public int size() {
        return size;
    }



    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }



    @Override
    public boolean isEmpty() {
        return size == 0 && head == null;
    }



    @Override
    public Object[] toArray() {
        Node pointer = head;
        Object[] arr = new Object[size];
        int i = 0;
        while (pointer != null) {
            arr[i] = pointer.value;
            i++;
            pointer = pointer.next;
        }
        return arr;
    }


    @Override
    public String toString() {
        Node pointer = head;
        StringBuffer str = new StringBuffer();
        while (pointer != null) {
            str.append(pointer.value + ", ");
            pointer = pointer.next;
        }
        String strRes = str.toString();
        if (strRes.length() > 0) {
            strRes = strRes.substring(0, strRes.length() - 2);
        }
        return strRes;
    }



    public ImmutableLinkedList addFirst(Object e) {

        return this.add(0, e);
    }



    public ImmutableLinkedList addLast(Object e) {

        return this.add(e);
    }



    public Object getFirst() {

        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Empty structure");
        }

        return head.value;
    }



    public Object getLast() {

        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Empty structure");
        }
        Node pointer = head;
        while (pointer.next != null) {
            pointer = pointer.next;
        }
        return pointer.value;
    }



    public ImmutableLinkedList removeFirst() {
        return this.remove(0);
    }



    public ImmutableLinkedList removeLast() {
        return this.remove(size - 1);
    }
}
