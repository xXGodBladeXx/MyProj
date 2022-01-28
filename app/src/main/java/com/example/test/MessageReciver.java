package com.example.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MessageReciver extends BroadcastReceiver {

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://basels-project-default-rtdb.europe-west1.firebasedatabase.app/");
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
   String UID =mAuth.getUid();
    DatabaseReference myRef = database.getReference("User/"+UID);

    private static String SMS ="android.provider.Telephony.SMS_RECEIVED";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("ON_RECEIVE_MESSAGE_BASEL", "Intent");
     //   myRef.push().setValue(new Message("basel","hello",false));
        if(intent != null && intent.getAction() !=null){
            Log.i(SMS, "Message recieved: " + messages[0].getMessageBody());
        if(intent.getAction().equals(SMS)) {

            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Log.i(SMS, "Message recieved: " + messages[0].getMessageBody());
                Object[] pdus = (Object[]) bundle.get("pdus");
                final SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    myRef.push().setValue(new Message("basel","hello",false));
                }
                if (messages.length > -1) {
                    Log.i(SMS, "Message recieved: " + messages[0].getMessageBody());
                    myRef.push().setValue(new Message("basel","hello",false));
                }
            }

            Toast.makeText(context, "OK", Toast.LENGTH_LONG).show();
        }
        }
    }
}//class

