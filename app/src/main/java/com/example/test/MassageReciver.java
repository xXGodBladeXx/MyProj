package com.example.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.common.internal.Constants;
import com.google.firebase.appindexing.builders.Actions;

import java.util.ArrayList;

public class MassageReciver extends BroadcastReceiver {
    public final static String TAG = "ReadMessageBroadcastReceiver";
    public static final String ACTION_TTS_READ_MESSAGE = "com.example.action.ACTION_TTS_READ_MESSAGE";


    public void ReadMessageBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(MassageReciver.ACTION_TTS_READ_MESSAGE)) {
            Log.v(TAG, "Read messages on tts received trough notification");

            String[] unreadMessages = intent.getStringArrayExtra(Constants.ACTION_LOAD_IMAGE);
            if (unreadMessages != null) {
                Log.v(TAG, "Read messages on tts trough notification received " + unreadMessages.length
                        + " total messages");
                String messageNumber = context.getString(R.string.notification_message_number);

                ArrayList<String> allMessagesToRead = new ArrayList<>(unreadMessages.length);
                for (int i = 0; i < unreadMessages.length; i++) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(messageNumber);
                    stringBuilder.append(" ");
                    stringBuilder.append(i + 1);
                    stringBuilder.append(" ");
                    stringBuilder.append(unreadMessages[i]);
                    stringBuilder.append(" ");
                    allMessagesToRead.add(stringBuilder.toString());
                }

                Log.d(TAG, "Texts to read loud: " + allMessagesToRead);
                Intent speechIntent = new Intent(context, TTS_service.class);
                speechIntent.putStringArrayListExtra(TTS_service.TEXT_TO_READ, allMessagesToRead);
                context.startService(speechIntent);

            }
        }
    }

    private class ACTION_TTS_READ_MESSAGE {
    }

    private class EXTRA_UNREAD_MESSAGES {
    }
}
