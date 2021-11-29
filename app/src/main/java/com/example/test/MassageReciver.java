package com.example.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MassageReciver extends BroadcastReceiver {
    private static final String TAG = "SMSBROADCAST";
    private static String SMS_RECEIVER = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i(TAG, "Intent Recived"+intent.getAction());
        if(intent.getAction()!=null) {
            if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)
        || intent.getAction().equals(SMS_RECEIVER)) {
                Log.i(TAG, "SMS RECIVED");
            } else {
                Intent intent1 = new Intent(context, NotificationIntentService.class);
                context.startService(intent1);
            }
        }
    }
}
