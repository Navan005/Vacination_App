package com.example.vacination_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AppointmentHistoryActivity extends AppCompatActivity {

    private List<AppointmentDisplay> listData;
    private RecyclerView rv;
    private AppointmentHistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_history);


        //Recycler view
        rv=(RecyclerView)findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        listData=new ArrayList<>();

        String notSet="not yet";
        String datee = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());


        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("Appointment");
        Query query = nm.orderByChild("appointmentDate").endAt(datee);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        AppointmentDisplay l=npsnapshot.getValue(AppointmentDisplay.class);
                        listData.add(l);
                    }
                    adapter=new AppointmentHistoryAdapter(AppointmentHistoryActivity.this,listData);
                    rv.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}