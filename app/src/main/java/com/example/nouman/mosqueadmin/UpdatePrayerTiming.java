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

import java.util.Calendar;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UpdatePrayerTiming extends AppCompatActivity implements View.OnClickListener {

    EditText fajar,zohar,asar,maghrib,isha,juma;
    Button updatefajartime,updatezohartime,updateasartime,updatemaghribtime,
            updateishatime,updatejumatime;
    EditText e1,e2,e3,e4,e5,e6;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;

    PrayerTImings prayerTImings;
    DBHelper dbHelper;
    DatabaseReference databasePrayerTiming;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_prayer_timing);

        e1 = (EditText) findViewById(R.id.fajar_editText);
        e2 = (EditText) findViewById(R.id.zohar_editText);
        e3 = (EditText) findViewById(R.id.asar_editText);
        e4 = (EditText) findViewById(R.id.maghrib_editText);
        e5 = (EditText) findViewById(R.id.isha_editText);
        e6 = (EditText) findViewById(R.id.juma_editText);

        dbHelper=new DBHelper(this);
        List<MosqueAdmin> contacts = dbHelper.getAllContacts();
        final String myphone = contacts.get(0).getPhone();

        prayerTImings =new PrayerTImings();
        prayerTImings.setPhoneNumber(myphone);

        databasePrayerTiming = FirebaseDatabase.getInstance().getReferenceFromUrl
                ("https://azzan-f7f08.firebaseio.com/prayertiming");

        Query fireBaseQuery = databasePrayerTiming.orderByChild("phoneNumber").equalTo(myphone);

        fireBaseQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //if (dataSnapshot.exists()) {

                // dataSnapshot is the "issue" node with all children with id 0
                for (DataSnapshot mosqueadminSnapshot : dataSnapshot.getChildren()) {
                    prayerTImings =  mosqueadminSnapshot.getValue(PrayerTImings.class);
                    e1.setText(prayerTImings.getFajartime());
                    e2.setText(prayerTImings.getZohartime());
                    e3.setText(prayerTImings.getAsartime());
                    e4.setText(prayerTImings.getMaghribtime());
                    e5.setText(prayerTImings.getIshatime());
                    e6.setText(prayerTImings.getJumatime());

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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

        updatefajartime=(Button) findViewById(R.id.button1);
        updatezohartime=(Button) findViewById(R.id.button2);
        updateasartime=(Button) findViewById(R.id.button3);
        updatemaghribtime=(Button) findViewById(R.id.button4);
        updateishatime=(Button) findViewById(R.id.button5);
        updatejumatime=(Button) findViewById(R.id.button6);

        updatefajartime.setOnClickListener(this);
        updatezohartime.setOnClickListener(this);
        updateasartime.setOnClickListener(this);
        updatemaghribtime.setOnClickListener(this);
        updateishatime.setOnClickListener(this);
        updatejumatime.setOnClickListener(this);
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

        }
        //////////////////////////////////////////////////////
        switch (v.getId()) {
            case R.id.button1:
                updatefajartime=(Button) v.findViewById(R.id.button1);
                String fajartime=fajar.getText().toString().trim();
                prayerTImings.setFajartime(fajartime);
                databasePrayerTiming.child(prayerTImings.getPhoneNumber()).setValue(prayerTImings);
                break;


            case R.id.button2:
                updatezohartime=(Button) v.findViewById(R.id.button2);
                String zohartime=zohar.getText().toString().trim();
                prayerTImings.setZohartime(zohartime);
                databasePrayerTiming.child(prayerTImings.getPhoneNumber()).setValue(prayerTImings);
                break;

            case R.id.button3:
                updateasartime=(Button) v.findViewById(R.id.button3);
                String asartime=asar.getText().toString().trim();
                prayerTImings.setAsartime(asartime);
                databasePrayerTiming.child(prayerTImings.getPhoneNumber()).setValue(prayerTImings);
                break;

            case R.id.button4:
                updatemaghribtime=(Button) v.findViewById(R.id.button4);
                String maghribtime=maghrib.getText().toString().trim();
                prayerTImings.setMaghribtime(maghribtime);
                databasePrayerTiming.child(prayerTImings.getPhoneNumber()).setValue(prayerTImings);
                break;

            case R.id.button5:
                updateishatime=(Button) v.findViewById(R.id.button5);
                String ishatime=isha.getText().toString().trim();
                prayerTImings.setIshatime(ishatime);
                databasePrayerTiming.child(prayerTImings.getPhoneNumber()).setValue(prayerTImings);
                break;

            case R.id.button6:
                updatejumatime=(Button) v.findViewById(R.id.button6);
                String jumatime=juma.getText().toString().trim();
                prayerTImings.setJumatime(jumatime);
                databasePrayerTiming.child(prayerTImings.getPhoneNumber()).setValue(prayerTImings);
                break;
        }

    }
}
