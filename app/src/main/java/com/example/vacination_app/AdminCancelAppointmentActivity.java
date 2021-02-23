package com.example.vacination_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AdminCancelAppointmentActivity extends AppCompatActivity {

    TextView date, vaccineName, parentEmail;
    Button cancelAppointmentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_cancel_appointment);

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("vaccine");
        String appointment_date = intent.getExtras().getString("appointmentDate");
        String parentName = intent.getExtras().getString("parentName");
        String appointmentId = intent.getStringExtra("id");

        vaccineName = (TextView) findViewById(R.id.vaccine_name);
        parentEmail = (TextView) findViewById(R.id.parent_email);
        date=(TextView) findViewById(R.id.appointmentDate);
        cancelAppointmentBtn=findViewById(R.id.cancel_appointment_button);


        vaccineName.setText(Title);
        parentEmail.setText(parentName);
        date.setText(appointment_date);


        cancelAppointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Appointment").child(appointmentId);

                Map<String, Object> data = new HashMap<>();
                data.put("appointmentDate", "Cancelled");

                databaseReference.updateChildren(data, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if(error == null){
                            Toast.makeText(getApplicationContext(), "Appointment cancelled successfullly", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(AdminCancelAppointmentActivity.this, Admin_dashboard.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }
}