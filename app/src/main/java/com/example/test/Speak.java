package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class Speak extends AppCompatActivity {
    private TextToSpeech TTS;
    private EditText EditText;
    private Button buttonS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak);

        buttonS = findViewById(R.id.button_speak);

        TTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i == TextToSpeech.SUCCESS){
                    int result = TTS.setLanguage(Locale.UK);

                    if(result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS", "Language not supported");
                    } else {
                        buttonS.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initailiziation failed");
                }
            }
        });
        EditText =findViewById(R.id.edit_text);
        buttonS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak();
            }
        });//



    }
    public  void onBroadcastSentBtnClicked(View v){
        Intent intent=new Intent();
        intent.setAction(("com.basel.Speak"));
        intent.setFlags(intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        sendBroadcast(intent);
    }

    public void speak() {
        String text = EditText.getText().toString();
        TTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {
        if(TTS !=null) {
            TTS.stop();
            TTS.shutdown();
        }
        super.onDestroy();
    }
}