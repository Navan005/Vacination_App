package com.example.vacination_app;

public class AddingAppointment {
    private String appointmentDate;
    private String childName;
    private String parentName;
    private String vaccineRequested;

    public AddingAppointment(){

    }

    public String getChildName() {
        return childName;
    }

    public String getParentName() {
        return parentName;
    }

    public String getVaccineRequested() {
        return vaccineRequested;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setChildName(String child_Name){
        child_Name= child_Name;
    }

    public void setParentName(String parent_Name) {
        parentName = parent_Name;
    }

    public void setVaccineRequested(String vaccine_Requested) { vaccineRequested = vaccine_Requested; }

    public void setAppointmentDate(String appointment_Date) {
        appointmentDate = appointment_Date;
    }

}
