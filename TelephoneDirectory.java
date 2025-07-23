package telephone;
import java.util.ArrayList;
import java.util.List;

class Contact {
    String name;
    String surname;
    String phoneNumber;
    String email;
    String place;

    public Contact(String name, String surname, String phoneNumber, String email, String place) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.place = place;
    }

    @Override
    public String toString() {
        return "Name: " + name + " " + surname + 
               ", Phone: " + phoneNumber + 
               ", Email: " + email + 
               ", Place: " + place;
    }
}

public class TelephoneDirectory {
    public static void main(String[] args) {
        List<Contact> contacts = new ArrayList<>();

        // Adding sample contacts
        contacts.add(new Contact("John", "Doe", "9876543210", "john.doe@example.com", "Mumbai"));
        contacts.add(new Contact("Alice", "Smith", "9123456780", "alice.smith@example.com", "Pune"));
        contacts.add(new Contact("Bob", "Johnson", "9988776655", "bob.j@example.com", "Delhi"));

        // Display contacts
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}





