package com.example.mycontacts;

import android.content.Intent;
import android.os.Bundle;

import com.example.mycontacts.data.MyDbHandler;
import com.example.mycontacts.model.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView noContact;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDbHandler db = new MyDbHandler(this);
        listView = findViewById(R.id.list);
        final ArrayList<Contact> allContact = db.getAllContact();
        for (Contact c: allContact) {
            Log.d("delete", c.getPhoneNumber());
        }
        noContact = findViewById(R.id.noContact);
        if(allContact.size() == 0) {
            noContact.setText("No contacts to show, click on + to add.");
        }
        ContactViewAdapter adapter = new ContactViewAdapter(this, allContact);
        listView.setAdapter(adapter);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ContactEdit.class);
                startActivity(i);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact.getMyName = allContact.get(position).getName();
                Contact.getMYNumber = allContact.get(position).getPhoneNumber();
                Contact.ID = allContact.get(position).getId();
//                Log.d("string", Contact.getMyName);
//                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this, EditContact.class);
                startActivity(i);
            }
        });
    }
}