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

import java.util.ArrayList;
import java.util.List;
//this activity display pending appointments to CLSC
public class PendingAppointmentActivity extends AppCompatActivity {


    private List<AppointmentDisplay> listData;
    private RecyclerView rv;
    private AppointmentAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_appointment);

        //Recycler view
        rv=(RecyclerView)findViewById(R.id.recyclerviewPending);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        listData=new ArrayList<>();

        String notSet="not yet";

        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("Appointment");
        Query query = nm.orderByChild("appointmentDate").equalTo(notSet);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        AppointmentDisplay l=npsnapshot.getValue(AppointmentDisplay.class);
                        l.setId(npsnapshot.getKey());
                        listData.add(l);
                    }
                    adapter=new AppointmentAdapter(PendingAppointmentActivity.this,listData);
                    rv.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}