package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class Arraylist_Activity extends AppCompatActivity{

    //the object of the view - design
    private ListView myListView;
    //the object for the adaptor connection the data to the view
    private CustomAdapter myAdapter;
    //object containing the item to be displayed - Data
    private ArrayList<Message> list;
    private TextToSpeech TTS;


    FirebaseDatabase database = FirebaseDatabase.getInstance("https://basels-project-default-rtdb.europe-west1.firebasedatabase.app/");
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String UID =mAuth.getUid();
    DatabaseReference myRef = database.getReference("User/" + UID + "/Messages");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arraylist);
        list = new ArrayList<>();

        //reference to the list view so it can programed
        myListView = findViewById(R.id.listitem);
        // connect adapter with Data
        myAdapter = new CustomAdapter(this,R.layout.massages_row, list);
        //connect adapter with view
        myListView.setAdapter(myAdapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                TTS = new TextToSpeech(Arraylist_Activity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int i) {
                        if(i == TextToSpeech.SUCCESS){//checks if the text to speak is ready to speak
                            TTS.setLanguage(Locale.UK);//language setter
                                String text = list.get(pos).getSender()+" sent "+list.get(pos).getDescription();
                                TTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                        }
                    }
                });
            }
        });

        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                list.remove(i);
                myAdapter.notifyDataSetChanged();
                return false;
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Message msg = dataSnapshot.getValue(Message.class);
                    list.add(msg);
                    myAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}