package um.edu.uy.TADs.Heap;

import um.edu.uy.Practico6.Exceptions.EmptyHeapException;

public interface MyBinaryHeapArray <K extends Comparable<K>,T> {
    void insert(K key, T value);
    T remove() throws EmptyHeapException;
    int sizeOfArrays();

}
