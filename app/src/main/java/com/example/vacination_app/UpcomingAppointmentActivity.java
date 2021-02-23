package com.example.vacination_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

public class UpcomingAppointmentActivity extends AppCompatActivity {

    private List<AppointmentDisplay> listData;
    private RecyclerView rv;
    private AdminAppointmentCancelAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_appointment);

        //Recycler view
        rv=(RecyclerView)findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        listData=new ArrayList<>();


        String datee = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //String Total = user.getEmail();
        //endat can be set to 99/99/9999 if you want to show just dates

        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("Appointment");
        Query query = nm.orderByChild("appointmentDate").startAt(datee).endAt("Cancelled");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        AppointmentDisplay l=npsnapshot.getValue(AppointmentDisplay.class);
                        l.setId(npsnapshot.getKey());
                        listData.add(l);
                    }
                    adapter=new AdminAppointmentCancelAdapter(UpcomingAppointmentActivity.this,listData);
                    rv.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}