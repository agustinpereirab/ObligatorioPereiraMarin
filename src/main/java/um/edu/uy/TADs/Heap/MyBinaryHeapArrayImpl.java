package um.edu.uy.TADs.Heap;

import lombok.Data;
import um.edu.uy.Practico6.Exceptions.EmptyHeapException;


@Data
public class MyBinaryHeapArrayImpl<K extends Comparable<K>,T> implements MyBinaryHeapArray<K,T> {
    private Element<K, T>[] heap;
    boolean isMax;

    @SuppressWarnings("unchecked") // no me cambia nada pero sino en los test me da una nota de que tengo algo que no lo chequeo esta sentencia le avisa que lo se y no importa
    public MyBinaryHeapArrayImpl(boolean isMax) {
        heap = (Element<K,T>[]) new Element[127]; // se usara de tamaño fijo "127" arbitrario
        this.isMax = isMax;
    }

    @Override
    public void insert(K key, T value) {

        Element<K, T> newElement = new Element<>(key, value);
        if (heap[0] == null) { // no pongo isEmpty pq asi me recorretodo el array y yo ya se q si la posicion 0 es null ya esta vacio el array y solo se fija ene l primero
            heap[0] = newElement;
            return;
        }

        int lastPosition = 0;

        for (int position = 0; position < heap.length; position++) {
            if (heap[2 * position + 1] == null) { // vas viendo los hijos, el momento q encuentres el primer null ahu agrego
                heap[2 * position + 1] = newElement;
                lastPosition = 2 * position + 1;
                break;
            } else if (heap[2 * position + 2] == null) {
                heap[2 * position + 2] = newElement;
                lastPosition = 2 * position + 2;
                break;

            }

        }
        if (isMax) { // si isMax == true
            while (lastPosition > 0) { // siempre voy a entrar
                int parentPosition = (int) (lastPosition - 1) / 2;

                Element<K, T> parent = heap[parentPosition];
                Element<K, T> current = heap[lastPosition];

                if (current.isGreater(parent)) {

                    heap[parentPosition] = new Element<>(current.getKey(), current.getValue());
                    heap[lastPosition] = new Element<>(parent.getKey(), parent.getValue());



                    lastPosition = parentPosition;// subo

                } else {
                    break;
                }
            }

        } else {
            while (lastPosition > 0) {
                int parentPosition = (lastPosition - 1) / 2;

                Element<K, T> parent = heap[parentPosition];
                Element<K, T> current = heap[lastPosition];

                if (!current.isGreater(parent)) {
                    heap[parentPosition] = new Element<>(current.getKey(), current.getValue());
                    heap[lastPosition] = new Element<>(parent.getKey(), parent.getValue());
                    lastPosition = parentPosition;
                } else {
                    break;
                }
            }
        }
    }

    @Override
    public T remove() throws EmptyHeapException {
        if (heap[0] == null) {
            throw new EmptyHeapException("Heap is empty");
        }

        T dataFirst = heap[0].getValue();

        int lastPosition = heap.length - 1;
        while (lastPosition > 0 && heap[lastPosition] == null) {
            lastPosition--;
        }

        Element<K, T> lastElement = heap[lastPosition];
        heap[lastPosition] = null;
        heap[0] = new Element<>(lastElement.getKey(), lastElement.getValue());

        int currentPosition = 0;

        while (true) {
            int sonLeftPosition = 2 * currentPosition + 1;
            int sonRightPosition = 2 * currentPosition + 2;

            // si no hay hijo izquierdo, termino
            if (sonLeftPosition >= heap.length || heap[sonLeftPosition] == null) break;

            boolean hasRight = sonRightPosition < heap.length && heap[sonRightPosition] != null;

            if (isMax) {
                if (
                        (hasRight && heap[sonRightPosition].isGreater(heap[sonLeftPosition]) &&
                                heap[sonRightPosition].isGreater(heap[currentPosition]))
                ) {
                    Element<K, T> temp = heap[currentPosition];
                    heap[currentPosition] = new Element<>(heap[sonRightPosition].getKey(), heap[sonRightPosition].getValue());
                    heap[sonRightPosition] = new Element<>(temp.getKey(), temp.getValue());
                    currentPosition = sonRightPosition;

                } else if (
                        heap[sonLeftPosition].isGreater(heap[currentPosition])
                ) {
                    Element<K, T> temp = heap[currentPosition];
                    heap[currentPosition] = new Element<>(heap[sonLeftPosition].getKey(), heap[sonLeftPosition].getValue());
                    heap[sonLeftPosition] = new Element<>(temp.getKey(), temp.getValue());
                    currentPosition = sonLeftPosition;
                } else {
                    break;
                }
            } else {
                if (
                        (hasRight && !heap[sonRightPosition].isGreater(heap[sonLeftPosition]) &&
                                !heap[sonRightPosition].isGreater(heap[currentPosition]))
                ) {
                    Element<K, T> temp = heap[currentPosition];
                    heap[currentPosition] = new Element<>(heap[sonRightPosition].getKey(), heap[sonRightPosition].getValue());
                    heap[sonRightPosition] = new Element<>(temp.getKey(), temp.getValue());
                    currentPosition = sonRightPosition;

                } else if (
                        !heap[sonLeftPosition].isGreater(heap[currentPosition])
                ) {
                    Element<K, T> temp = heap[currentPosition];
                    heap[currentPosition] = new Element<>(heap[sonLeftPosition].getKey(), heap[sonLeftPosition].getValue());
                    heap[sonLeftPosition] = new Element<>(temp.getKey(), temp.getValue());
                    currentPosition = sonLeftPosition;
                } else {
                    break;
                }
            }
        }

        return dataFirst;
    }


    @Override
    public int sizeOfArrays() {
        for (int amountOfElements = 0; amountOfElements < heap.length; amountOfElements++) {
            if (heap[amountOfElements] == null) {
                return amountOfElements;
            }
        }
        return heap.length;
    }

    public String repeatSpaces(int count) {
        StringBuilder spaces = new StringBuilder();
        for (int amountSpaces = 0; amountSpaces < count; amountSpaces++) {
            spaces.append(" ");
        }
        return spaces.toString();
    }

    public int countLevels(int amountOfelements) {
        return (int) (Math.log10(amountOfelements) / (Math.log10(2)) + 1);
    }

    @Override
    public String toString() {

        StringBuilder heapConstructor = new StringBuilder(); // creo q aprendi a usarlo
        int amountOfLevels = countLevels(sizeOfArrays());
        int numberOfActualElement = 0;

        for (int actualLevel = 0; actualLevel < amountOfLevels; actualLevel++) {
            int elementsInThisLevel = (int) Math.pow(2, actualLevel);
            int beforeSpaces = (int) Math.pow(2, amountOfLevels - actualLevel - 1) - 1;
            int betweenSpaces = (int) Math.pow(2, amountOfLevels - actualLevel) - 1;

            heapConstructor.append(repeatSpaces(beforeSpaces));

            for (int actualElement = 0; actualElement < elementsInThisLevel && numberOfActualElement < sizeOfArrays(); actualElement++) {
                Element<K, T> element = heap[numberOfActualElement++];
                if (element.getValue() != null) {
                    heapConstructor.append(element.getKey());
                }


                if ((numberOfActualElement == heap.length)) {
                    break;
                }

                if (!(actualElement == elementsInThisLevel - 1)) {
                    heapConstructor.append(repeatSpaces(betweenSpaces));
                }
            }
            if (!(actualLevel == amountOfLevels - 1)) {
                heapConstructor.append("\n"); // con esto bajo de linea
            }
        }
        return heapConstructor.toString();

    }
}
