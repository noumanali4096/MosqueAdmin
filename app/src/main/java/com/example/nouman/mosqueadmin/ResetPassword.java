package com.example.nouman.mosqueadmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ResetPassword extends AppCompatActivity {

    EditText old,newpass,confirm;
    Button submit;
    DBHelper dbHelper;
    DatabaseReference databasemosqueadmin;
    MosqueAdmin mosqueAdmin;
    String ph;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        mosqueAdmin=new MosqueAdmin();
        dbHelper=new DBHelper(this);
        List<MosqueAdmin> contacts = dbHelper.getAllContacts();
        Intent intent2 = getIntent();
       // final String myphone = contacts.get(0).getPhone();

        Bundle bundle = intent2.getExtras();
        String myphone = bundle.getString("user_phone");
        String mypass=bundle.getString("user_pass");
        mosqueAdmin.setPhone(myphone);
        mosqueAdmin.setPassword(mypass);
        databasemosqueadmin = FirebaseDatabase.getInstance().getReferenceFromUrl
                ("https://azzan-f7f08.firebaseio.com/mosqueadmin");

        old=(EditText) findViewById(R.id.oldpasword);
        newpass=(EditText) findViewById(R.id.newpassword);
        confirm=(EditText) findViewById(R.id.confirmpasword);

       // mosqueAdmin =dbHelper.getAdmin(myphone);
        old.setText(mosqueAdmin.getPassword());
        submit=(Button) findViewById(R.id.submitbutton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword(mosqueAdmin);

            }
        });

    }
    private void resetPassword(MosqueAdmin obj)
    {
        //Toast.makeText(this,"BUTTON CLICKED",Toast.LENGTH_LONG).show();
        String newpasword=newpass.getText().toString().trim();
        String confirmpass=confirm.getText().toString().trim();
        if(newpasword.equals(confirmpass)){
            obj.setPassword(newpasword);
            databasemosqueadmin.child(obj.getPhone()).setValue(obj);
            Toast.makeText(this,"Pasword updated",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(ResetPassword.this,FirstTimeUpdateTiming.class);
            intent.putExtra("user_phone",obj.getPhone());
            intent.putExtra("user_pass",obj.getPassword());
            startActivityForResult(intent,2);
            //startActivity(intent);
        }

    }
}
