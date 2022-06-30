package com.example.pythin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShowData extends AppCompatActivity {

    TextView data,toatl,mature,immauture;
    ImageView back;
    private FirebaseAuth firebase;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        data=findViewById(R.id.SDdat);
        toatl=findViewById(R.id.SDTotal);
        mature=findViewById(R.id.SDripped);
        immauture=findViewById(R.id.SDUnripped);
        back=findViewById(R.id.SDmoveback);
        firebase=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("data");
        String sat=getIntent().getExtras().getString("status");
        String k=getIntent().getExtras().getString("key");
        Log.d("datashow",k);
        databaseReference.child(k).child("status").setValue("1");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        data.setText(getIntent().getExtras().getString("harvest"));
        toatl.setText(getIntent().getExtras().getString("time"));
        //i.putExtra("Half_Mature",item.getImMature());
        mature.setText(getIntent().getExtras().getString("Mature"));
        immauture.setText(getIntent().getExtras().getString("Half_Mature"));




    }
}