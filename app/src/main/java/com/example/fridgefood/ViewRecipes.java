package com.example.fridgefood;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ViewRecipes extends AppCompatActivity {
    private Button backButton;
    private RequestQueue mQueue;
    public static ArrayList<Integer> recipeIDs = new ArrayList<>();
    public static ArrayList<Recipes> recipesList = new ArrayList<>();
    private LinearLayout container;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_recipes);
        backButton = findViewById(R.id.returnToHome);
        System.out.println(recipesList.size());


        final Recipes rr = recipesList.get(0);
        container = findViewById(R.id.linearContainer);
        LayoutInflater layoutInflater =
                (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (Recipes r : recipesList) {
            System.out.println(r);
            final View addView = layoutInflater.inflate(R.layout.recipe, null);
            final TextView nameView = (TextView)addView.findViewById(R.id.nameView);
            final TextView timeView = (TextView)addView.findViewById(R.id.timeView);
            final TextView caloriesView = (TextView)addView.findViewById(R.id.caloriesView);
            final Button button2 = (Button)addView.findViewById(R.id.button2);
            ImageView imageView = (ImageView) addView.findViewById(R.id.imageView);
            System.out.println("::::::::::::::::");
            System.out.println(r.getImageURL());
            nameView.setText(r.getName());
            timeView.setText("Time: " + r.getTime() + " Minutes");
            caloriesView.setText("Calories: " +  r.getSummary().substring(r.getSummary().indexOf("One serving contains <b>") + 24, r.getSummary().indexOf(" ", r.getSummary().indexOf("One serving contains <b>") + 24)));
            Picasso.with(ViewRecipes.this).load(r.getImageURL()).into(imageView);
            final String recipeurl = r.getRecipeURL();
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent browser1 = new Intent(Intent.ACTION_VIEW, Uri.parse(recipeurl));
                    startActivity(browser1);
                }
            });
            container.addView(addView);
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
/*

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
*/
    }