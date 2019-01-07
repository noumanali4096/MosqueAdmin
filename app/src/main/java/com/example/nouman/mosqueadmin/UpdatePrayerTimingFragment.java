package com.example.nouman.mosqueadmin;

import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

import java.util.Calendar;


public class UpdatePrayerTimingFragment extends Fragment {
    View mView;
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
    DatabaseReference databaseMosqueTiming;
    String myphone;
    String mypass;


    public UpdatePrayerTimingFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView= inflater.inflate(R.layout.fragment_update_prayer_timing, container, false);
        e1 = (EditText) mView.findViewById(R.id.fajar_editText);
        e2 = (EditText) mView.findViewById(R.id.zohar_editText);
        e3 = (EditText) mView.findViewById(R.id.asar_editText);
        e4 = (EditText) mView.findViewById(R.id.maghrib_editText);
        e5 = (EditText) mView.findViewById(R.id.isha_editText);
        e6 = (EditText) mView.findViewById(R.id.juma_editText);
        return mView ;
    }







}
