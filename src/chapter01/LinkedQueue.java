package chapter01;

public class LinkedQueue<E> {

    public static void main(String[] args) {
        LinkedQueue<String> queue = new LinkedQueue<>();
        System.out.println("queue empty: " + queue.isEmpty());
        for(int i = 0; i < 5; i++) {
            queue.add(i + 1 + "");
            // don't change
            System.out.println("queue peek: " + queue.peek());
            // increasing
            System.out.println("queue size: " + queue.size());
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("queue poll: " + queue.poll());
        }
        try {
            queue.poll();
        } catch (EmptyQueueException e) {
            System.out.println("queue empty error.");
        }
    }

    /**
     * 队列中的节点
     */
    class Node {
        E item = null;
        Node next = null;
    }

    /**
     * 队列头部
     */
    private Node head;

    /**
     * 队列尾部
     */
    private Node tail = new Node();

    /**
     * 队列元素大小
     */
    private int size;

    public LinkedQueue() {
        head = tail;
    }

    public void add(E item) {
        tail.item = item;
        tail.next = new Node();
        tail = tail.next;
        size++;
    }

    public E poll() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        E item = head.item;
        Node next = head.next;
        // remove item, to let gc do its work
        head.next = null;
        head = next;
        size--;
        return item;
    }

    public E peek() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return head.item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

}

class EmptyQueueException extends RuntimeException {}