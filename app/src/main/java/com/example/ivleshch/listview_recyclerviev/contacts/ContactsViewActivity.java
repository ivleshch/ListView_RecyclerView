package com.example.ivleshch.listview_recyclerviev.contacts;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ivleshch.listview_recyclerviev.R;
import com.example.ivleshch.listview_recyclerviev.broadcastreceivers.Receivers;

public class ContactsViewActivity extends AppCompatActivity {

    private IntentFilter receiverFilter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
    private Receivers receiver = new Receivers();

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

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, receiverFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

}
