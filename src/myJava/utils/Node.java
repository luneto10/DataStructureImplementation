package myJava.utils;

public class Node <T> {

    private Node<T> next = null;
    private Node<T> prev = null;
    private T item;

    public Node(T item){
        this.item = item;
    }

    public T getItem(){
        return item;
    }

    public Node<T> getNext(){
        return this.next;
    }

    public Node<T> getPrev(){
        return this.prev;
    }

    public void setNext(Node<T> next){
        this.next = next;
    }

    public void setPrev(Node<T> prev){
        this.prev = prev;
    }
}
