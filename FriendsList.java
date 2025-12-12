import java.util.ArrayList;
import java.util.List;

public class FriendsList {

    private List<Profile> friends;

    public FriendsList(List<Profile> friends) {
        // Ensure mutable copy
        this.friends = new ArrayList<>(friends);
    }

    public List<Profile> getAllFriends() {
        return friends;
    }

    public Profile getFriend(String username) {
        for (Profile f : friends) {
            if (f.getUsername().equalsIgnoreCase(username)) {
                return f;
            }
        }
        return null;
    }

    public void printFriends() {
        System.out.println("Friends:");
        for (Profile f : friends) {
            System.out.println("- " + f.getUsername());
        }
    }
}
