package com.example.pythin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Viewdata extends AppCompatActivity {
    private ArrayList<Items> itemsList=new ArrayList<>();
    private FirebaseAuth firebase;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdata);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("data");
        firebase=FirebaseAuth.getInstance();

        if(firebase.getCurrentUser().getEmail()!=null){
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot child : snapshot.getChildren()) {
                      //  Log.i("firstdata", child.getKey());
                        String Half_Mature=snapshot.child(child.getKey()).child("Half_Mature").getValue().toString();
                        String Mature=snapshot.child(child.getKey()).child("Mature").getValue().toString();
                        String Total_images=snapshot.child(child.getKey()).child("Total_images").getValue().toString();
                        String date=snapshot.child(child.getKey()).child("date").getValue().toString();
                        String time=snapshot.child(child.getKey()).child("time").getValue().toString();
                        String herv=snapshot.child(child.getKey()).child("Harvest").getValue().toString();
                        String status=snapshot.child(child.getKey()).child("status").getValue().toString();
                        //Log.i("firstdata", child.getValue(String.class));
                        int total=Integer.valueOf(Mature)+Integer.valueOf(Half_Mature);
                        String key=child.getKey().toString();
                        Items s1=new Items(date, 1,time,Mature,Half_Mature,String.valueOf(total),herv,status,key) ;
                        itemsList.add(s1);
                        //String name=databaseReference.child(child.getKey()).child("Half_Mature").g
                        //Log.i("firstdata", name);
                    }
                   //s4 Log.d("heredaf",String.valueOf(itemsList.size()));
                    // person p=snapshot.getValue(person.class);
                    //String name=snapshot.child("Half_Mature").getValue().toString();
                   // String phone=snapshot.child("phone").getValue().toString();
                    //String email=snapshot.child("email").getValue().toString();
                    //Log.d("firstdata",snapshot.getKey().ge);
                    AdapterClass recyclerviewItemAdapter = new AdapterClass(itemsList,"type");

                    RecyclerView recyclerView = findViewById(R.id.recycleView);
                    recyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(recyclerviewItemAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }


    //    Items s1=new Items("3/6/2002",1);
        //itemsList.add(s1);

       // Log.d("heredafnew",String.valueOf(itemsList.size()));


    }
}