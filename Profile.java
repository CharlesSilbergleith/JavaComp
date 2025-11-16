import java.util.ArrayList;
import java.util.List;

public class Profile {
    private String username;
    private String password;
    public List<Allergy> allergies = new ArrayList<>();
    private Allergy none = new Allergy ("None ", "no Allergy needed");

    public Profile() {
        this.username = "admin";
        this.password = "admin123";
        this.allergies.add( none);

    }

    public Profile(String username, String password, List<Allergy> allergies) {
        this.username = username;
        this.password = password;
        this.allergies = allergies;
    }

    public boolean login(String username, String password) {
        if (this.username == null || this.password == null) return false;

        return this.username.equals(username) && this.password.equals(password);
    }

    public String getUsername() { return username; }



}// end of class
