package com.example.test.UnusedActivities;

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

import com.example.test.R;

import java.util.List;
//a conector from the data to a view
public class CustomAdapter extends ArrayAdapter<item> {
    private Context context;//view for what i want to show
    private int resource;//id for xml in which order the arraylist will be shown
    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<item> objects) {
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
        item msg = getItem(position);//a counter for the resource
        if(msg!=null){
            ImageView imageView = view.findViewById(R.id.iamge);
            TextView textView = view.findViewById(R.id.textview123);
            Button itembutton = view.findViewById(R.id.itembut);
            itembutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "added", Toast.LENGTH_LONG).show();
                }
            });
            imageView.setImageResource(msg.getResid());//a call for the id of the item to be a different item than before
            textView.setText(msg.getDescription());
        }
        return view;
    }
}
