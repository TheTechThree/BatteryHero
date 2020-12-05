package com.example.android.batteryhero;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editBatteryLevel;
    TextView setLevel;
    TextView currentLevel;
    String threshold;
    float batteryPct;
    private PendingIntent pendingIntent;
    private AlarmManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editBatteryLevel = findViewById(R.id.et_battery_level);
        setLevel = findViewById(R.id.tv_current_set_level);
        currentLevel = findViewById(R.id.tv_current_level);

        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
    }


    public void setBatteryLevel(View view) {
        threshold = editBatteryLevel.getText().toString();
        setLevel.setText("Current Set Level: " + threshold + "%");
    }


    public void getBatLevel(View view) {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = registerReceiver(null, ifilter);

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        batteryPct = level * 100 / (float)scale;
        currentLevel.setText("Current Battery Level: " + String.valueOf(batteryPct) + "%");
    }


    public void startBackground(View view) {
        manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        int interval = 20000;
        manager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(this, "Background Process Started", Toast.LENGTH_LONG).show();
    }
}