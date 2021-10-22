package com.example.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Locale;

public class MassageReceiver extends BroadcastReceiver {
    TextToSpeech TTS = new TextToSpeech(this,new TextToSpeech.OnInitListener());

    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {
            int result = TTS.setLanguage(Locale.UK);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Language not supported");
            }
        } else {
            Log.e("TTS", "Initailiziation failed");
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                String massage = smsMessage.getMessageBody();
                String text = massage;
                TTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            }
        }
    }
}
