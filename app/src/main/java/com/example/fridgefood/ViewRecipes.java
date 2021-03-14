package com.example.fridgefood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class ViewRecipes extends AppCompatActivity {
    private Button backButton;
    private RequestQueue mQueue;
    public static ArrayList<Integer> recipeIDs = new ArrayList<>();
    public static ArrayList<Recipes> recipesList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_recipes);
        System.out.println("On the correct screen!!!!!!");
        backButton = findViewById(R.id.returnToHome);
        System.out.println(recipesList.size());

        for (Recipes r : recipesList) {
            System.out.println(r);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent page = new Intent(v.getContext(), MainActivity.class);
                startActivity(page);
            }
        });
    }

    /*public void sendRequest(ArrayList<Ingredient> list) {
        mQueue = Volley.newRequestQueue(this);
        System.out.println("on the right function");
        //String url = "https://api.spoonacular.com/recipes/findByIngredients?ingredients=apples,+flour,+sugar&number=3&apiKey=ee50286cf5474505a7f2b94cb3866aa2";
        String url = "https://api.spoonacular.com/recipes/findByIngredients?ingredients=";
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i).getName();
            if (i != 0) {
                url += ",+" + name;
            } else {
                url += name;
            }
        }
        url += "&number=4&apiKey=ee50286cf5474505a7f2b94cb3866aa2";
        //String url = "https://jsonplaceholder.typicode.com/todos/1";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println("here now in onResponse");
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = (JSONObject) response.get(i);
                                System.out.println(obj.getInt("id"));
                                int id = obj.getInt("id");
                                String url = "https://api.spoonacular.com/recipes/" + id + "/information?apiKey=ee50286cf5474505a7f2b94cb3866aa2";
                                getRecipes(url);
                                recipeIDs.add(obj.getInt("id"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);

    }*/

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

}