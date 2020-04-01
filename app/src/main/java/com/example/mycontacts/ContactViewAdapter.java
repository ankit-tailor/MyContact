package com.example.mycontacts;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mycontacts.model.Contact;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ContactViewAdapter extends ArrayAdapter<Contact> {
    public ContactViewAdapter(Activity context, ArrayList<Contact> contact) {
        super(context, 0, contact);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if(listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(
                    R.layout.contact_list,
                    parent,
                    false
            );
        }
        Contact currentContact = getItem(position);
        TextView name = listView.findViewById(R.id.name);
        name.setText(currentContact.getName());
        TextView number = listView.findViewById(R.id.number);
        number.setText(currentContact.getPhoneNumber());

        return listView;
    }
}
