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

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Massage_Activity> {
    private Context context;
    private int resource;
    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Massage_Activity> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view== null){
            view = LayoutInflater.from(context).inflate(resource,parent,false);
        }
        Massage_Activity msg = getItem(position);
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
            imageView.setImageResource(msg.getResid());
            textView.setText(msg.getDescription());
        }
        return view;
    }
}
