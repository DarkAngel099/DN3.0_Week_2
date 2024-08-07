import java.util.Hashtable;

class ContactManagement {
    private Hashtable<Integer, Contact> contacts;

    public ContactManagement() {
        contacts = new Hashtable<>();
    }

    public void addContact(Contact contact) {
        if (contacts.containsKey(contact.getId())) {
            System.out.println("Contact ID already exists: " + contact.getId());
        } else {
            contacts.put(contact.getId(), contact);
            System.out.println("Contact added: " + contact);
        }
    }

    public void removeContact(int contactId) {
        if (contacts.containsKey(contactId)) {
            Contact removedContact = contacts.remove(contactId);
            System.out.println("Contact removed: " + removedContact);
        } else {
            System.out.println("Contact not found with ID: " + contactId);
        }
    }

    public void displayContacts() {
        System.out.println("Contact List:");
        for (Contact contact : contacts.values()) {
            System.out.println(contact);
        }
    }
}
class Contact {
    private int id;
    private String name;
    private String phoneNumber;

    public Contact(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Contact ID: " + id + ", Name: " + name + ", Phone Number: " + phoneNumber;
    }
}
public class ContactManagementTest {
    public static void main(String[] args) {
        ContactManagement contactManager = new ContactManagement();

        Contact contact1 = new Contact(1, "Allen", "9876543210");
        Contact contact2 = new Contact(2, "Bob", "9578495234");
        Contact contact3 = new Contact(3, "Carl", "9765403246");

        contactManager.addContact(contact1);
        contactManager.addContact(contact2);
        contactManager.addContact(contact3);
        contactManager.displayContacts();
        contactManager.removeContact(2);
        contactManager.displayContacts();
    }
}


