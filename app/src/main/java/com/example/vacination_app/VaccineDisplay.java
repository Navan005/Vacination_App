package com.example.vacination_app;

public class VaccineDisplay {

    private String Name;
    private String Description;
    private String recommendedAge;

    public VaccineDisplay() {

    }

    public VaccineDisplay(String name,String description, String recommended_age) {
        this.Name=name;
        this.Description=description;
        this.recommendedAge=recommended_age;
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
