package com.example.vacination_app;

public class AddingChild {
    private String child_name;
    private String age;
    private String parent_name;

    public AddingChild(){

    }

    public String getchild_name() {
        return child_name;
    }

    public String getage() {
        return age;
    }

    public String getparent_name() {
        return parent_name;
    }

    public void setchild_name(String child_Name){
        child_name= child_Name;
    }

    public void setage(String C_age) {
        age = C_age;
    }

    public void setparent_name(String parent_Name) {
        parent_name = parent_Name;
    }
}