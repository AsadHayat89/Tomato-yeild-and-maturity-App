package com.example.pythin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Navigation extends AppCompatActivity {
    private TextView navName;
    private FirebaseAuth firebase;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    String namedata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

       // navName=findViewById(R.id.txtvanName);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("UserDATA");
        firebase=FirebaseAuth.getInstance();

        //Toast.makeText(Navigation.this,"here data"+navName.getText().toString().trim(),Toast.LENGTH_SHORT).show();
        if(firebase.getCurrentUser().getEmail()!=null){
            databaseReference.child(firebase.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    // person p=snapshot.getValue(person.class);
                    String name=snapshot.child("name").getValue().toString();
                    String phone=snapshot.child("phone").getValue().toString();
                    String email=snapshot.child("email").getValue().toString();
                    namedata=name;

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

        dl = (DrawerLayout)findViewById(R.id.navifation_activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        nv = (NavigationView)findViewById(R.id.nvdata);
        View headerView = nv.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.txtvanName);
        navUsername.setText(namedata);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Toast.makeText(Navigation.this, String.valueOf(id),Toast.LENGTH_SHORT).show();
                switch(id)
                {
                    case R.id.tvnewProfile:
                       // Toast.makeText(Navigation.this, "Profile",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_gallery:
                       // Toast.makeText(Navigation.this, "Gallery",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_slideshow:
                        //Toast.makeText(Navigation.this, "slide",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_settings:
                       // Toast.makeText(Navigation.this, "Settings",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return true;
                }

                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                item.setChecked(true);

                //loadHomeFragment();

                return true;
            }




        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
