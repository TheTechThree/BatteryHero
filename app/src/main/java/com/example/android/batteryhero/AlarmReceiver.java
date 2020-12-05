package com.example.android.batteryhero;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

public class AlarmReceiver extends BroadcastReceiver {

    Button btnAlert;
    MainActivity mainActivity = new MainActivity();

    @Override
    public void onReceive(Context context, Intent intent) {

        // Calculate the battery charged percentage
        float percentage = mainActivity.getBatLevel();
        Log.d("Rann", "this is the percentage " + String.valueOf(percentage));

        if (percentage >= 50.0) {
              Toast.makeText(context, "Background Apps Closing", Toast.LENGTH_LONG).show();
        }

    }
}