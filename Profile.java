import java.util.ArrayList;
import java.util.List;

public class Profile {

    private String username;
    private String password;
    private List<Allergy> allergies;

    // Admin constructor
    public Profile() {
        this.username = "admin";
        this.password = "admin123";
        this.allergies = new ArrayList<>();
    }

    public Profile(String username, String password, List<Allergy> allergies) {
        this.username = username;
        this.password = password;
        this.allergies = (allergies != null) ? allergies : new ArrayList<>();
    }

    public boolean login(String u, String p) {
        return username.equals(u) && password.equals(p);
    }

    public String getUsername() {
        return username;
    }

    public List<Allergy> getAllergies() {
        return allergies;
    }
}
