package com.example.test.UnusedActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test.R;

import java.util.Locale;

public class Speak_Activity extends AppCompatActivity {
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
                if(i == TextToSpeech.SUCCESS){//checks if the text to speak is ready to speak
                    int result = TTS.setLanguage(Locale.UK);//language setter

                    if(result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS", "Language not supported");//if the user inserted wrong info in the edit text
                    } else {
                        buttonS.setEnabled(true);//sets the button to speak to true
                    }
                } else {
                    Log.e("TTS", "Initailiziation failed");
                }
            }
        });
        EditText =findViewById(R.id.edit_text);
        buttonS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//on click of the button for it to speak
                speak();
            }
        });
    }

    public void speak() {
        String text = EditText.getText().toString();
        TTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);//takes the text and speaks it using the language that is preset
    }

    @Override
    protected void onDestroy() {//if the app is finished it makes the speaking stop
        if(TTS !=null) {
            TTS.stop();
            TTS.shutdown();
        }
        super.onDestroy();
    }
}