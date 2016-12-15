package com.example.kornelia.sms_client;

import android.app.Application;
import android.content.Intent;

/**
 * Created by kornelia on 14.12.16.
 */

public class MainApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        startService(new Intent(this, MyService.class));
    }
}
