package com.example.nouman.mosqueadmin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NikkahList extends AppCompatActivity {
    DatabaseReference databasenikah;
    ListView listNikah;
    List<ApproveNikkahModel> nikahLists;
    String myphone;
    Toolbar toolbar;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nikkah_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView = (TextView) findViewById(R.id.textView);
        textView.setVisibility(View.INVISIBLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
        Intent intent2 = getIntent();
        Bundle bundle = intent2.getExtras();
        myphone = bundle.getString("user_phone");
        listNikah = (ListView) findViewById(R.id.nikkahlist);
        nikahLists = new ArrayList<>();
        databasenikah = FirebaseDatabase.getInstance().getReferenceFromUrl("https://azzan-f7f08.firebaseio.com/NikkahAppointment");
        databasenikah=databasenikah.child(myphone);
        listNikah.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                ApproveNikkahModel approveNikkahModel;
                approveNikkahModel= nikahLists.get(position);



                Intent myIntent = new Intent(NikkahList.this, NikkahAcceptRejectActivity.class);
                myIntent.putExtra("userName", approveNikkahModel.getName());
                myIntent.putExtra("Date", approveNikkahModel.getDate());
                myIntent.putExtra("time", approveNikkahModel.getTime());
                myIntent.putExtra("userPhone", approveNikkahModel.getPhoneNo());
                myIntent.putExtra("mosqueId",myphone);//mosqueid
                startActivity(myIntent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databasenikah.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nikahLists.clear();

                for(DataSnapshot mosqSnapshot: dataSnapshot.getChildren()) {

                    ApproveNikkahModel obj = mosqSnapshot.getValue(ApproveNikkahModel.class);
                    if(obj!=null && obj.getStatus().equals("pending"))
                    {
                        nikahLists.add(obj);
                    }

                }

                if(!nikahLists.isEmpty()) {
                    NikkahAdapter adapter = new NikkahAdapter(NikkahList.this, nikahLists);

                    listNikah.setAdapter(adapter);
                    //listNikah.setOnClickListener(new AdapterView.OnItemClickListener());

                }
                else{
                    textView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
