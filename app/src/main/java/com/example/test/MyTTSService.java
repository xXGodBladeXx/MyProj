package com.example.test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.EditText;

import java.util.Locale;

public class MyTTSService extends Service implements TextToSpeech.OnInitListener{
    private static final String TAG = "SMSREADER";
    private TextToSpeech tts;
    public MyTTSService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        tts = new TextToSpeech(getApplicationContext(), this);
        Log.d(TAG, "onCreate");

    }

    @Override
    public void onInit(int i) {
        if(i == TextToSpeech.SUCCESS){//checks if the text to speak is ready to speak
            int result = tts.setLanguage(Locale.UK);//language setter

            if(result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS", "Language not supported");//if the user inserted wrong info in the edit text
            }
        } else {
            Log.e("TTS", "Initailiziation failed");
        }

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");

        speak("BASEL SWAID");

        return TTS_service.START_NOT_STICKY;
    }
    public void speak(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);//takes the text and speaks it using the language that is preset
    }
    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

}