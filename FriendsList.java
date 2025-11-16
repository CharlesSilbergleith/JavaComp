import java.util.List;
import java.util.ArrayList;


public class FriendsList {
    List<Profile> friend = new ArrayList<>();

    public FriendsList(){

    }
    public FriendsList(List<Profile> friend){
        for(Profile f : friend){
            this.friend.add(f);
        }

    }

    public void getFriendsList(){
        for(Profile f : this.friend){
            System.out.println(f.toString());
        }
    }
    public void getFriend(String username){
        for(Profile f : this.friend){
            if(f.getUsername().equals(username)){
                System.out.println(f.toString());
            }
        }

    }


}
