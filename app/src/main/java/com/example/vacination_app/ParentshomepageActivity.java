package com.example.vacination_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

//in this parents can add their child and view already added child
public class ParentshomepageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mNavDrawer; //member variable
    private List<ChildDisplay> listData;
    private RecyclerView rv;
    private ChidrenAdapter adapter;

    private Button addChild;
    private TextView profileb;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();  //to create inflater for menu
        inflater.inflate(R.menu.menu_nav, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer_layout);

        addChild=(Button) findViewById(R.id.addRecords);
        profileb=(TextView) findViewById(R.id.profile);
        Toolbar toolbar = findViewById(R.id.toolbar);  //reference to toolbar because i removed default one
        // setSupportActionBar(toolbar);  // call method and pass the toolbar
        mNavDrawer = findViewById(R.id.drawer_layout);  // we assign var
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( //to have the animation of the drawer
                this, mNavDrawer, toolbar, //var and tool bar pass
                R.string.navigation_drawer_open,      // the msg will not will appear for those who need
                //byt our action bar needs value
                R.string.navigation_drawer_close);
        mNavDrawer.addDrawerListener(toggle);  //toggle var passed
        toggle.syncState();               //rotation of hammburger btn
        NavigationView navigationView = findViewById(R.id.navigation_view);  //to listen to clicks on nav
        navigationView.setNavigationItemSelectedListener(this);



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String Total = user.getEmail();
        //String Total=getIntent().getStringExtra("email");

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
                        l.setId(npsnapshot.getKey());
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

        profileb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(ParentshomepageActivity.this, ProfileditActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {  // if any item is clicked in nav
        switch (item.getItemId()) {
            case R.id.nav_message:
                Intent intent=new Intent(ParentshomepageActivity.this, ProfileditActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_chat:
                Intent intent1=new Intent(ParentshomepageActivity.this, ParentHistoryActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_profile:
                Intent intent2=new Intent(ParentshomepageActivity.this, MainActivity.class);
                startActivity(intent2);
                break;
        }
        mNavDrawer.closeDrawer(GravityCompat.START); //nav in left side
        return true;
    }
}
