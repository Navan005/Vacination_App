package com.example.vacination_app;

public class VaccineDisplay {
    private String Name;
    private String Description;
    private String recommendedAge;

    public VaccineDisplay() {

    }

    public VaccineDisplay(String Name,String Description, String recommendedAge) {
        this.Name=Name;
        this.Description=Description;
        this.recommendedAge=recommendedAge;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }
    public String getRecommendedAge() {
        return recommendedAge;
    }

}
