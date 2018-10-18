package com.example.nouman.mosqueadmin;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText phone,pasword;
    Button login;
    String phone_number,pass;
    ProgressDialog progressDialog;
    MosqueAdmin mosqueAdmin;
    DatabaseReference databasemosqueadmin;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=new DBHelper(this);


        databasemosqueadmin = FirebaseDatabase.getInstance().getReferenceFromUrl("https://azzan-f7f08.firebaseio.com/mosqueadmin");
        phone=(EditText) findViewById(R.id.phone);
        pasword=(EditText) findViewById(R.id.password);
        login=(Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 phone_number=phone.getText().toString().trim();
                 pass=pasword.getText().toString().trim();

                searchMosqueadmin(phone_number);


            }
        });
    }


    private  void searchMosqueadmin(final String phone) {

        progressDialog = ProgressDialog.show(this, "","Please Wait...", true);
        Query fireBaseQuery = databasemosqueadmin.orderByChild("phone").equalTo(phone);

        fireBaseQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //if (dataSnapshot.exists()) {

                    // dataSnapshot is the "issue" node with all children with id 0
                    for (DataSnapshot mosqueadminSnapshot : dataSnapshot.getChildren()) {
                       mosqueAdmin =  mosqueadminSnapshot.getValue(MosqueAdmin.class);

                    }
                    if(mosqueAdmin.getPhone().equals(phone_number) && mosqueAdmin.getPassword().equals(pass))
                    {

                        progressDialog.dismiss();
                       // MosqueAdmin ma=new MosqueAdmin();
                        //ma=dbHelper.getAdmin(mosqueAdmin.getPhone());
                        //if(ma!=null){

                        //}
                        //else{
                          //  dbHelper.deleteTable();
                            dbHelper.addAdmin(mosqueAdmin);
                            Toast.makeText(MainActivity.this, "added into db", Toast.LENGTH_LONG).show();
                            Toast.makeText(MainActivity.this, "LogedIn", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(MainActivity.this,ResetPassword.class);

                            intent.putExtra("user_phone",phone_number);
                            intent.putExtra("user_pass",pass);
                            startActivityForResult(intent,1);

                        //}


// Insert the new row, returning the primary key value of the new row


                    }
                    else
                    {
                        progressDialog.dismiss();

                        Toast.makeText(MainActivity.this, "Not found or invalid credentials", Toast.LENGTH_LONG).show();



                    }
              //  }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}
