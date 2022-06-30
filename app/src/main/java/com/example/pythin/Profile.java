package com.example.pythin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    private TextView name,email,address,phone;
    private FirebaseAuth firebase;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name=findViewById(R.id.VPName);
        email=findViewById(R.id.VPEmail);
        address=findViewById(R.id.VpAddress);
        phone=findViewById(R.id.VPhomeNumberwork);
        firebase=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("UserDATA");

        if(firebase.getCurrentUser().getEmail()!=null){
            databaseReference.child(firebase.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    // person p=snapshot.getValue(person.class);
                    String name1=snapshot.child("name").getValue().toString();
                    String phone1=snapshot.child("phone").getValue().toString();
                    String email1=snapshot.child("email").getValue().toString();
                    String address1=snapshot.child("address").getValue().toString();

                    name.setText(name1);
                    email.setText(email1);
                    phone.setText(phone1);
                    address.setText(address1);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }


    }
}