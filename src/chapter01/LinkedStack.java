package chapter01;

import java.util.EmptyStackException;

public class LinkedStack<E> {

    public static void main(String[] args) {
        LinkedStack<String> stack = new LinkedStack<>();
        System.out.println("stack empty: " + stack.isEmpty());
        for (int i = 0; i < 5; i++) {
            stack.push(i + 1 + "");
            System.out.println("stack size: " + stack.size());
            System.out.println("stack peek: " + stack.peek());
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("stack pop: " + stack.pop());
        }
        try {
            stack.pop();
        } catch (EmptyStackException e) {
            System.out.println("stack empty error.");
        }
    }

    /**
     * 链表节点
     */
    class Node {
        E item = null;
        Node next = null;
    }

    /**
     * 栈顶
     */
    private Node top = null;

    /**
     * 栈元素大小
     */
    private int size = 0;

    /**
     * add item
     * @param item
     */
    public void push(E item) {
        Node node = new Node();
        node.item = item;
        node.next = top;
        top = node;
        size++;
    }

    /**
     * get item from top stack
     * @return
     */
    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        E item = top.item;
        Node next = top.next;
        // remove item, to let gc do its work
        top.next = null;
        top = next;
        size--;
        return item;
    }

    /**
     * see item from top stack
     * @return
     */
    public E peek() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return top.item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
