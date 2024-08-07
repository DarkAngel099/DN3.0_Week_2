import java.util.Random;

public class SensorSimulation {

    // Define the Sensor thread class
    static class Sensor extends Thread {
        private final int sensorId;
        private final Random random;

        public Sensor(int sensorId) {
            this.sensorId = sensorId;
            this.random = new Random();
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) { // Reduced the number of iterations
                int data = random.nextInt(100); // Simulate collecting random data
                System.out.println("Sensor " + sensorId + " collected data: " + data);
                try {
                    Thread.sleep(200); // Reduced the sleep duration
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        int numberOfSensors = 3; // Reduced the number of sensors
        Sensor[] sensors = new Sensor[numberOfSensors];

        // Create and start sensor threads
        for (int i = 0; i < numberOfSensors; i++) {
            sensors[i] = new Sensor(i);
            sensors[i].start();
        }

        // Wait for all sensor threads to complete
        for (Sensor sensor : sensors) {
            try {
                sensor.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All sensor threads have completed.");
    }
}

