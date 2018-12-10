package com.example.nouman.mosqueadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DefaultHomeScreen extends AppCompatActivity {

    Button updateprayertiming;
    Button viewnikkah,viewIttekaaf,viewComplaints;
    String myphone;
    String mypass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_home_screen);
        updateprayertiming=(Button) findViewById(R.id.update_prayer_timing);
        viewnikkah=(Button) findViewById(R.id.nikkahRequest);
        viewIttekaaf=(Button) findViewById(R.id.ittekaafRequest);
        viewComplaints=(Button) findViewById(R.id.button9);
        Intent intent2 = getIntent();
        // final String myphone = contacts.get(0).getPhone();

        Bundle bundle = intent2.getExtras();
        myphone = bundle.getString("user_phone");
        mypass=bundle.getString("user_pass");

        updateprayertiming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DefaultHomeScreen.this,UpdatePrayerTiming.class);
                intent.putExtra("user_phone",myphone);
                intent.putExtra("user_pass",mypass);
                startActivityForResult(intent,11);
                // startActivity(intent);
                finish();
                //startActivity(intent);
            }
        });
        viewnikkah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DefaultHomeScreen.this,NikkahList.class);
                intent.putExtra("user_phone",myphone);
                startActivityForResult(intent,18);
            }
        });
        viewIttekaaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DefaultHomeScreen.this,IttekaafListActivity.class);
                intent.putExtra("user_phone",myphone);
                startActivityForResult(intent,19);
            }
        });
    }
}
