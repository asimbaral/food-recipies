package com.example.fridgefood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button savedRecipesButton;
    private RequestQueue mQueue;
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
        ArrayList<Ingredient> l = new ArrayList<>();
        l.add(new Ingredient("bananas"));
        l.add(new Ingredient("milk"));
        l.add(new Ingredient("sugar"));
        sendRequest(l, new InputIngredients.VolleyResponseListener() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(String response) {

            }

            @Override
            public void onResponse(int response) {
                ViewRecipes.recipeIDs.add(response);
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
    public void sendRequest(ArrayList<Ingredient> list, final InputIngredients.VolleyResponseListener volleyResponseListener) {
        mQueue = Volley.newRequestQueue(this);
        //System.out.println("on the right function");
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
        url += "&number=4&apiKey=f663279861a14c8a90c5f3b17f8c09b0";
        //String url = "https://jsonplaceholder.typicode.com/todos/1";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //System.out.println("here now in onResponse");
                        for (int i = 0; i < response.length(); i++) {
                            int id = 0;
                            try {
                                JSONObject obj = (JSONObject) response.get(i);
                                id = obj.getInt("id");
                                //System.out.println(id);
                                //String url = "https://api.spoonacular.com/recipes/" + id + "/information?apiKey=ee50286cf5474505a7f2b94cb3866aa2";
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //ViewRecipes.recipeIDs.add(id);
                            volleyResponseListener.onResponse(id);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                volleyResponseListener.onError("Something wrong");
            }
        });
        mQueue.add(request);
    }
}