import java.util.NoSuchElementException;

public class OldRedo<E> {
    private Node head;
    private Node tail;
    private Node pointer;
    private int size;
    private boolean shouldRefreshSize;


    private class Node {
        E element;
        Node next = null;
        Node prev = null;

        Node(E element) {
            this.element = element;
        }
    }

    /**
     * Adds an element to the list
     */
    public void add(E element) {
        Node newNode = new Node(element);
        if (head == null) {
            head = newNode;
        } else {
            shouldRefreshSize = pointer.next != null;
            pointer.next = newNode;
            newNode.prev = pointer;
        }

        pointer = newNode;
        tail = newNode;
        if (shouldRefreshSize) {
            refreshSize();
        } else {
            size++;
        }
    }

    /**
     * @return The current element which is pointed at
     */
    public E getCurrent() {
        if (pointer == null) {
            throw new NoSuchElementException();
        }
        return pointer.element;
    }

    /**
     * @return The next element in the list
     */
    public E redo() {
        if (pointer.next == null) {
            throw new NoSuchElementException();
        }

        Node next = pointer.next;
        pointer = next;
        return next.element;
    }

    /**
     * @return The previous element in the list
     */
    public E undo() {
        if (pointer.prev == null) {
            throw new NoSuchElementException();
        }
        Node previousNode = pointer.prev;
        pointer = previousNode;
        return previousNode.element;
    }


    /**
     * @return A boolean for whether a next element exists
     */
    public boolean canRedo() {
        return pointer.next != null;
    }

    /**
     * @return A boolean for whether a previous element exists
     */
    public boolean canUndo() {
        return pointer.prev != null;
    }

    /**
     * @return The size of the list
     */
    public int size() {
        return size;
    }

    /**
     * @return A boolean for whether the list is empty or not
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Deletes all elements in the list and sets the size to 0
     */
    public void clear() {
        head = null;
        tail = null;
        pointer = null;
        size = 0;
    }


    /**
     * @return A string representation of all elements in the list
     */
    public String toString() {
        StringBuilder sb = new StringBuilder().append('[');
        Node tempHead = head;
        while (tempHead != null) {
            sb.append(tempHead.element);
            tempHead = tempHead.next;
            if (tempHead != null) {
                sb.append(',').append(' ');
            }
        }
        return sb.append(']').toString();
    }

    /*
     This method is only called if we're adding an element between two existing elements.
     */
    private void refreshSize() {
        size = 0;
        Node tempHead = head;
        while (tempHead != null) {
            tempHead = tempHead.next;
            size++;
        }
        shouldRefreshSize = false;
    }
}

