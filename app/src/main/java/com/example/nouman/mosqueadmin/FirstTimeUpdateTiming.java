package com.example.nouman.mosqueadmin;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.List;

public class FirstTimeUpdateTiming extends AppCompatActivity implements View.OnClickListener{
    EditText e1,e2,e3,e4,e5,e6;

    EditText fajar,zohar,asar,maghrib,isha,juma;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;
    String myphone;
    String mypass;
    DBHelper dbHelper;
    PrayerTImings prayerTImings;

    DatabaseReference databasePrayerTiming;
    DatabaseReference databaseMosqueTiming;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_update_timing);

        dbHelper=new DBHelper(this);
        List<MosqueAdmin> contacts = dbHelper.getAllContacts();

        Intent intent2 = getIntent();
        // final String myphone = contacts.get(0).getPhone();

        Bundle bundle = intent2.getExtras();
        myphone = bundle.getString("user_phone");
        mypass=bundle.getString("user_pass");
       // mosqueAdmin.setPhone(myphone);
        //mosqueAdmin.setPassword(mypass);

        //final String myphone = contacts.get(0).getPhone();
        databasePrayerTiming = FirebaseDatabase.getInstance().getReferenceFromUrl
                ("https://azzan-f7f08.firebaseio.com/prayertiming");
        databaseMosqueTiming = FirebaseDatabase.getInstance().getReferenceFromUrl
               ("https://azzan-f7f08.firebaseio.com/mosquentiming");
        init();

        e1.setFocusable(false);
        e1.setClickable(true);
        e1.setOnClickListener(this);

        e2.setFocusable(false);
        e2.setClickable(true);
        e2.setOnClickListener(this);

        e3.setFocusable(false);
        e3.setClickable(true);
        e3.setOnClickListener(this);

        e4.setFocusable(false);
        e4.setClickable(true);
        e4.setOnClickListener(this);

        e5.setFocusable(false);
        e5.setClickable(true);
        e5.setOnClickListener(this);

        e6.setFocusable(false);
        e6.setClickable(true);
        e6.setOnClickListener(this);
        prayerTImings =new PrayerTImings();
        prayerTImings.setPhoneNumber(myphone);
        update.setOnClickListener(this);
    }

    private  void init(){
        e1=(EditText) findViewById(R.id.fajar_editText);
        e2=(EditText) findViewById(R.id.zohar_editText);
        e3=(EditText) findViewById(R.id.asar_editText);
        e4=(EditText) findViewById(R.id.maghrib_editText);
        e5=(EditText) findViewById(R.id.isha_editText);
        e6=(EditText) findViewById(R.id.juma_editText);
        update=(Button) findViewById(R.id.update_button);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fajar_editText:
                fajar=(EditText) v.findViewById(R.id.fajar_editText);
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            if(hourOfDay > 12) {
                                hourOfDay = hourOfDay - 12;
                            }
                            amPm = "PM";
                        } else {
                            if(hourOfDay > 12) {
                                hourOfDay = 00;
                            }
                            amPm = "AM";
                        }
                        fajar.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
                break;
            //////////////////////////////////////////////////
            case R.id.zohar_editText:
                zohar=(EditText) v.findViewById(R.id.zohar_editText);
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            if(hourOfDay > 12) {
                                hourOfDay = hourOfDay - 12;
                            }
                            amPm = "PM";
                        } else {
                            if(hourOfDay > 12) {
                                hourOfDay = 00;
                            }
                            amPm = "AM";
                        }
                        zohar.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
                break;
            //////////////////////////////////////////
            case R.id.asar_editText:
                asar=(EditText) v.findViewById(R.id.asar_editText);
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            if(hourOfDay > 12) {
                                hourOfDay = hourOfDay - 12;
                            }
                            amPm = "PM";
                        } else {
                            if(hourOfDay > 12) {
                                hourOfDay = 00;
                            }
                            amPm = "AM";
                        }
                        asar.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
                break;
            /////////////////////////////////////////////////
            case R.id.maghrib_editText:
                maghrib=(EditText) v.findViewById(R.id.maghrib_editText);
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            if(hourOfDay > 12) {
                                hourOfDay = hourOfDay - 12;
                            }
                            amPm = "PM";
                        } else {
                            if(hourOfDay > 12) {
                                hourOfDay = 00;
                            }
                            amPm = "AM";
                        }
                        maghrib.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
                break;
            /////////////////////////////////////////////////////
            case R.id.isha_editText:
                isha=(EditText) v.findViewById(R.id.isha_editText);
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            if(hourOfDay > 12) {
                                hourOfDay = hourOfDay - 12;
                            }
                            amPm = "PM";
                        } else {
                            if(hourOfDay > 12) {
                                hourOfDay = 00;
                            }
                            amPm = "AM";
                        }
                        isha.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
                break;
            //////////////////////////////////////////////
            case R.id.juma_editText:
                juma=(EditText) v.findViewById(R.id.juma_editText);
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            if(hourOfDay > 12) {
                                hourOfDay = hourOfDay - 12;
                            }
                            amPm = "PM";
                        } else {
                            if(hourOfDay > 12) {
                                hourOfDay = 00;
                            }
                            amPm = "AM";
                        }
                        juma.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
                break;

            case R.id.update_button:
                update=(Button) v.findViewById(R.id.update_button);
                String fajartime=e1.getText().toString().trim();
                String zohartime=e2.getText().toString().trim();
                String asartime=e3.getText().toString().trim();
                String maghribtime=e4.getText().toString().trim();
                String ishatime=e5.getText().toString().trim();
                String jumatime=e6.getText().toString().trim();
                prayerTImings.setFajartime(fajartime);
                prayerTImings.setZohartime(zohartime);
                prayerTImings.setAsartime(asartime);
                prayerTImings.setMaghribtime(maghribtime);
                prayerTImings.setIshatime(ishatime);
                prayerTImings.setJumatime(jumatime);
                databasePrayerTiming.child(prayerTImings.getPhoneNumber()).setValue(prayerTImings);

                databaseMosqueTiming.child(prayerTImings.getPhoneNumber()).child("prayerTimmings").setValue(prayerTImings);
                Toast.makeText(this,"Prayer Timings Added",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(FirstTimeUpdateTiming.this,DefaultHomeScreen.class);
                intent.putExtra("user_phone",myphone);
                intent.putExtra("user_pass",mypass);
                startActivityForResult(intent,10);
                finish();
                break;

        }
    }
}
