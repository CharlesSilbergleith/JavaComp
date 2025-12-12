import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Allergies
        Allergy peanuts = new Allergy("Peanuts", "Severe reaction");
        Allergy shellfish = new Allergy("Shellfish", "Reacts to shrimp");
        Allergy fish = new Allergy("Fish", "Fish allergy");
        Allergy milk = new Allergy("Milk", "Dairy");
        Allergy eggs = new Allergy("Eggs", "Egg allergy");
        Allergy wheat = new Allergy("Wheat", "Wheat allergy");
        Allergy soy = new Allergy("Soy", "Soy allergy");
        Allergy strawberries = new Allergy("Strawberries", "Fruit allergy");
        Allergy gluten = new Allergy("Gluten", "Gluten reaction");

        // Users
        Profile admin = new Profile();
        Profile user1 = new Profile("ken", "12345", Arrays.asList(peanuts, wheat));
        Profile user2 = new Profile("bob", "2222", Arrays.asList(eggs, strawberries));
        Profile user3 = new Profile("alice", "3333", Arrays.asList(fish, shellfish));

        List<Profile> members = Arrays.asList(admin, user1, user2, user3);

        FriendsList friendsList = new FriendsList(Arrays.asList(user1, user2, user3));

        // Foods
        List<Food> foods = new ArrayList<>();
        foods.add(new Food("Cheeseburger", Arrays.asList(milk, gluten)));
        foods.add(new Food("Peanut Butter Sandwich", Arrays.asList(peanuts, gluten)));
        foods.add(new Food("Sushi Roll", Arrays.asList(shellfish, soy)));
        foods.add(new Food("Pizza", Arrays.asList(milk, gluten)));
        foods.add(new Food("Tofu Stir Fry", Arrays.asList(soy)));

        List<Party> parties = new ArrayList<>();

        // Start GUI
        GUI gui = new GUI(parties, foods, friendsList);
        gui.setVisible(true);
    }
}
