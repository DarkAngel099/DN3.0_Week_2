import java.util.LinkedHashSet;

class BookCollection {
    private LinkedHashSet<String> bookTitles;

    public BookCollection() {
        bookTitles = new LinkedHashSet<>();
    }
    public boolean addBook(String bookTitle) {
        if (bookTitles.contains(bookTitle)) {
            System.out.println("Book already exists: " + bookTitle);
            return false;
        } else {
            bookTitles.add(bookTitle);
            System.out.println("Book added: " + bookTitle);
            return true;
        }
    }
    public boolean removeBook(String bookTitle) {
        if (bookTitles.contains(bookTitle)) {
            bookTitles.remove(bookTitle);
            System.out.println("Book removed: " + bookTitle);
            return true;
        } else {
            System.out.println("Book not found: " + bookTitle);
            return false;
        }
    }
    public void displayBooks() {
        System.out.println("Book Collection:");
        for (String bookTitle : bookTitles) {
            System.out.println(bookTitle);
        }
    }
}
public class BookCollectionTest {
    public static void main(String[] args) {
        BookCollection collection = new BookCollection();
        collection.addBook("The Great Gatsby");
        collection.addBook("Moby Dick");
        collection.addBook("1984");
        collection.addBook("The Great Gatsby");
        collection.displayBooks();
        collection.removeBook("Moby Dick");
        collection.removeBook("To Kill a Mockingbird");
        collection.displayBooks();
    }
}

