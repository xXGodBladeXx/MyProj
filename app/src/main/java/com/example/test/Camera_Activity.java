package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;

public class Camera_Activity extends AppCompatActivity implements View.OnClickListener {
    private static final int CAMERA_REQUEST = 0;//a static variable which saves a unique number for the request of the camera
    private static final int GALLERY_REQUEST = 1;//a static variable which saves a unique number for the request of the gallery

    private Button buttoncam;
    private Button buttongal;
    private ImageView img;

    private Bitmap pic;//bitmap is a type of a picture memorieser which is built like
    // a table and saves every pixel as a color in the table
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        buttoncam = findViewById(R.id.button_cam);
        buttoncam.setOnClickListener(this);
        buttongal = findViewById(R.id.button_gal);
        buttongal.setOnClickListener(this);
        img = findViewById(R.id.img);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button_cam){//checks which button is pressed
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i,CAMERA_REQUEST);//calls the camera from the phone
        }else{
            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);//uri:its like a url
            // for a site that contains all the pictures that user took
            startActivityForResult(i,GALLERY_REQUEST);//calls the memorized photos that the user took
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST) {//checks if the request was from the camera
            if (resultCode == RESULT_OK) {//checks if the camera request was successful
                pic = (Bitmap) data.getExtras().get("data");//saves the picture data
                img.setImageBitmap(pic);
            }
        } else {//request from gallery
            if (resultCode == RESULT_OK) {//checks if the gallery request was successful
                Uri targetUri = data.getData();//the address that the content provider follows to provide data
                try {
                    //Decode an input stream into a bitmap.
                    pic = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                    //content provider is the main source of data which we can access by content resolver
                    //content resolver is the "client" to get data from the provider
                    img.setImageBitmap(pic);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}