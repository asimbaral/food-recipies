package com.example.fridgefood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewRecipes extends AppCompatActivity {
    private Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_recipes);
        backButton = findViewById(R.id.returnToHome);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent page = new Intent(v.getContext(), MainActivity.class);
                startActivity(page);
            }
        });
    }
}