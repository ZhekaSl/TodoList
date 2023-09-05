package ua.todo.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {
    protected LinkedList<Task> history;
    protected Map<Integer, Node<Task>> map;


    public InMemoryHistoryManager() {
        this.history = new LinkedList<>();
        this.map = new HashMap<>();
    }

    @Override
    public void add(Task task) {
        Node<Task> node = new Node<>(task);
        int taskId = task.getId();
        remove(taskId);
        map.put(taskId, node);
        history.linkLast(node);

        if (map.size() > 10) {
            Node<Task> toDelete = history.getFirst();
            remove(toDelete.data.getId());
        }
    }

    @Override
    public void remove(int id) {
        if (map.containsKey(id)) {
            Node<Task> node = map.get(id);
            history.removeNode(node);
            map.remove(id);
        }
    }

    @Override
    public List<Task> getHistory() {
        return history.getTasks();
    }


    protected static class LinkedList<T> {
        private Node<T> first = null;
        private Node<T> last = null;
        private int size = 0;

        public void linkLast(Node<T> node) {
            if (isEmpty()) {
                first = node;
            } else {
                node.prev = last;
                last.next = node;
            }
            last = node;
            size++;
        }

        public void removeNode(Node<T> node) {
            if (isEmpty()) return;

            if (size == 1) {
                first = null;
                last = null;
                size--;
                return;
            }
            if (node == first) {
                first = first.next;
                first.prev = null;
            } else if (node == last) {
                last = last.prev;
                last.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            size--;

        }

        public List<T> getTasks() {
            List<T> list = new ArrayList<>();
            Node<T> current = first;
            while (current != null) {
                list.add(current.data);
                current = current.next;
            }
            return list;
        }

        public boolean isEmpty() {
            return first == null;
        }

        public int size() {
            return size;
        }

        public Node<T> getFirst() {
            return first;
        }
    }


    private static class Node<T> {
        private Node<T> next;
        private Node<T> prev;
        private final T data;

        public Node(T data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }
}
