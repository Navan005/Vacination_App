package com.example.vacination_app;

public class ChildDisplay {

    private String child_name;
    private String age;
    private String parent_name;

    private ChildDisplay() {}

    private ChildDisplay(String child_name,String age, String parent_name) {
        this.age=age;
        this.child_name=child_name;
        this.parent_name=parent_name;
    }

    public String getChild_name() {
        return child_name;
    }

    public void setChild_name(String child_name) {
        this.child_name = child_name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }
}
