package myJava.utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.RandomAccess;

@SuppressWarnings("unchecked")
public class MySortedArrayList <T> implements RandomAccess, Iterable<T>{
    private int size;

    //Initial List of elements
    private T[] arrayElements;

    private final int INITIAL_CAPACITY = 10;

    private final Comparator<T> cmp;

    MySortedArrayList(Comparator<T> cmp){
        this.arrayElements = (T []) new Object[INITIAL_CAPACITY];
        this.size = 0;
        this.cmp = cmp;
    }

    private void resize(){
        this.arrayElements = Arrays.copyOf(arrayElements, this.arrayElements.length + (INITIAL_CAPACITY * 2));
    }

    public void add(T item){
        if (isEmpty()){
            this.arrayElements[0] = item;
            this.size++;
            return;
        }
        if (this.size + 1 > INITIAL_CAPACITY) {
            resize();
        }
        int index = binarySearchToAdd(item);
        shiftArrayRight(index);
        this.arrayElements[index] = item;
        size++;
    }

    public T pop (){
        if (this.isEmpty()){
            throw new IndexOutOfBoundsException("List is empty");
        }

        T remove = this.arrayElements[size - 1];
        this.arrayElements[size - 1] = null;
        this.size--;
        return remove;
    }

    public T remove(int index){
        boundCheck(index);

        if (index == this.size - 1){
            return pop();
        }
        T remove = this.arrayElements[index];
        shiftArrayLeft(index);
        this.arrayElements[size - 1] = null;
        this.size--;
        return remove;
    }

    public T remove(T item){
        int index = Arrays.binarySearch(this.arrayElements, item);
        if(index == -1) return null;

        return remove(index);
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void addFromArray(T[] array){
        for (T element : array){
            add(element);
        }
    }

    public void removeAll(){
        this.size = 0;
        this.arrayElements = (T []) new Object[INITIAL_CAPACITY];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>(){
            private int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < arrayElements.length && arrayElements[currentIndex] != null;
            }

            @Override
            public T next() {
                return arrayElements[currentIndex++];
            }
        };
    }

    private void shiftArrayRight(int index){
        if (index == size) return;
        for (int i = size - 1; i >= index; i--) {
            this.arrayElements[i + 1] = this.arrayElements[i];
        }
    }

    private void shiftArrayLeft(int index){
        for (int i = index + 1; i < size; i++) {
            this.arrayElements[i - 1] = this.arrayElements[i];
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

    private int binarySearchToAdd(T item){
        if (cmp.compare(item, this.arrayElements[0]) <= 0) return 0;
        if (cmp.compare(item, this.arrayElements[this.size - 1]) >= 0) return this.size;

        int left = 0;
        int right = this.size - 1;

        while(left < right){
            int middle = left + (right - left) / 2;

            if (this.arrayElements[middle].equals(item)) return middle;

            if (cmp.compare(item, this.arrayElements[middle]) > 0) {
                if (middle + 1 <= right && cmp.compare(item, this.arrayElements[middle + 1]) < 0)
                    return middle + 1;
                left = middle + 1;
            }
            else {
                if (middle - 1 >= left && cmp.compare(item, this.arrayElements[middle - 1]) > 0)
                    return middle;
                right = middle - 1;
            }
        }
        return left;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(arrayElements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}



