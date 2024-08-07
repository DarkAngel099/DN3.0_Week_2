import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {

    // Define the shared DataQueue class
    static class DataQueue {
        private final Queue<Integer> queue = new LinkedList<>();
        private final int capacity;

        public DataQueue(int capacity) {
            this.capacity = capacity;
        }

        // Synchronized method to add data to the queue
        public synchronized void produce(int data) throws InterruptedException {
            while (queue.size() == capacity) {
                wait(); // Wait if the queue is full
            }
            queue.add(data);
            System.out.println("Produced: " + data);
            notifyAll(); // Notify consumers that data is available
        }

        // Synchronized method to remove data from the queue
        public synchronized int consume() throws InterruptedException {
            while (queue.isEmpty()) {
                wait(); // Wait if the queue is empty
            }
            int data = queue.poll();
            System.out.println("Consumed: " + data);
            notifyAll(); // Notify producers that space is available
            return data;
        }
    }

    // Define the Producer thread class
    static class Producer extends Thread {
        private final DataQueue queue;

        public Producer(DataQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 5; i++) { // Produce 5 items
                    queue.produce(i);
                    Thread.sleep((int) (Math.random() * 1000)); // Simulate variable production time
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Define the Consumer thread class
    static class Consumer extends Thread {
        private final DataQueue queue;

        public Consumer(DataQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 5; i++) { // Consume 5 items
                    queue.consume();
                    Thread.sleep((int) (Math.random() * 1500)); // Simulate variable consumption time
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Main method to execute the program
    public static void main(String[] args) {
        DataQueue queue = new DataQueue(5); // Create a DataQueue with capacity 5

        // Create and start producer and consumer threads
        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
        Consumer consumer1 = new Consumer(queue);
        Consumer consumer2 = new Consumer(queue);

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();

        // Wait for all threads to complete
        try {
            producer1.join();
            producer2.join();
            consumer1.join();
            consumer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All producers and consumers have completed.");
    }
}

