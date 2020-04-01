package com.example.mycontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mycontacts.data.MyDbHandler;
import com.example.mycontacts.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactEdit extends AppCompatActivity {

    Button save;
    EditText name, number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edit);

       final MyDbHandler db = new MyDbHandler(this);
        save = (Button) findViewById(R.id.save);
        name =  findViewById(R.id.name_field);
        number =  findViewById(R.id.number_field);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contact = new Contact();
                String userName = name.getText().toString();
                String userNumber = number.getText().toString();
                if(!isCorrect(userNumber)) {
                    Toast.makeText(ContactEdit.this, "Please enter valid number!!", Toast.LENGTH_LONG).show();
                } else {
                    contact.setPhoneNumber(userNumber);
                    contact.setName(userName);
                    db.addContact(contact);
//                Log.d("number", contact.getPhoneNumber() + contact.getName());
//                Log.d("akki", "contat added");
                    Intent i = new Intent(ContactEdit.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }
    public boolean isCorrect(String userNumber) {
        for (int i = 0; i < userNumber.length(); i++) {
            if(userNumber.charAt(i) < '0' || userNumber.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }
}
