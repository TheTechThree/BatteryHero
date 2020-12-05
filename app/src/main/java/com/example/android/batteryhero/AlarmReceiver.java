package com.example.android.batteryhero;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // Calculate the battery charged percentage
        Log.d("Running Battery Check", "Should be displaying battery check ");
        Toast.makeText(context, "Checking Battery Level!", Toast.LENGTH_LONG).show();

    }
}