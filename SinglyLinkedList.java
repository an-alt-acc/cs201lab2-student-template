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

    // write your codes here
    public void swap(){
        if (size <= 1) return;

        HashSet<E> visited = new HashSet<>(size);
        while (visited.size() < size - 1) {
            Node<E> min = null;
            Node<E> minPred = null;
            Node<E> max = null;
            Node<E> maxPred = null;
            Node<E> ptr = head;
            Node<E> ptrPred = null;
            while (ptr != null) {
                if (visited.contains(ptr.getElement())) {
                    ptrPred = ptr;
                    ptr = ptr.getNext();
                    continue;
                }
                if (min == null || ptr.getElement().compareTo(min.getElement()) < 0) {
                    min = ptr;
                    minPred = ptrPred;
                }
                if (max == null || ptr.getElement().compareTo(max.getElement()) > 0) {
                    max = ptr;
                    maxPred = ptrPred;
                }
                ptrPred = ptr;
                ptr = ptr.getNext();
            }

            Node<E> minNext = min.getNext();
            Node<E> maxNext = max.getNext();
            if (maxPred == null) head = min; else maxPred.setNext(min);
            min.setNext(maxNext == min ? max : maxNext);
            if (maxNext == null) tail = min;
            if (minPred == null) head = max; else minPred.setNext(max);
            max.setNext(minNext == max ? min : minNext);
            if (minNext == null) tail = max;
            visited.add(min.getElement());
            visited.add(max.getElement());
        }
    }
   
}

