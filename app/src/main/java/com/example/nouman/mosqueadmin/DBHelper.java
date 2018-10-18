package com.example.nouman.mosqueadmin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mosqueadmin";
    private static final String TABLE_CONTACTS = "adminCredentials";
    private static final String KEY_Password = "password";
    private static final String KEY_PH_NO = "phone_number";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                 + KEY_PH_NO + " TEXT,"
                + KEY_Password + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public void deleteTable(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_CONTACTS,null,null);
        db.close();
    }
    public void addAdmin(MosqueAdmin contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PH_NO, contact.getPhone()); // Contact Name
        values.put(KEY_Password, contact.getPassword()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    MosqueAdmin getAdmin(String ph) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {
                        KEY_PH_NO, KEY_Password }, KEY_PH_NO + "=?",
                new String[] { String.valueOf(ph) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        MosqueAdmin contact = new MosqueAdmin(cursor.getString(0),
                cursor.getString(1));
        // return contact
        return contact;
    }

    public List<MosqueAdmin> getAllContacts() {
        List<MosqueAdmin> contactList = new ArrayList<MosqueAdmin>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MosqueAdmin contact = new MosqueAdmin();

                contact.setPhone(cursor.getString(0));
                contact.setPassword(cursor.getString(1));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }


}
