package com.lisong.learn.core.containers.util;

public class SList<T> {

    private final Node<T> HEAD = new Node<>(null,null);

    private class Node<T> {
        private Node<T> next;
        private T data;

        Node(Node<T> next, T data) {
            this.next = next;
            this.data = data;
        }

        @Override
        public String toString() {
            return data == null ? "" : data.toString();
        }
    }

    public SListIterator<T> iterator() {
        return new SListIterator<T>() {
            private Node<T> current = HEAD;
            @Override
            public boolean hasNext() {
                return current.next != null;
            }

            @Override
            public T next() {
                current = current.next;
                return current.data;
            }

            @Override
            public void remove() {
                if(hasNext())
                    current.next = current.next.next;
            }

            @Override
            public void add(T t) {
                current.next = new Node<>(current.next, t);
                current = current.next;
            }

            @Override
            public void init() {
                current = HEAD;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        SListIterator<T> it = iterator();
        while(it.hasNext()) {
            T t = it.next();
            sb.append(t + (it.hasNext() ? ", " : "}"));
        }
        return sb.toString();
    }
}