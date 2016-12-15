package com.example.kornelia.sms_client;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by kornelia on 14.12.16.
 */
public class IncomingSMS extends BroadcastReceiver {

    public static String smsBody = "";

    HttpConnect httpConnect = new HttpConnect();

    @Override
    public void onReceive(Context context, Intent intent) {
        smsBody = "";
        SmsMessage[] manager = null;
        Bundle extras = intent.getExtras();
        if(extras!=null) {
            Object[] pdus = (Object[]) extras.get("pdus");
            manager = new SmsMessage[pdus.length];

            for(int i = 0; i < pdus.length;i++) {
                if (Build.VERSION.SDK_INT >= M) {
                    String format = extras.getString("format");
                    manager[i] = SmsMessage.createFromPdu((byte[])pdus[i],format);
                    smsBody += manager[i].getOriginatingAddress() + ": " + manager[i].getDisplayMessageBody() + "\n";
                    httpConnect.makePostRequest(manager[i].getOriginatingAddress(),manager[i].getDisplayMessageBody());
                }
            }

            Toast.makeText(context,smsBody,Toast.LENGTH_LONG).show();
            MainActivity.output.append((CharSequence) IncomingSMS.smsBody);


        }
    }
}
