package com.example.pythin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

public class Signup extends AppCompatActivity {
    private EditText Email,Password,Confire_password,Name,phone;
    private Button Signup;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        intwidget();
        callListner();
    }

    private void callListner() {
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = Email.getText().toString();
                String password = Password.getText().toString();
                String confirm_password = Confire_password.getText().toString();
                String name=Name.getText().toString();
                String nedphnone=phone.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Email.setError("Email cannot be empty");
                    return;
                }
                else if (TextUtils.isEmpty(password)) {
                    Password.setError("Password cannot be empty");
                    return;
                }
                else if (TextUtils.isEmpty(confirm_password)) {
                    Confire_password.setError("Confirm Password cannot be empty");
                    return;
                }
                else if (!password.equals(confirm_password)) {
                    Confire_password.setError("Confirm Password should be same to Password");
                    return;
                }
                else if (password.length()<8) {
                    Password.setError("Password length should not be less than 6");
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                           // Uri imageUri = Uri.parse("android.resource://" + getApplicationContext().getPackageName() +
                            //        R.drawable.avagtar);

                            person person = new person(name,nedphnone,email,"",null);
                            //Adding values
                           databaseReference=firebaseDatabase.getReference("UserDATA").child(firebaseAuth.getUid());
                            //DatabaseReference newRef = rootRef.child("UserDATA");

                            databaseReference.setValue(person);
                            //newRef.child(email).setValue(person);
                            Toast.makeText(Signup.this, "Successfully Signed Up", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Signup.this, MainActivity.class);

                            startActivity(intent);
                            //finish();
                        }
                        else {
                            //Log.w("Tag", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Signup.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }


        });
    }

    private void intwidget() {
        firebaseAuth = FirebaseAuth.getInstance();
        Email=findViewById(R.id.btnSemail);
        Password=findViewById(R.id.btnSpass);
        Confire_password=findViewById(R.id.btnScon);
        Name=findViewById(R.id.btnSname);
        Signup=findViewById(R.id.btnSSignup);
        phone=findViewById(R.id.btnPhonevalue);
        firebaseDatabase=FirebaseDatabase.getInstance();
    }

}