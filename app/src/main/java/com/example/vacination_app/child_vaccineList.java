package com.example.vacination_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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

public class child_vaccineList extends AppCompatActivity {

    TextView childName, childAge;

    //Recycler view
    private List<VaccineDisplay> listData;
    private RecyclerView rv;
    private VaccineAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_vaccine_list);

        childName=findViewById(R.id.child_name);
        childAge=findViewById(R.id.child_age);

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Name");
        String Description = intent.getExtras().getString("Age");
        String parentName = intent.getExtras().getString("parentName");


        childAge.setText(Description);
        childName.setText(Title);

        //Recycler view
        rv=(RecyclerView)findViewById(R.id.recyclerviewVaccine);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        listData=new ArrayList<>();


        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("Vaccines");
        Query query = nm.orderByChild("recommendedAge").equalTo(Description);
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        VaccineDisplay l=npsnapshot.getValue(VaccineDisplay.class);
                        listData.add(l);
                    }
                    adapter=new VaccineAdapter(child_vaccineList.this,listData);
                    //intent.putExtra("email", parentName);
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}