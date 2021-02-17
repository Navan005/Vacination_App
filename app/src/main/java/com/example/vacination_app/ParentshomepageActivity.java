package com.example.vacination_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.List;

public class ParentshomepageActivity extends AppCompatActivity {

    private List<ChildDisplay> listData;
    private RecyclerView rv;
    private ChidrenAdapter adapter;

    private Button addChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parentshomepage);

        addChild=(Button) findViewById(R.id.addRecords);
        String Total=getIntent().getStringExtra("email");

        //Recycler view
        rv=(RecyclerView)findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        listData=new ArrayList<>();

        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("Children");
        Query query = nm.orderByChild("parent_name").equalTo(Total);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        ChildDisplay l=npsnapshot.getValue(ChildDisplay.class);
                        listData.add(l);
                    }
                    adapter=new ChidrenAdapter(ParentshomepageActivity.this,listData);
                    rv.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        addChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(ParentshomepageActivity.this, AddchildActivity.class);
                intent.putExtra("email", Total);
                startActivity(intent);
            }
        });


    }
}
