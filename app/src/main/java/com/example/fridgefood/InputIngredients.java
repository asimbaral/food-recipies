package com.example.fridgefood;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class InputIngredients extends AppCompatActivity {
    public ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();

    private Button backButton;
    private Button getRecipesButton;
    private ImageButton captureCamera;
    private ImageButton addIngredientButton;
    private LinearLayout container;
    private EditText ingredientInput;
    private ImageView imageView;
    private ImageButton imageButton;
    @SuppressLint("WrongViewCast")
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

        addIngredientButton = findViewById(R.id.addIngredient);
        ingredientInput = findViewById(R.id.ingredientInput);
        container = findViewById(R.id.ingredientsList);
        addIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                LayoutInflater layoutInflater =
                        (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.row, null);
                final TextView textOut = (TextView)addView.findViewById(R.id.textout);
                if(!ingredientInput.getText().toString().isEmpty()) {
                    textOut.setText(ingredientInput.getText().toString());
                    ingredients.add(new Ingredient(ingredientInput.getText().toString()));
                    Button buttonRemove = (Button) addView.findViewById(R.id.remove);
                    buttonRemove.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ingredients.remove(new Ingredient(textOut.getText().toString()));
                            ((LinearLayout) addView.getParent()).removeView(addView);
                        }
                    });
                    System.out.println(ingredients.toString());
                    container.addView(addView);
                }
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
