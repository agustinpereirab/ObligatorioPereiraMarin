package um.edu.uy.TADs.LinkedList;

import java.util.Iterator;


public class MyLinkedListImpl<T> implements MyLinkedList<T>, Iterable<T> {

    private Node<T> head;
    private int size;

    public MyLinkedListImpl(Node<T> head) {
        this.head = head;
        if (this.head == null) {
            this.size = 0;
        } else {
            this.size = 1;
        }
    }
    public MyLinkedListImpl() {
        this.head = null;
        this.size = 0;

    }

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void add(Node<T> Node) {

        if (isEmpty()) {
            setHead(Node);
            setSize(getSize() + 1);
            return;
        }

        Node<T> traverse = getHead();

        while(traverse.getNext() != null){
            traverse = traverse.getNext();
        }

        traverse.setNext(Node);
        setSize(getSize() + 1);
    }

    @Override
    public void remove(int position) {

        int pos = 0;
        Node<T> traverse = getHead();

        while(traverse.getNext() != null && pos < position){

            traverse = traverse.getNext();
            pos ++;

            if(pos == (position - 1)){
                traverse.setNext(traverse.getNext().getNext()); //me salteo el de posicion "position"
            }
        }
    }

    @Override
    public T get(int position) {

        int pos = 0;
        Node<T> traverse = getHead();

        while (traverse.getNext() != null && pos < position) {

            traverse = traverse.getNext();
            pos++;
            if(pos == position - 1) return traverse.getNext().getData();

        } return null;
    }

    @Override
    public int size() {
        return getSize();
    }

    @Override
    public boolean contains(Node<T> Valor) {

        if(isEmpty()) return false;

        Node<T> traverse = getHead();

        while(traverse.getNext() != null){
            if(traverse == Valor) return true;
            traverse = traverse.getNext();
        }

        return false;
    }

    private boolean isEmpty() {
        return getHead() == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T value = current.getData();
                current = current.getNext();
                return value;
            }
        };

    }
}