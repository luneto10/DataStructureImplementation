package myJava.utils;

import training.ExNode;

public class MyLinkedList <T>{
    private int size = 0;
    private ExNode<T> head = null;
    private ExNode<T> tail = null;

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

    private ExNode<T> getNode(int index){
        ExNode<T> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }

    public void push(T item){
        if(this.isEmpty()){
            this.head = new ExNode<>(item);
            this.tail = this.head;
            this.size++;
            return;
        }
        ExNode<T> tail = this.tail;
        ExNode<T> newNode = new ExNode<>(item);

        tail.setNext(newNode);
        newNode.setPrev(tail);
        this.tail = newNode;

        this.size++;
    }

    public void pushToTheFront(T item){
        ExNode<T> current = this.head;
        ExNode<T> newNode = new ExNode<>(item);
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
        ExNode<T> newNode = new ExNode<>(item);
        ExNode<T> nodeBefore = this.getNode(index - 1);
        ExNode<T> nodeAfter = nodeBefore.getNext();

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
        ExNode<T> toDelete = getNode(this.size - 1);

        this.tail = this.tail.getPrev();
        this.tail.setNext(null);
        size--;
        return toDelete.getItem();
    }

    public T remove(int index){
        boundCheck(index);

        if(index == 0){
            ExNode<T> toDelete = this.head;
            this.head = this.head.getNext();
            size--;
            return toDelete.getItem();
        }
        if (index == this.size - 1){
            return pop();
        }

        ExNode<T> toBeDeleted = this.getNode(index);
        ExNode<T> after = toBeDeleted.getNext();
        ExNode<T> before = toBeDeleted.getPrev();

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
