import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Pin fiest;
    private Pin last;
    private int size;

    private class Node {
        Item item;
        Node next;
        Node pre;
    }

    private class Pin {
        Node pin;
    }

    public Deque() {
        this.size = 0;
        this.fiest = new Pin();
        this.last = new Pin();
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("can not add null into the deque");
        }
        if (size == 0) {
            Node zero = new Node();
            zero.item = item;
            fiest.pin = zero;
            last.pin = zero;
            size++;
        } else {
            Node new_node = new Node();
            new_node.item = item;
            new_node.next = fiest.pin;
            fiest.pin.pre = new_node;
            fiest.pin = new_node;
            size ++;
        }
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("can not add null into the deque");
        }
        if (size == 0) {
            Node zero = new Node();
            zero.item = item;
            fiest.pin = zero;
            last.pin = zero;
            size++;
        } else {
            Node new_node = new Node();
            new_node.item = item;
            new_node.pre = last.pin;
            last.pin.next = new_node;
            last.pin = new_node;
            size++;
        }
    }

    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        } else if (size == 1) {
            Item it = fiest.pin.item;
            size--;
            return it;
        }
        Item it = fiest.pin.item;
        fiest.pin = fiest.pin.next;
        fiest.pin.pre = null;
        size = size - 1;
        return it;
    }

    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        } else if (size == 1) {
            Item it = last.pin.item;
            size--;
            return it;
        }
        Item it = last.pin.item;
        last.pin = last.pin.pre;
        last.pin.next = null;
        size = size - 1;
        return it;
    }

    public Iterator<Item> iterator() {

        return new creatIterator();

    }

    private class creatIterator implements Iterator<Item> {

        private Node pin_i;
        private int remain;

        public creatIterator() {
            pin_i = fiest.pin;
            remain = size;
        }

        public Item next() {
            if (remain == 0) {
                throw new NoSuchElementException();
            }
            Item value = pin_i.item;
            pin_i = pin_i.next;
            remain--;
            return value;
        }

        public boolean hasNext() {
            return (remain > 0);
        }

    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        System.out.println(deque.removeLast());
        System.out.println(deque.size);
        deque.addFirst(4);
        deque.addFirst(5);
        System.out.println(deque.removeLast());

    }
}
