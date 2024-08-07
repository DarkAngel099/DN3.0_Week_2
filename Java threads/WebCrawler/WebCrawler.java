import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

public class WebCrawler {

    // Thread-safe map to store crawled data
    private static final ConcurrentHashMap<String, String> crawledData = new ConcurrentHashMap<>();
    private static final AtomicInteger pendingTasks = new AtomicInteger(0);

    // Define the task for crawling web pages
    static class CrawlerTask implements Runnable {
        private final String url;

        public CrawlerTask(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            try {
                // Simulate web page retrieval
                String content = fetchContent(url);
                crawledData.put(url, content);
                System.out.println("Crawled: " + url);
            } catch (IOException e) {
                System.err.println("Failed to crawl: " + url);
            } finally {
                if (pendingTasks.decrementAndGet() == 0) {
                    System.out.println("All tasks completed.");
                }
            }
        }

        // Fetch content from the URL
        private String fetchContent(String url) throws IOException {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            try (java.io.InputStream in = connection.getInputStream();
                 java.util.Scanner scanner = new java.util.Scanner(in).useDelimiter("\\A")) {
                return scanner.hasNext() ? scanner.next() : "";
            }
        }
    }

    // Main method to execute the program
    public static void main(String[] args) {
        // List of URLs to crawl
        List<String> urls = List.of(
            "https://example.com",
            "https://example.org",
            "https://example.net"
        );

        // Create a thread pool
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit tasks to the thread pool
        for (String url : urls) {
            pendingTasks.incrementAndGet();
            executor.submit(new CrawlerTask(url));
        }

        // Shutdown the executor and wait for all tasks to complete
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        // Display crawled data
        System.out.println("Crawled Data:");
        crawledData.forEach((url, content) -> System.out.println(url + ": " + content.substring(0, Math.min(content.length(), 100)) + "..."));
    }
}

