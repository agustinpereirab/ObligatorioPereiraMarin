package um.edu.uy.TADs.Heap;

import lombok.Data;
import um.edu.uy.Practico6.Exceptions.EmptyHeapException;
import um.edu.uy.TADs.Node;

import java.util.LinkedList;
import java.util.Queue;

@Data
public class MyBinaryHeapImpl<K extends Comparable<K>,T> implements MyBinaryHeap <K,T> {
    private Node<K, T> root;

    public MyBinaryHeapImpl() {
        this.root = null;
    }



    public void addFinal(Node<K, T> nodeNew) {
        Queue<Node<K, T>> queues = new LinkedList<>();
        queues.add(this.root);

        while (!queues.isEmpty()) {
            Node<K, T> nodeAuxiliar = queues.poll();
            if (nodeAuxiliar.getLeftChild() == null) {
                nodeAuxiliar.setLeftChild(nodeNew);
                nodeNew.setParent(nodeAuxiliar);
                break;
            } else {
                queues.add(nodeAuxiliar.getLeftChild());
            }
            if (nodeAuxiliar.getRightChild() == null) {
                nodeAuxiliar.setRightChild(nodeNew);
                nodeNew.setParent(nodeAuxiliar);
                break;
            } else {
                queues.add(nodeAuxiliar.getRightChild());
            }
        }

    }

    @Override
    public void insert(K key, T value) {
        if (root == null) {
            root = new Node<>(key, value);
        } else {
            Node<K, T> nodeToAdd = new Node<>(key, value);
            addFinal(nodeToAdd);

            while (nodeToAdd.isGreater(nodeToAdd.getParent()) || nodeToAdd.getParent() == null) {

                Node<K, T> parentNode = nodeToAdd.getParent();
                if (parentNode == null) break;
                K keyFather = parentNode.getKey();
                T dataFather = parentNode.getData();

                parentNode.setData(value);
                parentNode.setKey(key);

                nodeToAdd.setData(dataFather);
                nodeToAdd.setKey(keyFather);

                nodeToAdd = parentNode; // esto me sirve

            }

        }


    }

    public void removeFinal() {
        Queue<Node<K, T>> queues = new LinkedList<>();
        queues.add(this.root);
        Node<K, T> nodeToRemove = this.root;

        while (!queues.isEmpty()) {
            Node<K, T> nodeAuxiliar = queues.remove();
            if (nodeAuxiliar.getLeftChild() != null) {
                nodeToRemove = nodeAuxiliar.getLeftChild();
                queues.add(nodeToRemove);


            } else {
                break;
            }
            if (nodeAuxiliar.getRightChild() != null) {
                nodeToRemove = nodeAuxiliar.getRightChild();
                queues.add(nodeToRemove);

            } else {
                break;
            }
        }

            T valueLast = nodeToRemove.getData();
            K keyLast = nodeToRemove.getKey();

            root.setData(valueLast);
            root.setKey(keyLast);

            if (nodeToRemove.getParent().getLeftChild().equals(nodeToRemove)) {
                nodeToRemove.getParent().setLeftChild(null);
            } else {
                nodeToRemove.getParent().setRightChild(null);
            }
        }


