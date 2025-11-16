import java.util.List;
import java.util.ArrayList;

public class Party {
    private List<Profile> members = new ArrayList<>();

    public Party(Profile[] members) {
        if (members != null) {
            for (Profile member : members) {
                this.members.add(member);
            }
        }
    }

    public Party() {
    }

    public void addMember(Profile member) {
        members.add(member);
    }
    public void removeMember(Profile member) {
        members.remove(member);
    }
    public void getMembers() {
        for (Profile member : members) {
            System.out.println(member.toString());
        }
    }
    public void getMember(String username){
        for (Profile member : members) {
            if (member.getUsername().equals(username)){
                System.out.println(member.toString());
            }
        }
    }

}
