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
            switch (packageInfo.packageName) {
                case "com.example.android.batteryhero":
                case "com.android.cts.priv.ctsshim":
                case "com.google.android.ext.services":
                case "com.android.providers.telephony":
                case "com.google.android.googlequicksearchbox":
                case "com.android.providers.calendar":
                case "com.android.providers.media":
                case "com.google.android.onetimeinitializer":
                case "com.google.android.ext.shared":
                case "com.android.protips":
                case "com.android.documentsui":
                case "com.android.externalstorage":
                case "com.android.htmlviewer":
                case "com.android.companiondevicemanager":
                case "com.android.mms.service":
                case "com.android.providers.downloads":
                case "com.google.android.apps.messaging":
                case "com.google.android.configupdater":
                case "com.android.defcontainer":
                case "com.google.ar.core":
                case "com.android.providers.downloads.ui":
                case "com.android.vending":
                case "com.android.pacprocessor":
                case "com.android.certinstaller":
                case "com.android.carrierconfig":
                case "android":
                case "com.android.contacts":
                case "com.android.camera2":
                case "com.google.android.apps.inputmethod.hindi":
                case "com.android.egg":
                case "com.android.mtp":
                case "com.android.backupconfirm":
                case "com.google.android.deskclock":
                case "com.android.statementservice":
                case "com.google.android.gm":
                case "com.android.systemui.theme.dark":
                case "com.google.android.setupwizard":
                case "com.android.providers.settings":
                case "com.android.sharedstoragebackup":
                case "com.google.android.music":
                case "com.android.printspooler":
                case "com.android.dreams.basic":
                case "com.android.inputdevices":
                case "com.google.android.dialer":
                case "com.android.bips":
                case "com.android.cellbroadcastreceiver":
                case "com.google.android.webview":
                case "com.android.server.telecom":
                case "com.google.android.syncadapters.contacts":
                case "com.android.keychain":
                case "com.google.android.packageinstaller":
                case "com.google.android.gms":
                case "com.google.android.gsf":
                case "com.google.android.tts":
                case "com.ustwo.lwp":
                case "com.android.calllogbackup":
                case "com.google.android.partnersetup":
                case "com.google.android.apps.wallpaper.nexus":
                case "com.google.android.apps.nexuslauncher":
                case "com.android.proxyhandler":
                case "com.breel.geswallpapers":
                case "org.chromium.webview_shell":
                case "com.google.android.feedback":
                case "com.google.android.printservice.recommendation":
                case "com.android.managedprovisioning":
                case "com.android.providers.partnerbookmarks":
                case "com.android.wallpaper.livepicker":
                case "com.android.netspeed":
                case "com.google.android.sdksetup":
                case "com.google.android.backuptransport":
                case "com.android.storagemanager":
                case "jp.co.omronsoft.openwnn":
                case "com.android.bookmarkprovider":
                case "com.android.settings":
                case "com.google.android.inputmethod.pinyin":
                case "com.android.cts.ctsshim":
                case "com.android.vpndialogs":
                case "com.google.android.apps.wallpaper":
                case "com.android.phone":
                case "com.android.shell":
                case "com.android.wallpaperbackup":
                case "com.android.providers.blockednumber":
                case "com.android.providers.userdictionary":
                case "com.android.emergency":
                case "com.android.location.fused":
                case "com.android.systemui":
                case "com.android.customlocale2":
                case "com.android.development":
                case "com.android.providers.contacts":
                case "com.android.captiveportallogin":
                case "com.google.android.inputmethod.latin":
                    break;
                default:
                    mActivityManager.killBackgroundProcesses(packageInfo.packageName);
                    Log.d("Runn", "Killed " + packageInfo.packageName);
            }
        }
    }
}