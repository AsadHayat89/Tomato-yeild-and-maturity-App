package com.example.pythin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfile extends AppCompatActivity {
    private TextView name,email,address,phone;
    private FirebaseAuth firebase;
    private FirebaseDatabase firebaseDatabase;
    private Button eidt;
    private DatabaseReference databaseReference;
    String name1,phone1,email1,address1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        intiwidget();

        if(firebase.getCurrentUser().getEmail()!=null){
            databaseReference.child(firebase.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    // person p=snapshot.getValue(person.class);
                    name1=snapshot.child("name").getValue().toString();
                    phone1=snapshot.child("phone").getValue().toString();
                    email1=snapshot.child("email").getValue().toString();
                    address1=snapshot.child("address").getValue().toString();

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
eidt.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        databaseReference.child(firebase.getCurrentUser().getUid()).child("name").setValue(name.getText().toString());
        databaseReference.child(firebase.getCurrentUser().getUid()).child("phone").setValue(phone.getText().toString());
        databaseReference.child(firebase.getCurrentUser().getUid()).child("email").setValue(email.getText().toString());
        databaseReference.child(firebase.getCurrentUser().getUid()).child("address").setValue(address.getText().toString());
    }
});

    }

    private void intiwidget() {
        name=findViewById(R.id.VPEditName);
        email=findViewById(R.id.VPEditEmail);
        address=findViewById(R.id.VpEditAddress);
        phone=findViewById(R.id.VPEdithomeNumberwork);
        firebase=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("UserDATA");
        eidt=findViewById(R.id.btnEditdata);
    }
}