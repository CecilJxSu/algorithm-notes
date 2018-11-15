package chapter01;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

public class Bag<E> implements Iterable<E> {

    public static void main(String[] args) {
        Bag<String> bag = new Bag<>();
        for (int i = 0; i < 5; i++) {
            bag.add(i + 1 + "");
        }
        // retrieve by forEach
        bag.forEach(System.out::println);
        // retrieve by iterator
        Iterator<String> it = bag.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    class Node {
        E item;
        Node next;
    }

    private Node head = null;

    private int size = 0;

    public void add(E item) {
        Node node = new Node();
        node.item = item;
        node.next = head;
        head = node;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        // 哨兵
        Node sentinel = head;
        while (sentinel != null) {
            action.accept(sentinel.item);
            sentinel = sentinel.next;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            /**
             * 当前游标
             */
            int cursor = 0;

            /**
             * 节点游标
             */
            Node cursorNode = head;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public E next() {
                if (cursorNode == null) {
                    throw new IllegalStateException();
                }
                E item = cursorNode.item;
                cursorNode = cursorNode.next;
                cursor++;
                return item;
            }
        };
    }
}
