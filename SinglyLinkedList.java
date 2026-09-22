import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    private static class OrderedNodesEntry<E extends Comparable<E>> implements Comparable<OrderedNodesEntry<E>> {
        private Node<E> pred;
        private Node<E> node;

        OrderedNodesEntry(Node<E> pred, Node<E> node) {
            this.pred = pred;
            this.node = node;
        }

        public Node<E> getPred() {
            return pred;
        }

        public Node<E> getNode() {
            return node;
        }

        public int compareTo(OrderedNodesEntry<E> o) {
            return getNode().getElement().compareTo(o.getNode().getElement());
        };
    }

    // write your codes here
    public void swap(){
        if (size <= 1) return;

        TreeSet<OrderedNodesEntry<E>> orderedNodes = new TreeSet<>();
        HashMap<Node<E>, Node<E>> swappedNodes = new HashMap<>();
        Node<E> ptr = head;
        Node<E> ptrPred = null;
        while (ptr != null) {
            orderedNodes.add(new OrderedNodesEntry<>(ptrPred, ptr));
            ptrPred = ptr;
            ptr = ptr.getNext();
        }
        
        do {
            OrderedNodesEntry<E> minPtr = orderedNodes.first();
            OrderedNodesEntry<E> maxPtr = orderedNodes.last();
            orderedNodes.remove(minPtr);
            orderedNodes.remove(maxPtr);
            
            Node<E> min = minPtr.getNode();
            Node<E> minPred = swappedNodes.getOrDefault(minPtr.getPred(), minPtr.getPred());
            Node<E> minNext = min.getNext();

            Node<E> max = maxPtr.getNode();
            Node<E> maxPred = swappedNodes.getOrDefault(maxPtr.getPred(), maxPtr.getPred());
            Node<E> maxNext = max.getNext();

            if (maxPred == null) head = min; else maxPred.setNext(min);
            min.setNext(maxNext == min ? max : maxNext);
            if (maxNext == null) tail = min;

            if (minPred == null) head = max; else minPred.setNext(max);
            max.setNext(minNext == max ? min : minNext);
            if (minNext == null) tail = max;

            swappedNodes.put(min, max);
            swappedNodes.put(max, min);
            
        } while (orderedNodes.size() > 1);
    }
   
}

