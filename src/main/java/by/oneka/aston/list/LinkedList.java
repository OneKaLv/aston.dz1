package by.oneka.aston.list;

import by.oneka.aston.Exceptions;

import java.util.Comparator;

/**
 * Double-linked list with recursive QuickSort.
 *
 * @param <E> the Type of object in this list
 * @author Ivan Batskalevich
 */
//TODO LinkedList::get
public class LinkedList<E> {
    /**
     * Contains head of list.
     */
    private Node<E> head;
    /**
     * Contains size of list.
     */
    private int size = 0;


    /**
     * Appends the specified object to the start of this list.
     *
     * @param object - to be appended to list.
     */
    public void addFirst(E object) {
        Node<E> firstNode = new Node<>(object);
        head.previous = firstNode;
        firstNode.next = head;
        head = firstNode;
        size++;
    }

    /**
     * Appends the specified object to the end of this list.
     *
     * @param object - to be appended to list.
     */
    public void addLast(E object) {
        if (head == null) {
            head = new Node<>(object);
            return;
        }

        Node<E> currentNode = head;
        while (currentNode.next != null)
            currentNode = currentNode.next;
        Node<E> lastNode = new Node<>(object);
        currentNode.next = lastNode;
        lastNode.previous = currentNode;
        size++;
    }

    public boolean add(E object) {
        addLast(object);
        return true;
    }

    /**
     * Getting object from index position.
     *
     * @param index - index of object position.
     * @return the object at the index position
     * @throws IndexOutOfBoundsException - if index is wrong
     */
    public E get(int index) {
        Exceptions.checkBounds(index, size);

        Node<E> currentNode = head;
        for (int i = 0; i < index; i++)
            currentNode = currentNode.next;
        return currentNode.value;
    }

    /**
     * Removes all the objects from list.
     */
    public void clear() {
        head = null;
    }

    /**
     * Getting index from object position.
     *
     * @param object - findable object.
     * @return the index of object position
     */
    public int get(E object) {
        if (head == null || head == object)
            return head == null ? -1 : 0;

        Node<E> currentNode = head;
        int index = 0;
        while (currentNode.next != null) {
            index++;
            if (currentNode.next.value == object)
                return index;
            currentNode = currentNode.next;
        }
        return -1;
    }

    /**
     * Removes the object at the specified position in this list.
     * Shifts any subsequent objects to the left.
     *
     * @param object - object to be removed
     */
    public void remove(E object) {
        if (head == null)
            return;
        if (head.value == object)
            head = head.next;

        Node<E> currentNode = head;
        while (currentNode.next != null) {
            if (currentNode.next.value == object) {
                Node<E> removedNode = currentNode.next;
                Node<E> nextRemovedNode = removedNode.next;
                nextRemovedNode.previous = removedNode.previous;
                removedNode.previous.next = nextRemovedNode;
                break;
            }
            currentNode = currentNode.next;
        }
        size--;
    }

    /**
     * Replaces the object at the position in this list with the new object.
     *
     * @param index  - position of object
     * @param object - to be stored
     * @return the object previously at the specified position
     * @throws IndexOutOfBoundsException - if index is wrong
     */
    public E set(E object, int index) {
        Exceptions.checkBounds(index, size);
        Node<E> currentNode = head;
        for (int i = 0; i < index; i++)
            currentNode = currentNode.next;
        Node<E> newNode = new Node<>(object);
        newNode.next = currentNode.next;
        newNode.previous = currentNode.previous;
        currentNode.next.previous = newNode;
        currentNode.previous.next = newNode;
        return currentNode.value;
    }

    /**
     * Sorts the list according to the order induced by the comparator.
     *
     * @param comparator - used to compare list objects
     * @throws NullPointerException - if the list contains (@Code null) objects
     */
    public void sort(Comparator<? super E> comparator) {
        Node<E> last = getLastNode(head);
        quickSort(head, last, comparator);
    }

    /**
     * A utility function to find last node of linked list
     */
    private Node<E> getLastNode(Node<E> node) {
        while (node.next != null)
            node = node.next;
        return node;
    }

    /**
     * Considers last element as pivot, places the pivot element at its
     * correct position in sorted array, and places all smaller (smaller than
     * pivot) to left of pivot and all greater elements to right of pivot
     */
    private Node<E> partition(Node<E> head, Node<E> last, Comparator comparator) {
        E x = last.value;
        Node<E> i = head.previous;

        for (Node<E> j = head; j != last; j = j.next)
            if (comparator.compare(j.value, x) <= 0) {
                i = (i == null) ? head : i.next;
                E temp = i.value;
                i.value = j.value;
                j.value = temp;
            }
        i = (i == null) ? head : i.next;
        E temp = i.value;
        i.value = last.value;
        last.value = temp;
        return i;
    }

    /**
     * The main function to sort a linked list.
     */
    private void quickSort(Node<E> head, Node<E> last, Comparator comparator) {
        if (last != null && head != last && head != last.next) {
            Node<E> temp = partition(head, last, comparator);
            quickSort(head, temp.previous, comparator);
            quickSort(temp.next, last, comparator);
        }
    }


    @Override
    public String toString() {
        return "LinkedList{" +
                "head=" + head +
                '}';
    }

    /**
     * A node of the doubly linked list
     * @param <E> the Type of object in this node
     */
    private static class Node<E> {
        private E value;
        private Node<E> next;
        private Node<E> previous;

        public Node(E value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    (next == null ? "" : ", next=" + next) +
                    '}';
        }
    }
}

