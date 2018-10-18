package com.example.nouman.mosqueadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DefaultHomeScreen extends AppCompatActivity {

    Button updateprayertiming;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_home_screen);
        updateprayertiming=(Button) findViewById(R.id.update_prayer_timing);
        updateprayertiming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DefaultHomeScreen.this,UpdatePrayerTiming.class);
                startActivity(intent);
            }
        });
    }
}
