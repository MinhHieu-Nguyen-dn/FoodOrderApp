package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.foodorder.databinding.ActivityLoginBinding;
import com.example.foodorder.databinding.ActivityOrderHistoryBinding;
import com.example.model.RestaurantModel;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity {

    private ActivityOrderHistoryBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }


//    displayView() throws JSONException {
//        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
//        String ordersJson = sharedPreferences.getString("restaurantModel", null);
//        if (ordersJson != null) {
//            JSONObject restaurantData = new JSONObject(ordersJson);
//            JSONArray menusArray = restaurantData.getJSONArray("menus");
//
//        }
//    }
}