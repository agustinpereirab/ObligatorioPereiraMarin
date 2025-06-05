package um.edu.uy.TADs.Heap;


import um.edu.uy.Practico6.Exceptions.EmptyHeapException;

public interface MyBinaryHeap <K extends Comparable<K>,T>  { //quiero comparar las k entonces pongo el comparable
    void insert(K key, T value); // creo una nueva hoja, le paso toda su info
    void remove() throws EmptyHeapException;// saco la raiz entonces no tengo q pasarle nada

}
