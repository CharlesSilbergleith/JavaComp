import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GUI extends JFrame {

    private JTextArea partyNameDisplay;
    private JTextArea friendsListDisplay;
    private JTextArea mainDisplay;
    private JTextField foodField;

    private JButton btnThisParty;
    private JButton btnNext;
    private JButton btnPrev;
    private JButton btnNew;
    private JButton btnCheckFood;

    private List<Party> parties;
    private List<Food> foods;
    private FriendsList friendsList;

    private int index = 0;

    Font monoSpace = new Font("monospace", Font.BOLD, 20);

    public GUI(List<Party> parties, List<Food> foods, FriendsList friendsList) {

        this.parties = parties;
        this.foods = foods;
        this.friendsList = friendsList;

        setTitle("AllergyLink");
        setSize(1200, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Left panel
        JPanel partyPanel = new JPanel(new BorderLayout());

        partyNameDisplay = new JTextArea(10, 30);
        partyNameDisplay.setEditable(false);
        partyNameDisplay.setFont(monoSpace);
        partyNameDisplay.setText("No Parties Yet");
        partyPanel.add(partyNameDisplay, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 0, 10));
        btnThisParty = new JButton("Show This Party");
        btnNext = new JButton("Next Party");
        btnPrev = new JButton("Previous Party");
        btnNew = new JButton("Create New Party");
        btnCheckFood = new JButton("Check Food Safety");

        buttonPanel.add(btnThisParty);
        buttonPanel.add(btnNext);
        buttonPanel.add(btnPrev);
        buttonPanel.add(btnNew);
        buttonPanel.add(btnCheckFood);

        partyPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(partyPanel, BorderLayout.WEST);

        // Center panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainDisplay = new JTextArea(10, 30);
        mainDisplay.setEditable(false);
        mainPanel.add(new JScrollPane(mainDisplay), BorderLayout.CENTER);

        foodField = new JTextField("Enter Food Name");
        foodField.setFont(monoSpace);
        mainPanel.add(foodField, BorderLayout.NORTH);

        add(mainPanel, BorderLayout.CENTER);

        // Right panel
        JPanel friendsPanel = new JPanel(new BorderLayout());
        friendsListDisplay = new JTextArea(10, 30);
        friendsListDisplay.setEditable(false);
        friendsListDisplay.setFont(monoSpace);
        friendsPanel.add(new JScrollPane(friendsListDisplay), BorderLayout.CENTER);

        updateFriendsList();
        add(friendsPanel, BorderLayout.EAST);

        // Action listeners
        btnNext.addActionListener(e -> nextParty());
        btnPrev.addActionListener(e -> previousParty());
        btnThisParty.addActionListener(e -> updatePartyDisplay());
        btnNew.addActionListener(e -> createPartyGUI());
        btnCheckFood.addActionListener(e -> checkFood());
    }

    private void updateFriendsList() {
        StringBuilder sb = new StringBuilder();
        sb.append("Friends List:\n");
        for (Profile f : friendsList.getAllFriends()) {
            sb.append("- ").append(f.getUsername()).append("\n");
        }
        friendsListDisplay.setText(sb.toString());
    }

    private void updatePartyDisplay() {
        if (parties.isEmpty()) {
            partyNameDisplay.setText("No parties created.");
            mainDisplay.setText("");
            return;
        }

        Party p = parties.get(index);
        StringBuilder sb = new StringBuilder();
        sb.append("Party Members (").append(p.size()).append("):\n");
        for (Profile m : p.getMembers()) {
            sb.append("- ").append(m.getUsername()).append("\n");
        }
        partyNameDisplay.setText(sb.toString());
    }

    private void nextParty() {
        if (parties.isEmpty()) return;
        index = (index + 1) % parties.size();
        updatePartyDisplay();
    }

    private void previousParty() {
        if (parties.isEmpty()) return;
        index = (index - 1 + parties.size()) % parties.size();
        updatePartyDisplay();
    }

    private void createPartyGUI() {
        Party newParty = new Party();
        List<Profile> fl = friendsList.getAllFriends();
        String[] choices = new String[fl.size()];
        for (int i = 0; i < fl.size(); i++) choices[i] = fl.get(i).getUsername();

        JList<String> friendSelector = new JList<>(choices);
        friendSelector.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        int choice = JOptionPane.showConfirmDialog(this, new JScrollPane(friendSelector),
                "Select Friends for Party", JOptionPane.OK_CANCEL_OPTION);

        if (choice == JOptionPane.OK_OPTION) {
            for (String username : friendSelector.getSelectedValuesList()) {
                Profile f = friendsList.getFriend(username);
                if (f != null) newParty.addMember(f);
            }
            if (newParty.size() == 0) {
                JOptionPane.showMessageDialog(this, "Cannot create empty party.");
                return;
            }
            parties.add(newParty);
            index = parties.size() - 1;
            updatePartyDisplay();
        }
    }

    private void checkFood() {
        if (parties.isEmpty()) {
            mainDisplay.setText("No party to check.");
            return;
        }

        String input = foodField.getText().trim();
        if (input.isEmpty()) {
            mainDisplay.setText("Enter a food name first.");
            return;
        }

        Food dish = null;
        for (Food f : foods) {
            if (f.getName().equalsIgnoreCase(input)) {
                dish = f;
                break;
            }
        }

        if (dish == null) {
            mainDisplay.setText("Food not found.");
            return;
        }

        Party p = parties.get(index);
        StringBuilder sb = new StringBuilder();
        boolean safe = true;
        sb.append("Checking allergies for: ").append(dish.getName()).append("\n\n");

        for (Profile member : p.getMembers()) {
            for (Allergy a : dish.getAllergiesInFood()) {
                if (member.getAllergies().contains(a)) {
                    sb.append("⚠ ").append(member.getUsername())
                            .append(" is allergic to ").append(a.getAllergy()).append("!\n");
                    safe = false;
                }
            }
        }

        if (safe) sb.append("\n✔ SAFE for all party members!");
        else sb.append("\n✖ NOT SAFE for this party.");

        mainDisplay.setText(sb.toString());
    }
}
