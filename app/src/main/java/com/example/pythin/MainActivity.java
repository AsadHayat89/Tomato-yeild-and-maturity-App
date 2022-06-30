package com.example.pythin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button SignUp,SignIn;
    private EditText email,pass;
    private EditText Email,Pass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SignUp=findViewById(R.id.btnSignUp);
        SignIn=findViewById(R.id.btnLoginIn);
        mAuth = FirebaseAuth.getInstance();
        email=findViewById(R.id.edtEmail);
        pass=findViewById(R.id.edtPassword);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Signup.class);
                startActivity(i);

            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString();
                String Pass = pass.getText().toString();
                if(TextUtils.isEmpty(Pass)){
                    pass.setError("Email cannot be empty");
                    return;
                }
                else if(TextUtils.isEmpty(Email))
                {
                    email.setError("Email cannot be empty");
                    return;
                }
                else{
                   // Toast.makeText(MainActivity.this,"here",Toast.LENGTH_SHORT).show();

                    mAuth.signInWithEmailAndPassword(Email, Pass)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("nerdataa","dfsd");
                                        Intent in = new Intent(MainActivity.this, mainDashj.class);
                                        startActivity(in);
                                    } else {
                                        // If sign in fails, display a message to the user.

                                        pass.setError("Invalid Password");
                                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });
                }



            }
        });
            }

}