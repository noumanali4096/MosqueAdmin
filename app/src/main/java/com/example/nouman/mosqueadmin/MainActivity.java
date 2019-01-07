package com.example.nouman.mosqueadmin;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=new DBHelper(this);
        mAuth = FirebaseAuth.getInstance();

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

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null)
        {
            Intent intent = new Intent(MainActivity.this,DefaultHomeScreen.class);
            startActivity(intent);
            finish();
        }
        else{
            mAuth.signInAnonymously()
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(MainActivity.this,"sucessful",Toast.LENGTH_LONG);
                            } else {
                                Toast.makeText(MainActivity.this,"sucessful",Toast.LENGTH_LONG);
                            }
                        }
                    });
        }
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
                            dbHelper.addAdmin(mosqueAdmin);
                        Toast.makeText(MainActivity.this, "LogedIn", Toast.LENGTH_LONG).show();
                            if(phone_number.equals(pass)) {

                                Intent intent = new Intent(MainActivity.this, ResetPassword.class);
                                intent.putExtra("user_phone", phone_number);
                                intent.putExtra("user_pass", pass);
                                startActivityForResult(intent, 1);
                                finish();
                            }
                            else{
                                Intent intent = new Intent(MainActivity.this, DefaultHomeScreen.class);
                                intent.putExtra("user_phone",phone_number);
                                intent.putExtra("user_pass",pass);
                                startActivityForResult(intent, 1);
                                finish();
                            }



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
