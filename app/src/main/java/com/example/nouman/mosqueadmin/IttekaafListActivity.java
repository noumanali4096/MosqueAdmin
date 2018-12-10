package com.example.nouman.mosqueadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class IttekaafListActivity extends AppCompatActivity {

    DatabaseReference databaseittekaaf;
    ListView listviewIteekaaf;
    List<ApproveIttekaafModel> IttekaafLists;
    String myphone;
    Toolbar toolbar;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ittekaaf_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        textView = (TextView) findViewById(R.id.pending_textView);
        textView.setVisibility(View.INVISIBLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
        Intent intent2 = getIntent();
        Bundle bundle = intent2.getExtras();
        myphone = bundle.getString("user_phone");
        listviewIteekaaf = (ListView) findViewById(R.id.Ittekaaflist);
        IttekaafLists = new ArrayList<>();
        databaseittekaaf = FirebaseDatabase.getInstance().getReferenceFromUrl("https://azzan-f7f08.firebaseio.com/IttekaafAppointment");
        databaseittekaaf=databaseittekaaf.child(myphone);
        listviewIteekaaf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                ApproveIttekaafModel approveIttekaafModel;
                approveIttekaafModel= IttekaafLists.get(position);



                Intent myIntent = new Intent(IttekaafListActivity.this, IttekaafAcceptedRejected.class);
                myIntent.putExtra("userName", approveIttekaafModel.getName());
                myIntent.putExtra("userPhoneNo", approveIttekaafModel.getPhoneNo());


                myIntent.putExtra("mosqueId",myphone);//mosqueid
                startActivity(myIntent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseittekaaf.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                IttekaafLists.clear();

                for(DataSnapshot mosqSnapshot: dataSnapshot.getChildren()) {

                    ApproveIttekaafModel obj = mosqSnapshot.getValue(ApproveIttekaafModel.class);
                    if(obj!=null && obj.getStatus().equals("pending"))
                    {
                        IttekaafLists.add(obj);
                    }

                }

                if(!IttekaafLists.isEmpty()) {
                    IttekaafAdapter adapter = new IttekaafAdapter(IttekaafListActivity.this, IttekaafLists);

                    listviewIteekaaf.setAdapter(adapter);
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
