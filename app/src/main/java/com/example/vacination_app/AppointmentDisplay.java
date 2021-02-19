package com.example.vacination_app;

public class AppointmentDisplay {

    private String parentName;
    private String appointmentDate;
    private String vaccineRequested;
    private String id;

    public AppointmentDisplay() {

    }

    public AppointmentDisplay(String parentName, String vaccineRequested, String appointmentDate) {
        this.parentName=parentName;
        this.vaccineRequested=vaccineRequested;
        this.appointmentDate=appointmentDate;
    }

    public String getId() {
        return id;
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

    public void setId(String id){
        this.id = id;
    }
}
