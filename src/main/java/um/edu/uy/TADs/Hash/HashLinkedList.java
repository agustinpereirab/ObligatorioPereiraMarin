package um.edu.uy.TADs.Hash;

import um.edu.uy.TADs.Hash.Exceptions.ElementAlreadyExistsException;
import um.edu.uy.TADs.LinkedList;
import um.edu.uy.TADs.MyLinkedListImpl;

public class HashLinkedList implements HashTable {
    private int size;
    private LinkedList<Entry>[] hashTable;

    public HashLinkedList(int size) {
        this.size = size;
        this.hashTable = new MyLinkedListImpl[size];

        for (int place = 0; place < size; place++) {
            hashTable[place] = new MyLinkedListImpl<>();
        }
    }

    @Override
    public void insert(String key, Object value) throws ElementAlreadyExistsException {
        int position = Math.abs((key.hashCode())) % size; // el hash puede dar negativo sino uso abs
        LinkedList<Entry> list = hashTable[position]; // la lista que hay en esa posicion

        for (Entry entry : list) { // las entradas en esa lista
            if (entry.getKey().equals(key)) {
                throw new ElementAlreadyExistsException();
            }
        }

        list.add(new Entry(key, value));

    }

    @Override
    public boolean belongs(String key) {
        int position = Math.abs((key.hashCode())) % size;
        LinkedList<Entry> list = hashTable[position]; // la lista que hay en esa posicion

        for (Entry entry : list) { // las entradas en esa lista
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(String key) {
        if (belongs(key)) {
            int position = Math.abs((key.hashCode())) % size;
            int positionList = 0;
            LinkedList<Entry> list = hashTable[position];
            for (Entry entry : list) {
                if (entry.getKey().equals(key)) {
                    list.remove(positionList);
                    break;


                }
                positionList++;
            }
        }

    }
}
