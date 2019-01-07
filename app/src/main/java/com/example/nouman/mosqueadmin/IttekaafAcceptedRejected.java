package com.example.nouman.mosqueadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class IttekaafAcceptedRejected extends AppCompatActivity {

    TextView t1,t2,t3,t4;
    Button B1,b2;
    DatabaseReference approveldb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ittekaaf_accepted_rejected);
        Intent intent = getIntent();

        String uName = intent.getStringExtra("userName");

        final String userPhone = intent.getStringExtra("userPhoneNo");
        final String mosqueId = intent.getStringExtra("mosqueId");

        t1=(TextView) findViewById(R.id.textView7);
        t2=(TextView) findViewById(R.id.textView9);
        t3=(TextView) findViewById(R.id.textView2);
        t4=(TextView) findViewById(R.id.textView3);
        B1=(Button) findViewById(R.id.button7);
        b2=(Button) findViewById(R.id.button8);
        t3.setText(uName);
        t4.setText(userPhone);

        approveldb= FirebaseDatabase.getInstance().getReferenceFromUrl("https://azzan-f7f08.firebaseio.com/IttekaafAppointment");

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                approveldb.child(mosqueId).child(userPhone).child("status").setValue("Accepted");
                Toast.makeText(IttekaafAcceptedRejected.this,"Status changed",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                approveldb.child(mosqueId).child(userPhone).child("status").setValue("Rejected");
                Toast.makeText(IttekaafAcceptedRejected.this,"Status changed",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
