package com.example.ivleshch.listview_recyclerviev.contacts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ivleshch.listview_recyclerviev.R;

public class ContactsViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_view);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_contactsView, new ContactsViewFragment())
                    .commit();
        }
    }
}
