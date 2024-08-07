import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

// Contact class
class Contact implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String phone;
    private String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact [name=" + name + ", phone=" + phone + ", email=" + email + "]";
    }
}

// ContactWriter class
class ContactWriter {
    public static void saveContact(String fileName, Contact contact) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(contact);
        } catch (IOException e) {
            System.err.println("Error writing contact to file: " + e.getMessage());
        }
    }
}

// ContactReader class
class ContactReader {
    public static Contact readContact(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Contact) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading contact from file: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
        }
        return null;
    }
}

// Main application class
public class ContactManagementSystem {
    public static void main(String[] args) {
        String fileName = "contact.ser";

        // Create a Contact object
        Contact contact = new Contact("John Doe", "1234567890", "john.doe@example.com");

        // Save the Contact object to a file
        ContactWriter.saveContact(fileName, contact);

        // Read the Contact object back from the file
        System.out.println("Reading contact from the file:");
        Contact readContact = ContactReader.readContact(fileName);
        if (readContact != null) {
            System.out.println(readContact);
        }

        // Demonstrating error handling by attempting to read from a non-existent file
        System.out.println("Attempting to read contact from a non-existent file:");
        ContactReader.readContact("non_existent_contact.ser");
    }
}

