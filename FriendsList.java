import java.util.List;
import java.util.ArrayList;

public class FriendsList {

    private List<Profile> friends = new ArrayList<>();

    public FriendsList() {}

    public FriendsList(List<Profile> friends) {
        this.friends.addAll(friends);
    }

    public void addFriend(Profile p) {
        friends.add(p);
    }

    public void removeFriend(String username) {
        friends.removeIf(f -> f.getUsername().equals(username));
    }

    public List<Profile> getFriendsList() {
        return friends;
    }

    public Profile getFriend(String username) {
        for (Profile p : friends) {
            if (p.getUsername().equals(username)) {
                return p;
            }
        }
        return null;
    }

    public void printFriends() {
        for (Profile p : friends) {
            System.out.println(p.toString());
        }
    }
}
