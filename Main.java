import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Main {

    static boolean loggedIn = false;
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        // --- ALLERGIES ---
        Allergy peanuts = new Allergy("Peanuts", "Severe reaction to peanut proteins.");
        Allergy shellfish = new Allergy("Shellfish", "Reacts to shrimp, crab, lobster.");
        Allergy fish = new Allergy("Fish", "Allergic to fish such as salmon or tuna.");
        Allergy milk = new Allergy("Milk", "Dairy allergy or reaction to milk proteins.");
        Allergy eggs = new Allergy("Eggs", "Reaction to egg whites or yolks.");
        Allergy wheat = new Allergy("Wheat", "Allergy to wheat proteins; may include gluten sensitivity.");
        Allergy soy = new Allergy("Soy", "Allergic to soy-based foods.");
        Allergy strawberries = new Allergy("Strawberries", "Fruit allergy causing hives or swelling.");
        Allergy gluten = new Allergy("Gluten", "Allergic reaction to gluten.");

        // --- USERS ---
        Profile admin = new Profile();  // admin / admin123

        Profile user1 = new Profile("ken", "12345",
                new ArrayList<>(Arrays.asList(peanuts, wheat)));

        Profile user2 = new Profile("bob", "2222",
                new ArrayList<>(Arrays.asList(eggs, strawberries)));

        Profile user3 = new Profile("alice", "3333",
                new ArrayList<>(Arrays.asList(fish, shellfish)));
        

        List<Profile> members = new ArrayList<>();
        members.add(admin);
        members.add(user1);
        members.add(user2);
        members.add(user3);

        // --- FRIENDS LIST FOR EVERYONE (simple demo) ---
        FriendsList friendsList = new FriendsList(List.of(user1, user2, user3));

        // --- FOODS ---
        List<Food> foods = new ArrayList<>();
        foods.add(new Food("Cheeseburger", List.of(milk, gluten)));
        foods.add(new Food("Peanut Butter Sandwich", List.of(peanuts, gluten)));
        foods.add(new Food("Sushi Roll", List.of(shellfish, soy)));
        foods.add(new Food("Pizza", List.of(milk, gluten)));
        foods.add(new Food("Tofu Stir Fry", List.of(soy)));

        // --- PARTIES STORAGE ---
        List<Party> parties = new ArrayList<>();

        // --- START PROGRAM ---
        GUI gui = new GUI();
        
        gui.setVisible(true);
        

    }


    // ----------------------------------------------
    // LOGIN SYSTEM
    // ----------------------------------------------
    public static Profile Login(List<Profile> members) {

        while (!loggedIn) {

            System.out.print("Enter username: ");
            String username = scan.nextLine();

            System.out.print("Enter password: ");
            String password = scan.nextLine();

            for (Profile member : members) {
                if (member.login(username, password)) {
                    loggedIn = true;
                    System.out.println("\nLogin successful! Welcome " + member.getUsername() + "!");
                    return member;
                }
            }
            System.out.println("Incorrect username or password.\n");
        }
        return null;
    }


    // ----------------------------------------------
    // MAIN MENU
    // ----------------------------------------------
    public static void mainMenu(Profile currentUser,
                                List<Profile> members,
                                List<Party> parties,
                                List<Food> foods,
                                FriendsList friendsList) {

        while (true) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Create a New Party");
            System.out.println("2. Plan a Party");
            System.out.println("3. View Friends");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    createParty(currentUser, friendsList, parties);
                    break;

                case "2":
                    planParty(parties, foods);
                    break;

                case "3":
                    friendsList.printFriends();
                    break;

                case "4":
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    // ----------------------------------------------
    // CREATE PARTY
    // ----------------------------------------------
    public static void createParty(Profile currentUser,
                                   FriendsList friendsList,
                                   List<Party> parties) {

        Party newParty = new Party();

        System.out.println("\n--- CREATE A NEW PARTY ---");
        System.out.println("Your friends:");

        friendsList.printFriends();

        System.out.println("\nType the username of a friend to add them.");
        System.out.println("Type STOP to finish.");

        while (true) {
            System.out.print("Add friend: ");
            String input = scan.nextLine();

            if (input.equalsIgnoreCase("STOP")) break;

            Profile f = friendsList.getFriend(input);

            if (f == null) {
                System.out.println("Friend not found.");
            } else {
                newParty.addMember(f);
                System.out.println(f.getUsername() + " added.");
            }
        }

        if (newParty.size() == 0) {
            System.out.println("Party has no members. Not saved.");
            return;
        }

        parties.add(newParty);
        System.out.println("Party created with " + newParty.size() + " members!");
    }


    // ----------------------------------------------
    // PLAN PARTY
    // ----------------------------------------------
    public static void planParty(List<Party> parties, List<Food> foods) {

        if (parties.size() == 0) {
            System.out.println("You have no parties. Make one first!");
            return;
        }

        System.out.println("\n--- PLAN A PARTY ---");
        System.out.println("Choose a party:");

        for (int i = 0; i < parties.size(); i++) {
            System.out.println((i + 1) + ". Party with " + parties.get(i).size() + " members");
        }

        System.out.print("Enter number: ");
        int index = Integer.parseInt(scan.nextLine()) - 1;

        if (index < 0 || index >= parties.size()) {
            System.out.println("Invalid party.");
            return;
        }

        Party selected = parties.get(index);

        // Choose food
        System.out.println("\nWhat food do you want to serve?");
        for (int i = 0; i < foods.size(); i++) {
            System.out.println((i + 1) + ". " + foods.get(i).name);
        }

        System.out.print("Enter number: ");
        int foodIndex = Integer.parseInt(scan.nextLine()) - 1;

        if (foodIndex < 0 || foodIndex >= foods.size()) {
            System.out.println("Invalid food choice.");
            return;
        }

        Food dish = foods.get(foodIndex);

        System.out.println("\nChecking allergies...");
        boolean safe = true;

        for (Profile p : selected.getMembers()) {
            for (Allergy a : dish.allergiesInFood) {
                if (p.getAllergies().contains(a)) {
                    System.out.println("⚠ " + p.getUsername() + " is allergic to: " + a.getAllergy());
                    safe = false;
                }
            }
        }

        if (safe) {
            System.out.println("\n This dish is SAFE for the entire party!");
        } else {
            System.out.println("\n This dish CANNOT be served.");
        }
    }
}



