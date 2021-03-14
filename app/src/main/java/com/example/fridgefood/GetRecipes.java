package com.example.fridgefood;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.graphics.Bitmap;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import static com.example.fridgefood.ViewRecipes.recipeIDs;
import static com.example.fridgefood.ViewRecipes.recipesList;

public class GetRecipes extends AppCompatActivity {
    private Button backButton;
    private Button getRecipesButton;
    private ImageButton captureCamera;
    private ImageView imageView;
    private ImageButton imageButton;
    private RequestQueue mQueue;
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
        for (int id : recipeIDs) {
            System.out.println("Here____________--------------");
            String url = "https://api.spoonacular.com/recipes/" + id + "/information?apiKey=f663279861a14c8a90c5f3b17f8c09b0";
            getRecipes(url);
        }
        getRecipesButton = findViewById(R.id.getRecipesButton);
        getRecipesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent page = new Intent(v.getContext(), ViewRecipes.class);
                startActivity(page);
            }
        });

        if (ContextCompat.checkSelfPermission(GetRecipes.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(GetRecipes.this,
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

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String response);

        void onResponse(int response);
    }


    public void getRecipes(String url) {
        mQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println("_____________________000000000000000000000");
                            int time = response.getInt("readyInMinutes");
                            String summary = response.getString("summary");
                            String imageURL = response.getString("image");
                            String recipeURL = response.getString("sourceUrl");
                            recipesList.add(new Recipes(time, summary, imageURL, recipeURL));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);

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
