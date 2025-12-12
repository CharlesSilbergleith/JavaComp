import java.util.ArrayList;
import java.util.List;

public class Party {

    private List<Profile> members;

    public Party() {
        members = new ArrayList<>();
    }

    public Party(List<Profile> initialMembers) {
        members = (initialMembers != null) ? new ArrayList<>(initialMembers) : new ArrayList<>();
    }

    public void addMember(Profile p) {
        if (p != null && !members.contains(p)) {
            members.add(p);
        }
    }

    public List<Profile> getMembers() {
        return members;
    }

    public int size() {
        return members.size();
    }
}
