import java.util.List;
import java.util.ArrayList;

public class Party {

    private List<Profile> members = new ArrayList<>();

    public Party() {}

    public Party(Profile[] members) {
        if (members != null) {
            for (Profile p : members) {
                this.members.add(p);
            }
        }
    }

    public void addMember(Profile member) {
        members.add(member);
    }

    public void removeMember(Profile member) {
        members.remove(member);
    }

    public void removeMember(String username) {
        members.removeIf(p -> p.getUsername().equals(username));
    }

    public List<Profile> getMembers() {
        return members;
    }

    public Profile getMember(String username) {
        for (Profile p : members) {
            if (p.getUsername().equals(username)) {
                return p;
            }
        }
        return null;
    }

    public void printMembers() {
        for (Profile p : members) {
            System.out.println(p.toString());
        }
    }

    public int size() {
        return members.size();
    }
}
