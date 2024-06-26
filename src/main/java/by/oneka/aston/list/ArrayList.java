package by.oneka.aston.list;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Resizable ArrayList with QuickSort based on array.
 * @param <E> the Type of object in this list
 * @author Ivan Batskalevich
 */
public class ArrayList<E> implements Iterable<E> {

    private E[] array;
    private int lastPosition = 0;

    /**
     * Constructs an empty list.
     */
    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.array = (E[]) new Object[0];
    }

    /**
     * Returns the number of objects in this list.
     *
     * @return - the number of objects in this list.
     */
    public int getSize() {
        return this.array.length;
    }

    /**
     * Appends the specified object to the end of this list.
     *
     * @param object - to be appended to list.
     */
    public void add(E object) {
        if (lastPosition >= getSize())
            expandArray();
        array[lastPosition] = object;
        lastPosition++;
    }

    /**
     * Inserts the object at the specified position in list.
     * Shifts the object currently at that position to the right.
     * If insert in last position of the list. Adding new object to end of list.
     *
     * @param object - to be inserted
     * @param index  - position to insertion
     *
     * @throws IndexOutOfBoundsException - if index is wrong
     */
    public void add(E object, int index) {
        if (index == lastPosition) {
            add(object);
            return;
        }
        checkBounds(index);
        if (lastPosition + 1 >= array.length)
            expandArray();
        System.arraycopy(array, index, array, index + 1, lastPosition - index);
        array[index] = object;
        lastPosition++;
    }

    /**
     * Removes the object at the specified position in this list.
     * Shifts any subsequent objects to the left.
     *
     * @param index - of the object to be removed
     * @throws IndexOutOfBoundsException - if index is wrong
     */
    public void remove(int index) {
        checkBounds(index);
        System.arraycopy(array, index + 1, array, index, lastPosition - index - 1);
        reduceArray();
        lastPosition--;
    }

    /**
     * Removes the first occurrence of the object from this list.
     *
     * @param obj - object to be removed from this list, if present;
     * @return - {@code true} if this list contained the specified object
     */
    public boolean remove(E obj) {
        return Arrays.asList(array).remove(obj);
    }

    /**
     * Replaces the object at the position in this list with the new object.
     *
     * @param index - position of object
     * @param obj   - to be stored
     * @return the object previously at the specified position
     * @throws IndexOutOfBoundsException - if index is wrong
     */
    public E set(E obj, int index) {
        checkBounds(index);
        E lastObj = array[index];
        array[index] = obj;
        return lastObj;
    }

    /**
     * Sorts the list according to the order induced by the comparator.
     *
     * @param comparator - used to compare list objects
     * @throws NullPointerException - if the list contains (@Code null) objects
     */
    public void sort(Comparator<? super E> comparator) {
        quickSort(0, lastPosition - 1, comparator);
    }

    /**
     * Removes all the objects from this list.
     */
    public void clear() {
        Arrays.fill(array, null);
        lastPosition = 0;
    }


    /**
     * Checks whether object exist in a list
     *
     * @param obj - the object being checked
     */
    public boolean contains(E obj) {
        return Arrays.stream(array).anyMatch(object -> object.equals(obj));
    }

    /**
     * Realisation of QuickSort.
     *
     * @param low        - bound of array
     * @param high       - bound of array
     * @param comparator - used to compare list objects
     * @throws NullPointerException - if the list contains (@Code null) objects
     */
    private void quickSort(int low, int high, Comparator comparator) {
        if (low >= high)
            return;
        int mid = low + (high - low) / 2;
        E opor = array[mid];

        int leftBound = low;
        int rightBound = high;
        while (leftBound <= rightBound) {
            while (comparator.compare(array[leftBound], opor) < 0)
                leftBound++;
            while (comparator.compare(array[rightBound], opor) > 0)
                rightBound--;
            if (leftBound <= rightBound) {
                E temp = array[leftBound];
                array[leftBound] = array[rightBound];
                array[rightBound] = temp;
                leftBound++;
                rightBound--;
            }
        }
        if (low < rightBound)
            quickSort(low, rightBound, comparator);
        if (high > leftBound)
            quickSort(leftBound, high, comparator);
    }

    /**
     * Expand base array.
     * Create the new array with capacity of old array+1. Copy all objects to the new array.
     */
    @SuppressWarnings("unchecked")
    private void expandArray() {
        long newCapacity = (long) array.length + 1;

        E[] newArray = (E[]) new Object[(int) newCapacity];
        System.arraycopy(this.array, 0, newArray, 0, array.length);
        this.array = newArray;
    }

    /**
     * Reduce base array.
     * Create the new array with capacity of old array-1. Copy all objects to the new array.
     */
    @SuppressWarnings("unchecked")
    private void reduceArray() {
        long newCapacity = (long) array.length - 1;

        E[] newArray = (E[]) new Object[(int) newCapacity];
        System.arraycopy(this.array, 0, newArray, 0, array.length - 1);
        this.array = newArray;
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= lastPosition)
            throw new IndexOutOfBoundsException(String.format("index: %d, size: %d", index, lastPosition));
    }

    @Override
    public Iterator<E> iterator() {
        return Arrays.stream(array).iterator();
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(array, 0, lastPosition));
    }
}
