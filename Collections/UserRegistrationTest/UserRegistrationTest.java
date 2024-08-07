import java.util.TreeSet;

class UserRegistration {
    private TreeSet<String> userNames;

    public UserRegistration() {
        userNames = new TreeSet<>();
    }
    public boolean registerUser(String userName) {
        if (userNames.contains(userName)) {
            System.out.println("User already registered: " + userName);
            return false;
        }
 else {
            userNames.add(userName);
            System.out.println("User registered: " + userName);
            return true;
        }
    }
    public boolean removeUser(String userName) {
        if (userNames.contains(userName)) {
            userNames.remove(userName);
            System.out.println("User removed: " + userName);
            return true;
        } else {
            System.out.println("User not found: " + userName);
            return false;
        }
    }
    public void displayUsers() {
        System.out.println("Registered Users:");
        for (String userName : userNames) {
            System.out.println(userName);
        }
    }
}
public class UserRegistrationTest {
    public static void main(String[] args) {
        UserRegistration registration = new UserRegistration();
        registration.registerUser("Allen");
        registration.registerUser("Bob");
        registration.registerUser("Carl");
        registration.registerUser("Allen");
        registration.displayUsers();
        registration.removeUser("Bob");
        registration.removeUser("Carl");
        registration.displayUsers();
    }
}