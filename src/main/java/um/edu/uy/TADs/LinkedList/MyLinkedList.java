package um.edu.uy.TADs.LinkedList;

public interface MyLinkedList<T> {

    void add(Node<T> Node);

    void remove(int position);

    T get(int position);

    int size();

    boolean contains(Node<T> Node);

}