    @Override
    public void remove() throws EmptyHeapException {
        if (root == null) { // this.root solo lo pongo si le paso al metodo un parametro q se llame root, asi no genera errores, aca no lo necesito
            throw new EmptyHeapException("Heap is empty");
        }
        if (root.getLeftChild() == null) {
            root = null;
            return;

        }
        removeFinal();

        Node<K, T> nodeGoingDown = this.root;
        while (!nodeGoingDown.isGreater(nodeGoingDown.getLeftChild()) && !nodeGoingDown.isGreater(nodeGoingDown.getRightChild())) {
            if (nodeGoingDown.getLeftChild().isGreater(nodeGoingDown.getRightChild())) {
                T valueBiggerChild = nodeGoingDown.getLeftChild().getData();
                K keyBiggerChild = nodeGoingDown.getLeftChild().getKey();

                nodeGoingDown.getLeftChild().setData(nodeGoingDown.getData());// aca no pongo setLeftChild pq ahi si tendria el mismo nodo dos veces, entonces solo cambio la info
                nodeGoingDown.getLeftChild().setKey(nodeGoingDown.getKey());

                nodeGoingDown.setKey(keyBiggerChild);
                nodeGoingDown.setData(valueBiggerChild); // son nodos distintos con mismos valores, por ende puedo acceder a ellos sin pensr q accedo a los dos al mismo tiwmpo

                nodeGoingDown = nodeGoingDown.getLeftChild();

            } else {
                T valueBiggerChild = nodeGoingDown.getRightChild().getData();
                K keyBiggerChild = nodeGoingDown.getRightChild().getKey();

                nodeGoingDown.getRightChild().setData(nodeGoingDown.getData());// aca no pongo setLeftChild pq ahi si tendria el mismo nodo dos veces, entonces solo cambio la info
                nodeGoingDown.getRightChild().setKey(nodeGoingDown.getKey());

                nodeGoingDown.setKey(keyBiggerChild);
                nodeGoingDown.setData(valueBiggerChild); // son nodos distintos con mismos valores, por ende puedo acceder a ellos sin pensr q accedo a los dos al mismo tiwmpo

                nodeGoingDown = nodeGoingDown.getRightChild();

            }

        }
    }

    public int countLevels(int amountOfelements) {
        return (int) ((Math.log10(amountOfelements) / (Math.log10(2))) + 1);
    }

    public String repeatSpaces(int amountSpaces) {
        String spaces = "";
        for (int count = 0; count < amountSpaces; count++) {
            spaces += " ";
        }
        return spaces;
    }


    @Override
    public String toString() {
        if (root == null) {
            return "";
        }
        Queue<Node<K, T>> queuesOfElements = new LinkedList<>();
        queuesOfElements.add(root);
        LinkedList<Node<K, T>> nodesOfElements = new LinkedList<>();
        nodesOfElements.add(root);
        int amountOfElements = 1;
        while (!queuesOfElements.isEmpty()) {
            Node<K, T> nodeAuxiliar = queuesOfElements.poll();
            if (nodeAuxiliar.getLeftChild() == null) {
                break;
            } else {
                queuesOfElements.add(nodeAuxiliar.getLeftChild());
                amountOfElements++;
                nodesOfElements.add(nodeAuxiliar.getLeftChild());
            }
            if (nodeAuxiliar.getRightChild() == null) {
                break;

            } else {
                queuesOfElements.add(nodeAuxiliar.getRightChild());
                amountOfElements++;
                nodesOfElements.add(nodeAuxiliar.getRightChild());
            }

        }
        int amountOfLevels = countLevels(amountOfElements);
        String result = "";
        int numberOfActualNode = 0;

        for (int actualLevel = 0; actualLevel < amountOfLevels; actualLevel++) {
            int nodesInThisLevel = (int) Math.pow(2, actualLevel);
            int beforeSpaces = (int) Math.pow(2, amountOfLevels - actualLevel - 1) - 1;
            int betweenSpaces = (int) Math.pow(2, amountOfLevels - actualLevel ) - 1;

            result += repeatSpaces(beforeSpaces); // me recomienda hacer un string builder pero creo q no nos dejas

            for (int actualNode = 0; actualNode < nodesInThisLevel && numberOfActualNode < nodesOfElements.size(); actualNode++) {
                Node<K, T> node = nodesOfElements.get(numberOfActualNode++);
                result +=  node.getData();

                if ((numberOfActualNode == nodesOfElements.size())) {
                    break;
                }

                if (!(actualNode == nodesInThisLevel - 1)){
                    result += repeatSpaces(betweenSpaces);
                }

            }
            if (!(actualLevel == amountOfLevels - 1)) {
                result += "\n";
            }
             // con esto bajo de linea
        }
        return result;

    }

}


/* Probamos unas funciones para ver los espacios necesarios

Espacio inicial = 2^(h - i ) - 1 -->  h es altura, i es nivel actual
Espacio entre nodos = 2^(h - i + 1) - 1

                               d
               f                               g
       a               b               c               g
   d       b       d       f       f       f       g       t
 f   g   n   n   m   n   m   n   m   n   m   n   m   n   m   n

 d
f t
        */
