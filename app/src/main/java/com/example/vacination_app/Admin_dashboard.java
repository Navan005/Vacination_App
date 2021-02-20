package com.example.vacination_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
//this directs CLSC to check pending and history of appointments
public class Admin_dashboard extends AppCompatActivity {

    Button pendingAppointment, appointmentHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
//find id with specific tags as needed
        pendingAppointment = (Button) findViewById(R.id.pendingAppointmentBtn);
        appointmentHistory = (Button) findViewById(R.id.appointmentHistoryBtn);

        pendingAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_dashboard.this, PendingAppointmentActivity.class);
                startActivity(intent);
            }
        });

        appointmentHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_dashboard.this, AppointmentHistoryActivity.class);
                startActivity(intent);
            }
        });

    }
}