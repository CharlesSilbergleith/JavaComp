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

    @Override
    public String toString() {
        return allergy;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Allergy)) return false;
        Allergy other = (Allergy) obj;
        return allergy.equalsIgnoreCase(other.allergy);
    }

    @Override
    public int hashCode() {
        return allergy.toLowerCase().hashCode();
    }
}
