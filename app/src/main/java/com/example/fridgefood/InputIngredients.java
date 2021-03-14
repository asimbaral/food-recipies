package com.example.fridgefood;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class InputIngredients extends AppCompatActivity {
    private Button backButton;
    private Button getRecipesButton;
    private ImageButton captureCamera;
    private ImageView imageView;
    private ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_ingredients);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent page = new Intent(v.getContext(), MainActivity.class);
                startActivity(page);
            }
        });

        getRecipesButton = findViewById(R.id.getRecipesButton);
        getRecipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent page = new Intent(v.getContext(), ViewRecipes.class);
                startActivity(page);
            }
        });

        if (ContextCompat.checkSelfPermission(InputIngredients.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(InputIngredients.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    }, 100);
        }

        //imageButton = findViewById(R.id.takePhoto);
        captureCamera = findViewById(R.id.takePhoto);
        captureCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            //imageView.setImageBitmap(captureImage);
        }
    }
}