class GUI extends JFrame{

    private JButton thisParty;
    private JButton upButton;
    private JButton downButton;
    private JButton newButton;
    
    Font monoSpace = new Font("monoSpace",Font.BOLD,20);
    
   
    public GUI() {
        setTitle("AllergyLink");
        setSize(1920,1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    
        // RIGHT SIDE PARTY DISPLAY AND SORT
        JPanel PartyPanel = new JPanel(new BorderLayout());  // <-- IMPORTANT FIX
    
        JTextArea partyNameDisplay = new JTextArea();
        partyNameDisplay.setEditable(false);
        partyNameDisplay.setFont(monoSpace);
        partyNameDisplay.setAlignmentX(RIGHT_ALIGNMENT);
        partyNameDisplay.setText("Party Name Here");
    
        PartyPanel.add(partyNameDisplay, BorderLayout.NORTH);
    
        JPanel ButtonPanel = new JPanel();
        ButtonPanel.setLayout(new GridLayout(0, 1, 0, 10));
    
        ButtonPanel.add(new JButton("ThisParty"));
        ButtonPanel.add(new JButton("Next Party"));
        ButtonPanel.add(new JButton("Previous Party"));
        ButtonPanel.add(new JButton("New Party"));
    
        PartyPanel.add(ButtonPanel, BorderLayout.SOUTH);
    
        add(PartyPanel, BorderLayout.WEST);
    }





}
