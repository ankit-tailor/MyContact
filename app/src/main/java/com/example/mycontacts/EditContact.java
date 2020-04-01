package com.example.mycontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mycontacts.data.MyDbHandler;
import com.example.mycontacts.model.Contact;

public class EditContact extends AppCompatActivity {

    Button save, delete;
    EditText name, number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        final MyDbHandler db = new MyDbHandler(this);
        save = findViewById(R.id.save_edit);
        delete = findViewById(R.id.delete_edit);
        name = findViewById(R.id.name_field_edit);
        number = findViewById(R.id.number_field_edit);

        name.setText(Contact.getMyName);
        number.setText(Contact.getMYNumber);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact c = new Contact();
                String userName = name.getText().toString();
                String userNumber = number.getText().toString();
                if(!isCorrect(userNumber)) {
                    Toast.makeText(EditContact.this, "Please enter valid number!!", Toast.LENGTH_LONG).show();
                } else {
                    c.setPhoneNumber(number.getText().toString());
                    c.setName(name.getText().toString());
                    c.setId(Contact.ID);
                    db.updateContact(c);
                    String s = "Contact updated successfully";
                    Toast.makeText(EditContact.this, s, Toast.LENGTH_LONG).show();
                    Intent i = new Intent(EditContact.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteContact(Contact.ID);
                Toast.makeText(EditContact.this, "Deleted successfully", Toast.LENGTH_LONG).show();
                Intent i = new Intent(EditContact.this, MainActivity.class);
                startActivity(i);
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
