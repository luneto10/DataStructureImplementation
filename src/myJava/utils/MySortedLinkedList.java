package myJava.utils;

public class MySortedLinkedList<T extends Comparable<T>> {
    private int size;
    private Node<T> head;
    private Node<T> tail;

    public MySortedLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size(){
        return this.size;
    }

    public void removeAll(){
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    private Node<T> getNode(int index){
        Node<T> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }

    public void add(T item){
        if (item == null){
            throw new IllegalArgumentException("Cannot add a null element");
        }
        if(isEmpty()){
            this.head = new Node<>(item);
            this.tail = this.head;
            this.size++;
            return;
        }
        Node<T> newNode = new Node<>(item);

        if (item.compareTo(this.head.getItem()) <= 0){
            this.head.setPrev(newNode);
            newNode.setNext(this.head);
            this.head = newNode;
            size++;
            return;
        }

        if (item.compareTo(this.tail.getItem()) >= 0){
            this.tail.setNext(newNode);
            newNode.setPrev(this.tail);
            this.tail = newNode;
            size++;
            return;
        }

        Node<T> current = this.head;

        for (int i = 0; i < size; i++) {
            if (item.compareTo(current.getItem()) < 0){
                break;
            }
            current = current.getNext();
        }

        Node<T> previous = current.getPrev();

        previous.setNext(newNode);
        newNode.setPrev(previous);

        newNode.setNext(current);
        current.setPrev(newNode);

        this.size++;
    }

    public T pop(){
        if (isEmpty()){
            throw new IllegalStateException("Cannot pop an empty list");
        }
        Node<T> toDelete = getNode(this.size - 1);

        this.tail = this.tail.getPrev();
        this.tail.setNext(null);
        size--;
        return toDelete.getItem();
    }

    public T remove(int index){
        boundCheck(index);

        if(index == 0){
            Node<T> toDelete = this.head;
            this.head = this.head.getNext();
            size--;
            return toDelete.getItem();
        }
        if (index == this.size - 1){
            return pop();
        }

        Node<T> toBeDeleted = this.getNode(index);
        Node<T> after = toBeDeleted.getNext();
        Node<T> before = toBeDeleted.getPrev();

        after.setPrev(before);
        before.setNext(after);
        size--;
        return toBeDeleted.getItem();
    }

    public void addFromArray(T [] array){
        for(T element : array){
            add(element);
        }
    }

    private void boundCheck (int index){
        if (index < 0){
            throw new IllegalArgumentException("Index less than 0");
        }
        if (index >= this.size){
            throw new IndexOutOfBoundsException();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> current = this.head;
        while (current != null) {
            sb.append(current.getItem());
            if (current.getNext() != null) {
                sb.append(", ");
            }
            current = current.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}
