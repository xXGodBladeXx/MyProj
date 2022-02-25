package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test.Message;
import com.example.test.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
//a conector from the data to a view
public class CustomAdapter extends ArrayAdapter<Message> {
    private Context context;//view for what i want to show
    private int resource;//id for xml in which order the arraylist will be shown

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://basels-project-default-rtdb.europe-west1.firebasedatabase.app/");
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String UID = mAuth.getUid();
    DatabaseReference myRef = database.getReference("Users/" + UID + "/favorites");

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Message> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
    }
    //a method that is called when the item is being created in the method the item is inflated by LayoutInflater
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view== null){
            view = LayoutInflater.from(context).inflate(resource,parent,false);//shows up a new item
        }
        Message msg = getItem(position);//a counter for the resource
        if(msg!=null){
            TextView desc = view.findViewById(R.id.msg);
            TextView sender = view.findViewById(R.id.sender);
            Button favbut= view.findViewById(R.id.favbut);

            desc.setText(msg.getDescription());
            sender.setText(msg.getSender());
            favbut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!msg.getfav()) {
                        msg.setFav(true);
                        myRef.push().setValue(msg);
                        Toast.makeText(context, "Added to favorites", Toast.LENGTH_LONG).show();
                    }
                    else {
                        msg.setFav(false);
                        myRef.child("favoritesid").removeValue();
                        Toast.makeText(context, "Removed from favorites", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        return view;
    }

}
