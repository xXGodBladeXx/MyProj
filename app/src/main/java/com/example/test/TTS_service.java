package com.example.test;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class TTS_service extends Service implements TextToSpeech.OnInitListener {
    public final static String TAG = "TextToSpeechService";

    public static final String TEXT_TO_READ = "text";
    private final String UTTERANCE_ID = "FINISHED_PLAYING";
    private final int MULTI_LINE = 2;

    private TextToSpeech tts;
    private ArrayList<String> texts;
    private boolean isInit;

    private UtteranceProgressListener utteranceProgressListener = new UtteranceProgressListener() {
        @Override
        public void onStart(String utteranceId) {

        }

        @Override
        public void onDone(String utteranceId) {
            if (utteranceId.equals(UTTERANCE_ID)) {
                stopSelf();
            }
        }

        @Override
        public void onError(String utteranceId) {
            stopSelf();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        tts = new TextToSpeech(getApplicationContext(), this);
        tts.setOnUtteranceProgressListener(utteranceProgressListener);
        Log.d(TAG, "onCreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        texts = intent.getStringArrayListExtra(TTS_service.TEXT_TO_READ);

        if (isInit) {
            speak();
        }

        return TTS_service.START_NOT_STICKY;
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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onInit(int status) {
        Log.d(TAG, "onInit");
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.getDefault());
            if (result != TextToSpeech.LANG_MISSING_DATA
                    && result != TextToSpeech.LANG_NOT_SUPPORTED) {
                speak();
                isInit = true;
            }
        }
    }

    private void speak() {
        if (tts != null) {

            // Speak with 3 parameters deprecated but necessary on pre 21 version codes
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // This is a single message
                String utteranceId = null;
                if (texts.size() < MULTI_LINE) {
                    // If is a single message this needs to be the last one
                    utteranceId = UTTERANCE_ID;
                }
                tts.speak(texts.get(0), TextToSpeech.QUEUE_FLUSH, null, utteranceId);
                if (texts.size() >= MULTI_LINE) {
                    for (int i = 1; i < texts.size(); i++) {
                        if (texts.size() - 1 == i) {
                            // If is the last message add the id
                            utteranceId = UTTERANCE_ID;
                        }
                        tts.speak(texts.get(i), TextToSpeech.QUEUE_ADD, null, utteranceId);
                    }
                }
            } else {
                HashMap<String, String> myHashAlarm = null;
                if (texts.size() < MULTI_LINE) {
                    myHashAlarm = new HashMap<>();
                    myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, UTTERANCE_ID);
                }
                tts.speak(texts.get(0), TextToSpeech.QUEUE_FLUSH, myHashAlarm);
                if (texts.size() >= MULTI_LINE) {
                    for (int i = 1; i < texts.size(); i++) {
                        if (texts.size() - 1 == i) {
                            // If is the last message add the id
                            myHashAlarm = new HashMap<>();
                            myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,
                                    UTTERANCE_ID);
                        }
                        tts.speak(texts.get(i), TextToSpeech.QUEUE_ADD, myHashAlarm);
                    }
                }
            }
        }
    }
}
