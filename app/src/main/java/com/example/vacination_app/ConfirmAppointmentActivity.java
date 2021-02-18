package com.example.vacination_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class ConfirmAppointmentActivity extends AppCompatActivity {

    TextView date, vaccineName, parentEmail;
    DatePickerDialog datePickerDialog;
    Button appointmentBtn;

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_appointment);


        Intent intent = getIntent();
        String Title = intent.getExtras().getString("vaccine");
        //String appointment_date = intent.getExtras().getString("appointmentDate");
        String parentName = intent.getExtras().getString("parentName");

        vaccineName = (TextView) findViewById(R.id.vaccine_name);
        parentEmail = (TextView) findViewById(R.id.parent_email);
        appointmentBtn=findViewById(R.id.appointment_button);

        vaccineName.setText(Title);
        parentEmail.setText(parentName);



        /*
        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("Appointment");
        Query query = nm.orderByChild("parentName").equalTo(parentName);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.getRef().child("appointmentDate").setValue(date);

                }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/




        // initiate the date picker and a button
        date = (TextView) findViewById(R.id.appointmentDate);

        // perform click event on edit text
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(ConfirmAppointmentActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        appointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmAppointmentActivity.this, ParentshomepageActivity.class);
                startActivity(intent);
            }
        });


    }
}