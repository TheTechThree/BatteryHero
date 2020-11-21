package com.example.android.batteryhero;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editBatteryLevel;
    TextView setLevel;
    TextView currentLevel;
    Button btnAlert;
    String threshold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editBatteryLevel = findViewById(R.id.et_battery_level);
        setLevel = findViewById(R.id.tv_current_set_level);
        currentLevel = findViewById(R.id.tv_current_level);

    }

    public void setBatteryLevel(View view) {
        threshold = editBatteryLevel.getText().toString();
        setLevel.setText("Current Set Level: " + threshold + "%");
    }

    public void getCurrentBatteryLevel(View view) {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = registerReceiver(null, ifilter);

        assert batteryStatus != null;
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float batteryPct = level * 100 / (float)scale;
        String currentLevelString = String.valueOf(batteryPct);

        currentLevel.setText("Current Battery Level: " + currentLevelString + "%");


        if(batteryPct <= Float.parseFloat(threshold)) {
            btnAlert = (Button) findViewById(R.id.btn_get_level);

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            builder.setMessage("Battery at danger zone.")
                    .setNegativeButton("Close", null);

            AlertDialog alert = builder.create();
            alert.show();
        }

        closeBackGroundApps(getApplicationContext());
    }

    public void closeBackGroundApps(Context c) {
        List<ApplicationInfo> packages;
        PackageManager pm;
        pm = getPackageManager();
        //get a list of installed apps. maybe get running apps instead
        packages = pm.getInstalledApplications(0);

        ActivityManager mActivityManager = (ActivityManager)c.getSystemService(Context.ACTIVITY_SERVICE);

        for (ApplicationInfo packageInfo : packages) {
            Log.d("Run", String.valueOf(packageInfo.flags)); // certain packageInfo.flags numbers for each name
            if(!((packageInfo.flags) ==8388608)) { continue; } //maybe if 'google' 'example' and 'codepath' are in the name
//            if(packageInfo.packageName.contains("android")) { continue; }
            Log.d("Run", String.valueOf(packageInfo.flags & ApplicationInfo.FLAG_SYSTEM));
            if(packageInfo.packageName.equals("com.example.android.batteryhero")) { continue; }
//            Log.d("Run", "Got to point 3");
            mActivityManager.killBackgroundProcesses(packageInfo.packageName);
            Log.d("Run", "Killed " + packageInfo.packageName);
        }
    }
}