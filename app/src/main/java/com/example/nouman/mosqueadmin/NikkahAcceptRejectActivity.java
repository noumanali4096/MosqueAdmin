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

public class NikkahAcceptRejectActivity extends AppCompatActivity {
    TextView t1,t2,t3,t4;
    Button B1,b2;
    DatabaseReference approveldb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nikkah_accept_reject);
        Intent intent = getIntent();

        String uName = intent.getStringExtra("userName");
        String udate = intent.getStringExtra("Date");
        String time = intent.getStringExtra("time");
        final String userPhone = intent.getStringExtra("userPhone");
        final String mosqueId = intent.getStringExtra("mosqueId");

        t1=(TextView) findViewById(R.id.name_textView);
        t2=(TextView) findViewById(R.id.date_textView);
        t3=(TextView) findViewById(R.id.time_textView);
        t4=(TextView) findViewById(R.id.textView5);
        B1=(Button) findViewById(R.id.accept_button);
        b2=(Button) findViewById(R.id.reject_button);
        t1.setText(uName);
        t2.setText(udate);
        t3.setText(time);
        approveldb=FirebaseDatabase.getInstance().getReferenceFromUrl("https://azzan-f7f08.firebaseio.com/NikkahAppointment");

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                approveldb.child(mosqueId).child(userPhone).child("status").setValue("Accepted");
                Toast.makeText(NikkahAcceptRejectActivity.this,"Status changed",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                approveldb.child(mosqueId).child(userPhone).child("status").setValue("Rejected");
                Toast.makeText(NikkahAcceptRejectActivity.this,"Status changed",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
