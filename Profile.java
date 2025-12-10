import java.util.ArrayList;
import java.util.List;

public class Profile {

    private String username;
    private String password;
    private List<Allergy> allergies = new ArrayList<>();

    private static final Allergy NONE = new Allergy("None", "No allergies");

    // Default admin constructor
    public Profile() {
        this.username = "admin";
        this.password = "admin123";
        this.allergies.add(NONE);
    }

    // Normal user constructor
    public Profile(String username, String password, List<Allergy> allergies) {
        this.username = username;
        this.password = password;

        if (allergies == null || allergies.isEmpty()) {
            this.allergies.add(NONE);
        } else {
            this.allergies.addAll(allergies);
        }
    }

    public boolean login(String username, String password) {
        if (this.username == null || this.password == null) return false;
        return this.username.equals(username) && this.password.equals(password);
    }

    public String getUsername() {
        return username;
    }

    public List<Allergy> getAllergies() {
        return allergies;
    }

    public boolean hasAllergy(Allergy allergy) {
        return allergies.contains(allergy);
    }

    @Override
    public String toString() {
        return "User: " + username + " | Allergies: " + allergies.toString();
    }
}
