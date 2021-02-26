package com.example.vacination_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainpageActivity extends AppCompatActivity {


    private Button addChild,addChild2;
    private TextView profileb,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        addChild=(Button) findViewById(R.id.addRecords);
        addChild2=(Button) findViewById(R.id.addRecords2);
        logout=findViewById(R.id.logout_txt);

        profileb=(TextView) findViewById(R.id.profile);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String Total = user.getEmail();
        //String Total=getIntent().getStringExtra("email");


        addChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainpageActivity.this, AddchildActivity.class);
                intent.putExtra("email", Total);
                startActivity(intent);
            }
        });

        addChild2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainpageActivity.this, AddchildActivity.class);
                intent.putExtra("email", Total);
                startActivity(intent);
            }
        });

        profileb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainpageActivity.this, ProfileditActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainpageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


}