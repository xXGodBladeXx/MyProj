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
    private static String SMS = "android.provider.Telephony.SMS_RECEIVER";
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://basels-project-default-rtdb.europe-west1.firebasedatabase.app/");

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String UID =mAuth.getUid();
    DatabaseReference myRef = database.getReference("User/"+UID);



    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "UID: "+UID, Toast.LENGTH_SHORT).show();

        Log.d("ON_RECEIVE_MESSAGE_BASEL", "onReceive: intent");
        if(intent.getAction().equals(SMS)){
            Bundle bundle = intent.getExtras();
            if(bundle != null){
                Object[] pdus = (Object[]) bundle.get("pdus");
                final SmsMessage[] messages = new SmsMessage[pdus.length];
                for(int i =0; i < pdus.length; i++){
                    myRef.push().setValue(new Message("basel","hey what are you doing?",false));
                    messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                    Log.d("hello", "onReceive: hello");
                }
                String[] MSGDISC = new String[pdus.length];
                if(messages.length >-1){// && button == true
                    for(int i= 0; i< messages.length; i++){
                        MSGDISC[i] = messages[0].getMessageBody();
                    }//for
                }//if
            }//if
        }//if
    }//onreceive
}//class
