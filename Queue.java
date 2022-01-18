//Sencer Yücel
//16933103152
//Queue Implementation Class for hw3

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int counter;
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Queue() {
        first = null;
        last = null;
        counter = 0;
    }
    //Returns null if it's empty
    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return counter;
    }

    //Enqueue the item
    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        counter++;
    }
    //Dequeue the item
    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        counter--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }
    //Returns to a LinkedIterator
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }
        public boolean hasNext() {
            return current != null;
        }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}