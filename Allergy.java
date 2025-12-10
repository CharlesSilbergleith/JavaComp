public class Allergy {

    private String allergy;
    private String description;

    public Allergy(String allergy, String description) {
        this.allergy = allergy;
        this.description = description;
    }

    public String getAllergy() {
        return allergy;
    }

    public String getDescription() {
        return description;
    }

    public boolean checkAllergy(String allergyToCheck) {
        return allergy.equalsIgnoreCase(allergyToCheck);
    }

    @Override
    public String toString() {
        return allergy + ": " + description;
    }
}
