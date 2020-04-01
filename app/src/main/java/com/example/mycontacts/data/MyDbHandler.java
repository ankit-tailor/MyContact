package com.example.mycontacts.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.mycontacts.model.Contact;
import com.example.mycontacts.paramams.Params;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {

    public MyDbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + Params.TABLE_NAME + "("
                + Params.KEY_ID + " INTEGER PRIMARY KEY," + Params.KEY_NAME + " TEXT,"
                + Params.KEY_PHONE + " TEXT" + ")";
        Log.d("akki", "Query is running: " + CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addContact(Contact c) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Params.KEY_NAME, c.getName());
        value.put(Params.KEY_PHONE, c.getPhoneNumber());
        db.insert(Params.TABLE_NAME, null, value);
        Log.d("Akki", "Contact inserted successfully");
        db.close();
    }

    public ArrayList<Contact> getAllContact() {
        ArrayList<Contact> allContact = new ArrayList<Contact>();

        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM " + Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);

        if(cursor.moveToFirst()) {
            do {
                Contact c = new Contact();
                c.setId(Integer.parseInt(cursor.getString(0)));
                c.setName(cursor.getString(1));
                c.setPhoneNumber(cursor.getString(2));
                allContact.add(c);
            } while(cursor.moveToNext());
        }
        return allContact;
    }

    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME, contact.getName());
        values.put(Params.KEY_PHONE, contact.getPhoneNumber());

        return db.update(Params.TABLE_NAME, values, Params.KEY_ID + "=?", new String[] {String.valueOf(contact.getId())});
    }

    public void deleteContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Params.TABLE_NAME, Params.KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public int getCount() {
        String count = "SELECT * FROM " + Params.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(count, null);
        return cursor.getCount();
    }
}
