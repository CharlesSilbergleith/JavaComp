import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
    //make all the allergies
        Allergy peanuts = new Allergy("Peanuts", "Severe reaction to peanut proteins.");
        Allergy tree_nuts = new Allergy("Tree Nuts", "Allergic to almonds, cashews, walnuts, etc.");
        Allergy shellfish = new Allergy("Shellfish", "Reacts to shrimp, crab, lobster.");
        Allergy fish = new Allergy("Fish", "Allergic to fish such as salmon or tuna.");
        Allergy milk = new Allergy("Milk", "Dairy allergy or reaction to milk proteins.");
        Allergy eggs = new Allergy("Eggs", "Reaction to egg whites or yolks.");
        Allergy wheat = new Allergy("Wheat", "Allergy to wheat proteins; may include gluten sensitivity.");
        Allergy soy = new Allergy("Soy", "Allergic to soy-based foods.");
        Allergy sesame = new Allergy("Sesame", "Reaction to sesame seeds or sesame oil.");
        Allergy strawberries = new Allergy("Strawberries", "Fruit allergy causing hives or swelling.");
        Allergy latex = new Allergy("Latex", "Reaction to latex products like gloves.");
        Allergy penicillin = new Allergy("Penicillin", "Allergic to penicillin-based antibiotics.");
        Allergy pollen = new Allergy("Pollen", "Seasonal allergy to airborne pollen.");
        Allergy dust_mites = new Allergy("Dust Mites", "Triggered by dust mite particles.");
        Allergy bee_stings = new Allergy("Bee Stings", "Reaction to bee venom.");

        //built in users
        Profile admin = new Profile();  // username = admin, password = admin123
        Profile user1 = new Profile("USER", "12345",  new ArrayList<Allergy>(Arrays.asList(peanuts,dust_mites )));
        Profile user2 = new Profile("user2", "123456",new ArrayList<Allergy>(Arrays.asList(eggs,strawberries )) );
        Profile user3 = new Profile("user3", "654321", new ArrayList<Allergy>(Arrays.asList(fish,shellfish )));
        Profile user4 = new Profile("user4", "654321", new ArrayList<Allergy>(Arrays.asList(wheat,soy )));
        Profile user5 = new Profile("user5", "654321" ,new ArrayList<Allergy>(Arrays.asList(latex,tree_nuts )));
    //add the users to the list of profiles in the system.
        List<Profile> members = new ArrayList<>();
        members.add(admin);
        members.add(user1);
        members.add(user2);
    //make a friends list

        boolean loggedIn = false;

        System.out.println("----- Welcome to AllergyLink -----\n");

        while (!loggedIn) {
            System.out.print("Enter username: ");
            String username = scan.nextLine();

            System.out.print("Enter password: ");
            String password = scan.nextLine();

            for (Profile member : members) {
                if (member.login(username, password)) {
                    loggedIn = true;
                    System.out.println("\nLogin successful! Welcome " + member.getUsername() + "!");
                    break;
                }
            }

            if (!loggedIn) {
                System.out.println("Incorrect username or password.\n");
            }


        }// end of the login loop




    }






}// end of class



