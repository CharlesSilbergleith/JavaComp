import java.util.ArrayList;
import java.util.List;

public class Food {
    public String name;
    public List<Allergy> allergiesInFood;

    public Food(String name, List<Allergy> allergies) {
        this.name = name;
        this.allergiesInFood = new ArrayList<>(allergies);
    }

    public String getName() {
        return name;
    }

    public List<Allergy> getAllergies() {
        return allergiesInFood;
    }
}
