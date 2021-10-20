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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;

public class Camera_Activity extends AppCompatActivity implements View.OnClickListener {
    private static final int CAMERA_REQUEST = 0;
    private static final int GALLERY_REQUEST = 1;

    private Button buttoncam;
    private Button buttongal;
    private ImageView img;

    private Bitmap pic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        buttoncam = findViewById(R.id.button_cam);
        buttoncam.setOnClickListener(this);
        buttongal = findViewById(R.id.button_gal);
        buttoncam.setOnClickListener(this);
        img = findViewById(R.id.img);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button_cam){
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i,CAMERA_REQUEST);
        }else{
            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i,GALLERY_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST) {
            if (requestCode == RESULT_OK) {
                pic = (Bitmap) data.getExtras().get("data");
                img.setImageBitmap(pic);
            }
        } else {
            if (resultCode == RESULT_OK) {
                Uri targetUri = data.getData();
                try {
                    //Decode an input stream into a bitmap.
                    pic = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                    img.setImageBitmap(pic);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}