package com.example.ivleshch.listview_recyclerviev.broadcastreceivers;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ivleshch.listview_recyclerviev.data.MyApplication;

/**
 * Created by Ivleshch on 08.11.2016.
 */
public class Receivers extends BroadcastReceiver {

    private Boolean firstTime = true;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (MyApplication.isActivityVisible()) {
            String action = intent.getAction();
            Bundle extras = intent.getExtras();

            switch (action) {
                case "android.bluetooth.adapter.action.STATE_CHANGED": {
                    bluetoothStateChanged(extras.getInt("android.bluetooth.adapter.extra.STATE"), context);
                    break;
                }
                case "android.intent.action.HEADSET_PLUG": {
                    if (!firstTime) {
                        headsetStateChanged(extras.getInt("state"), context);
                    } else {
                        firstTime = false;
                    }
                    break;
                }
                case "android.intent.action.ACTION_POWER_CONNECTED": {
                    Toast.makeText(context, "Charging ON", Toast.LENGTH_SHORT).show();
                    break;
                }
                case "android.intent.action.ACTION_POWER_DISCONNECTED": {
                    Toast.makeText(context, "Charging OFF", Toast.LENGTH_SHORT).show();
                    break;
                }
                case "android.net.conn.CONNECTIVITY_CHANGE": {
                    if (!intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, Boolean.FALSE)) {
                        Toast.makeText(context, "Internet ON", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Internet OFF", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }
        }
    }

    private void headsetStateChanged(int state, Context context) {
        switch (state) {
            case 0:
                Toast.makeText(context, "Headset unplugged", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(context, "Headset plugged", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    private void bluetoothStateChanged(int state, Context context) {
        switch (state) {
            case BluetoothAdapter.STATE_OFF:
                Toast.makeText(context, "Bluetooth OFF", Toast.LENGTH_SHORT).show();
                break;
            case BluetoothAdapter.STATE_ON:
                Toast.makeText(context, "Bluetooth ON", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
