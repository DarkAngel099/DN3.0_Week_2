public class TaskScheduler {

    // Define the Task thread class
    static class Task extends Thread {
        private final String taskName;
        private final Task dependentTask;

        public Task(String taskName, Task dependentTask) {
            this.taskName = taskName;
            this.dependentTask = dependentTask;
            this.setName(taskName); // Set the thread name to match the task name
        }

        @Override
        public void run() {
            try {
                if (dependentTask != null) {
                    // Wait for the dependent task to complete
                    System.out.println(taskName + " is waiting for " + dependentTask.getName() + " to complete.");
                    dependentTask.join();
                    System.out.println(taskName + " has started after " + dependentTask.getName() + " completed.");
                }

                System.out.println(taskName + " is starting.");
                for (int i = 0; i < 3; i++) {
                    System.out.println(taskName + " is running.");
                    Thread.sleep(1000); // Pause for 1 second
                    Thread.yield(); // Yield to allow other threads to execute
                }
                System.out.println(taskName + " is completed.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Create tasks with dependencies
        Task task1 = new Task("Task 1", null);
        Task task2 = new Task("Task 2", task1);
        Task task3 = new Task("Task 3", task1);
        Task task4 = new Task("Task 4", task2);

        // Start tasks
        task1.start();
        task2.start();
        task3.start();
        task4.start();

        // Wait for all tasks to complete
        try {
            task1.join();
            task2.join();
            task3.join();
            task4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks have completed.");
    }
}

