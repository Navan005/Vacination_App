package com.example.vacination_app;

public class ChildDisplay {

    private String child_name;
    private String age;
    private String parent_name;

    public ChildDisplay() {

    }

    public ChildDisplay(String child_name,String age, String parent_name) {
        this.age=age;
        this.child_name=child_name;
        this.parent_name=parent_name;
    }

    public String getChild_name() {
        return child_name;
    }

    public String getAge() {
        return age;
    }
    public String getParent_name() {
        return parent_name;
    }

}
