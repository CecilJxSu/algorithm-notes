package chapter01;

import java.util.EmptyStackException;

public class FixedCapacityStack<T> {

    public static void main(String[] args) {
        FixedCapacityStack<String> stack = new FixedCapacityStack<>(5);
        System.out.println("stack empty: " + stack.isEmpty());
        for (int i = 0; i < 5; i++) {
            stack.push(i + 1 + "");
            System.out.println("stack size: " + stack.size());
            System.out.println("stack peek: " + stack.peek());
        }
        try {
            stack.push("6");
        } catch (StackOverflowError e) {
            System.out.println("stack overflow error.");
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
     * element collection
     */
    private Object[] data;

    /**
     * element number
     */
    private int size = 0;

    /**
     * Fixed capacity
     * @param maxSize   max capacity
     */
    public FixedCapacityStack(int maxSize) {
        data = new Object[maxSize];
    }

    /**
     * add element
     * @param item  element
     */
    public void push(T item) {
        if (size == data.length) {
            throw new StackOverflowError();
        }
        data[size++] = item;
    }

    /**
     * get element from top stack
     * @return  element
     */
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T obj = peek();
        // to let gc do its work.
        data[--size] = null;
        return obj;
    }

    /**
     * see element from top stack
     * @return  element
     */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (T) data[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
