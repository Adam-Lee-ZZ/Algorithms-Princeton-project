import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items = (Item[]) new Object[10];
    private int capacity = 2;
    private int size;

    public RandomizedQueue() {
        this.size = 0;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (size == capacity) {
            capacity = 2 * capacity;
            Item[] new_items = (Item[]) new Object[capacity];
            for (int i = 0; i < size; i++) {
                new_items[i] = items[i];
            }
            this.items = new_items;
        }
        items[size] = item;
        size++;
    }

    public Item dequeue() {
        int no = StdRandom.uniformInt(size);
        Item it = items[no];

        if (size - 1 < capacity * 0.25) {
            capacity = (int) (capacity * 0.5 + 1);
        }
        Item[] new_items = (Item[]) new Object[capacity];
        for (int i = 0; i < no; i++) {
            new_items[i] = items[i];
        }
        for (int i = no + 1; i < size; i++) {
            new_items[i - 1] = items[i];
        }

        this.items = new_items;
        size--;
        return it;
    }

    public Item sample() {
        int no = StdRandom.uniformInt(size);
        Item it = items[no];
        return it;
    }

    public Iterator<Item> iterator() {
        return new creatIterator();
    }

    private class creatIterator implements Iterator<Item> {

        private Item[] copyArray;
        private int remain;

        public creatIterator() {
            copyArray = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                copyArray[i] = items[i];
            }
            StdRandom.shuffle(copyArray);
            remain = size;
        }

        public Item next() {
            if (remain == 0) {
                throw new NoSuchElementException();
            }
            return copyArray[--remain];

        }

        public boolean hasNext() {
            return (remain != 0);
        }

    }

    public static void main(String[] args) {
        RandomizedQueue<String> r = new RandomizedQueue<String>();
        r.enqueue("nut");
        r.enqueue("pig");
        r.enqueue("cat");
        r.enqueue("mongo");
        System.out.println(r.sample());
        System.out.println(r.sample());
        System.out.println(r.dequeue());
        if (!r.isEmpty()) {
            System.out.println("Not empty");
        }
        Iterator<String> it = r.iterator();
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
    }

}
