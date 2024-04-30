package myJava.utils;

public class MyLinkedList <T>{
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    public int getSize(){
        return this.size;
    }

    public void clean(){
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

    public void push(T item){
        if(this.isEmpty()){
            this.head = new Node<>(item);
            this.tail = this.head;
            this.size++;
            return;
        }
        Node<T> tail = this.tail;
        Node<T> newNode = new Node<>(item);

        tail.setNext(newNode);
        newNode.setPrev(tail);
        this.tail = newNode;

        this.size++;
    }

    public void pushToTheFront(T item){
        Node<T> current = this.head;
        Node<T> newNode = new Node<>(item);
        current.setPrev(newNode);
        newNode.setNext(current);
        this.head = newNode;
        this.size++;
    }

    public void insert (int index, T item){
        if (index == 0){
            this.pushToTheFront(item);
            return;
        }

        if(index == this.size){
            this.push(item);
            return;
        }
        Node<T> newNode = new Node<>(item);
        Node<T> nodeBefore = this.getNode(index - 1);
        Node<T> nodeAfter = nodeBefore.getNext();

        newNode.setNext(nodeAfter);
        nodeAfter.setPrev(newNode);
        newNode.setPrev(nodeBefore);
        nodeBefore.setNext(newNode);
        size++;
    }

    public T pop(){
        if (isEmpty()){
            throw new IndexOutOfBoundsException("Cannot pop an empty list");
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
            push(element);
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
}
