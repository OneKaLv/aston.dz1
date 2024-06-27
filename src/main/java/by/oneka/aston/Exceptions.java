package by.oneka.aston;

/**
 * Utility class for exeptions.
 * @author Ivan Batskalevich
 */
public final class Exceptions {

    private Exceptions() { throw new UnsupportedOperationException(); }

    /**
     * Checking if index did not exceed list size
     *
     * @param index - index of checking object
     * @param size  - size of checking list
     * @throws IndexOutOfBoundsException if index bigger or equals size list
     */
    public static void checkBounds(int index, int size) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(String.format("index: %d, size: %d", index, size));
    }
}
