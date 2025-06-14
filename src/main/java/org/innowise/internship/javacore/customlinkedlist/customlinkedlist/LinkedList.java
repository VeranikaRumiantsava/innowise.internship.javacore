package org.innowise.internship.javacore.customlinkedlist.customlinkedlist;

public class LinkedList<T>{
    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    //1. size()
    public int size() {
        return this.size;
    }

    //2. addFirst()
    public void addFirst(T element) {
        Node<T> newNode = new Node<>(element);

        if (head == null) {
            head = tail = newNode;
        }
        else {
            head.setPrev(newNode);
            newNode.setNext(head);
            head = newNode;
        }
        ++size;
    }

    //3. addLast()
    public void addLast(T element) {
        Node<T> newNode = new Node<>(element);

        if (tail == null) {
            head = tail = newNode;
        }
        else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        ++size;
    }

    //4. add()
    public void add(int index, T element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        if (index == 0){
            addFirst(element);
            return;
        }

        if (index == this.size){
            addLast(element);
            return;
        }

        Node<T> current = getNode(index-1);
        Node<T> newNode = new Node<>(element);

        newNode.setNext(current.getNext());
        newNode.setPrev(current);
        current.setNext(newNode);
        ++size;
    }

    //5. getFirst()
    public T getFirst() {
        return (head == null) ? null : this.head.getItem();
    }

    //6. getLast()
    public T getLast() {
        return (head == null) ? null : this.tail.getItem();
    }

    //7. get(index)
    //половинить?
    public T get(int index) {
        return getNode(index).getItem();
    }

    private Node<T> getNode(int index){
        if (index<0 || index>=this.size)
            throw new IndexOutOfBoundsException();

        if (index==0)
            return this.head;

        Node<T> current;
        int counter;

        if (index<this.size/2){
            counter = 0;
            current = this.head;
            while (counter < index) {
                current = current.getNext();
                ++counter;
            }
        }
        else {
            counter = size-1;
            current = this.tail;
            while (counter > index) {
                current = current.getPrev();
                --counter;
            }
        }


        return current;
    }

    //8. removeFirst()
    public void removeFirst(){
        if (head == null)
            return;

        if (head.getNext() == null){
            head = null;
            tail = null;
        }
        else {
            head = head.getNext();
            head.setPrev(null);
        }
        --size;
    }

    //9. removeLast()
    public void removeLast(){
        if (head == null)
            return;

        if (tail.getPrev() == null){
            head = null;
            tail = null;
        }
        else {
            tail = tail.getPrev();
            tail.setNext(null);
        }
        --size;
    }

    //10. remove(index)
    public void remove(int index){
        if (index<0 || index>=this.size)
            throw new IndexOutOfBoundsException();

        if (index==0) {
            removeFirst();
            return;
        }

        if (index == this.size-1) {
            removeLast();
            return;
        }

        Node<T> current = this.getNode(index);

        current.getPrev().setNext(current.getNext());
        current.getNext().setPrev(current.getPrev());
        --size;
    }

    public boolean isEmpty(){
        return head==null;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            str.append(current.getItem().toString()).append(" ");
            current = current.getNext();
        }
        return str.toString();
    }
}
