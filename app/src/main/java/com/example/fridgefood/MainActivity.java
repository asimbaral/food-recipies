package com.example.fridgefood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button savedRecipesButton;
    private Button getInfoButton;
    private PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.inputIngredientsButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent page = new Intent(v.getContext(), InputIngredients.class);
                startActivity(page);
            }
        });

        savedRecipesButton = findViewById(R.id.savedRecipesButton);
        savedRecipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent page = new Intent(v.getContext(), SavedRecipes.class);
                startActivity(page);
            }
        });

        getInfoButton = findViewById(R.id.getInfo);
        getInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    public void openDialog() {
        InfoDialog dialog = new InfoDialog();
        dialog.show(getSupportFragmentManager(), "More Info");
    }
}