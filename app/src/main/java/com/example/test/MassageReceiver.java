package com.example.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.speech.tts.TextToSpeech;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Locale;

/*public class MassageReceiver extends BroadcastReceiver {
    private TextToSpeech TTS;
    public void onReceive(Context context, Intent intent) {
        TTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

            }
        });

        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) {
            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                String massage = smsMessage.getMessageBody();
                TTS.speak(massage, TextToSpeech.QUEUE_FLUSH, null);
            }
        }
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

    }
}*/
