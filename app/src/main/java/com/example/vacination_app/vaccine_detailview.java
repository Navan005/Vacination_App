package com.example.vacination_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class vaccine_detailview extends AppCompatActivity {

    TextView vaccineName, vaccineAge, vaccineDescription;
    Button appointmentBtn,historyAppBtn,logout,homeBt;
    FirebaseFirestore db;
    DatabaseReference appointments;
    AddingAppointment member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_detailview);

        vaccineName=findViewById(R.id.vaccine_name);
        vaccineAge=findViewById(R.id.vaccine_age);
        vaccineDescription=findViewById(R.id.vaccine_description);
        appointmentBtn=findViewById(R.id.appointment_button);
        historyAppBtn=findViewById(R.id.appointmentHistory_button);
        logout=findViewById(R.id.logoutBtn);
        homeBt=findViewById(R.id.homeBtn);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userid = user.getEmail();

        db = FirebaseFirestore.getInstance();
        appointments= FirebaseDatabase.getInstance().getReference().child("Appointment");
        member=new AddingAppointment();

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Name");
        String Description = intent.getExtras().getString("Description");
        String Age = intent.getExtras().getString("Age");


        vaccineName.setText(Title);
        vaccineAge.setText(Age + " year");
        vaccineDescription.setText(Description);

        appointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String datee="not yet";

                //member.setChildName(child_name);
                member.setParentName(userid);
                member.setVaccineRequested(Title);
                member.setAppointmentDate(datee);
                appointments.push().setValue(member);

                Toast.makeText(vaccine_detailview.this, "Appointment booked!", Toast.LENGTH_SHORT).show();
                //Intent intent=new Intent(vaccine_detailview.this, ParentshomepageActivity.class);
                //intent.putExtra("email", Total);
                //startActivity(intent);
            }
        });

        historyAppBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(vaccine_detailview.this, ParentHistoryActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vaccine_detailview.this, MainActivity.class);
                Toast.makeText(vaccine_detailview.this, "Account logged out", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        homeBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vaccine_detailview.this, ParentshomepageActivity.class);
                startActivity(intent);
            }
        });

    }
}