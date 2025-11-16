public class Allergy {
    public String alergy;
    public String description;



    public Allergy(String alergy, String description){

        this.alergy = alergy;
        this.description = description;
    }

    public String getAllergy() {
        return alergy;
    }

    public String getDescription() {
        return description;
    }

    public boolean CheckAllergy( String allergy ){
        if(this.alergy.equals(allergy) )
            return true;

        return false;
    }


}
