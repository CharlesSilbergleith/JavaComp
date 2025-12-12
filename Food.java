import java.util.ArrayList;
import java.util.List;

public class Food {
    private String name;
    private List<Allergy> allergiesInFood;

    public Food(String name, List<Allergy> allergiesInFood) {
        this.name = name;
        this.allergiesInFood = (allergiesInFood != null) ? allergiesInFood : new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Allergy> getAllergiesInFood() {
        return allergiesInFood;
    }
}
