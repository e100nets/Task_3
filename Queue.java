package ilya.ignatov;

import java.util.Collections;
import java.util.LinkedList;

public class Queue {
    java.util.Queue<String> queue = new LinkedList<>();

    public void addElement (String value) {
        queue.add(value);
    }

    public void deleteElement () {
        queue.poll();
    }

    public void clear () {
        queue.clear();
    }

    public void duplicate () {
        final int count = queue.size();
        for (int i = 0; i < count; i++) {
            queue.add(queue.peek());
            queue.add(queue.poll());
        }
    }

    public String[] toArray() {
        String[] array = new String[queue.size()];
        return queue.toArray(array);
    }
}
