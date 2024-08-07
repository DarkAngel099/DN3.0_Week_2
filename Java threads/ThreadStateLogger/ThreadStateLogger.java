public class ThreadStateLogger {

    // Define a new thread class
    static class SimpleThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println("Number: " + i);
                try {
                    // Sleep for a very short duration
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Create an instance of the thread
        SimpleThread thread = new SimpleThread();
        
        // Log the state of the thread before starting
        System.out.println("Thread state before starting: " + thread.getState());
        
        // Start the thread
        thread.start();
        
        // Log the state of the thread after starting
        System.out.println("Thread state after starting: " + thread.getState());
        
        // While the thread is still alive, log its state
        while (thread.isAlive()) {
            System.out.println("Thread state during execution: " + thread.getState());
            // Sleep for a very short duration
            Thread.sleep(5);
        }
        
        // Log the state of the thread after completion
        System.out.println("Thread state after completion: " + thread.getState());
    }
}

