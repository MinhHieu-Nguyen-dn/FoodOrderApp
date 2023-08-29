package com.example.foodorder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.adapters.PlaceYourOrderAdapter;
import com.example.foodorder.databinding.ActivityLoginBinding;
import com.example.foodorder.databinding.ActivityPlaceYourOrderBinding;
import com.example.model.Menu;
import com.example.model.Order;
import com.example.model.RestaurantModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaceYourOrderActivity extends AppCompatActivity {

    private EditText inputName, inputAddress, inputCity, inputState, inputZip, inputCardNumber, inputCardExpiry, inputCardPin;
    private RecyclerView cartItemsRecyclerView;
    private TextView tvSubtotalAmount, tvDeliveryChargeAmount, tvDeliveryCharge, tvTotalAmount, buttonPlaceYourOrder;
    private SwitchCompat switchDelivery;
    private boolean isDeliveryOn;
    private PlaceYourOrderAdapter placeYourOrderAdapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ActivityPlaceYourOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_your_order);
        binding = ActivityPlaceYourOrderBinding.inflate(getLayoutInflater());

        RestaurantModel restaurantModel = getIntent().getParcelableExtra("RestaurantModel");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(restaurantModel.getName());
        actionBar.setSubtitle(restaurantModel.getAddress());
        actionBar.setDisplayHomeAsUpEnabled(true);

        inputName = findViewById(R.id.inputName);
        inputAddress = findViewById(R.id.inputAddress);
        inputCity = findViewById(R.id.inputCity);
        inputState = findViewById(R.id.inputState);
        inputZip = findViewById(R.id.inputZip);
        inputCardNumber = findViewById(R.id.inputCardNumber);
        inputCardExpiry = findViewById(R.id.inputCardExpiry);
        inputCardPin = findViewById(R.id.inputCardPin);
        tvSubtotalAmount = findViewById(R.id.tvSubtotalAmount);
        tvDeliveryChargeAmount = findViewById(R.id.tvDeliveryChargeAmount);
        tvDeliveryCharge = findViewById(R.id.tvDeliveryCharge);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);


        buttonPlaceYourOrder = findViewById(R.id.buttonPlaceYourOrder);
        switchDelivery = findViewById(R.id.switchDelivery);

        cartItemsRecyclerView = findViewById(R.id.cartItemsRecyclerView);

        buttonPlaceYourOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText txtname = findViewById(R.id.inputName);
                String inputName1 = txtname.getText().toString();

                EditText txtaddress = findViewById(R.id.inputAddress);
                String inputAddress1 = txtaddress.getText().toString();

                EditText txtcity = findViewById(R.id.inputCity);
                String inputCity1 = txtcity.getText().toString();

                EditText txtstate = findViewById(R.id.inputState);
                String inputState1 = txtstate.getText().toString();

                EditText inputzip = findViewById(R.id.inputZip);
                String inputZip1 = inputzip.getText().toString();

                EditText inputcardnumber = findViewById(R.id.inputCardNumber);
                String inputCardNumber1 = inputcardnumber.getText().toString();

                EditText txtcardexpiry = findViewById(R.id.inputCardExpiry);
                String inputCardExpiry1 = txtcardexpiry.getText().toString();

                EditText txtcardpin = findViewById(R.id.inputCardPin);
                String inputCardPin1 = txtcardpin.getText().toString();

                TextView txtsubtotal = findViewById(R.id.tvSubtotalAmount);
                String tvSubtotalAmount1 = txtsubtotal.getText().toString();

                TextView txtdeliver = findViewById(R.id.tvDeliveryChargeAmount);
                String tvDeliveryChargeAmount1 = txtdeliver.getText().toString();

                TextView tvcharge = findViewById(R.id.tvDeliveryCharge);
                String tvDeliveryCharge1 = tvcharge.getText().toString();

                TextView tvtotal = findViewById(R.id.tvTotalAmount);
                String tvTotalAmount1 = tvtotal.getText().toString();

                Order order = new Order(inputName1, inputAddress1, inputCity1, inputState1, inputZip1, inputCardNumber1, inputCardExpiry1, inputCardPin1
                        , tvSubtotalAmount1, tvDeliveryChargeAmount1, tvDeliveryCharge1, tvTotalAmount1);

                Map<String, Object> objectMap = new HashMap<>();
                objectMap.put("inputName", order.getInputName());
                objectMap.put("inputAddress", order.getInputAddress());
                objectMap.put("inputCity", order.getInputCity());
                objectMap.put("inputState", order.getInputState());
                objectMap.put("inputZip", order.getInputZip());
                objectMap.put("inputCardNumber", order.getInputCardNumber());
                objectMap.put("inputCardExpiry", order.getInputCardExpiry());
                objectMap.put("inputCardPin", order.getInputCardPin());
                objectMap.put("tvSubtotalAmount", order.getTvSubtotalAmount());
                objectMap.put("tvDeliveryChargeAmount", order.getTvDeliveryChargeAmount());
                objectMap.put("tvDeliveryCharge", order.getTvDeliveryCharge());
                objectMap.put("tvTotalAmount", order.getTvTotalAmount());

                db.collection("orderHistory").add(objectMap)
                        .addOnSuccessListener(documentReference -> {
                            // Object added successfully
                        })
                        .addOnFailureListener(e -> {
                            // Handle error
                        });

                onPlaceOrderButtonClick(restaurantModel);
            }
        });

        switchDelivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    inputAddress.setVisibility(View.VISIBLE);
                    inputCity.setVisibility(View.VISIBLE);
                    inputState.setVisibility(View.VISIBLE);
                    inputZip.setVisibility(View.VISIBLE);
                    tvDeliveryChargeAmount.setVisibility(View.VISIBLE);
                    tvDeliveryCharge.setVisibility(View.VISIBLE);
                    isDeliveryOn = true;
                    calculateTotalAmount(restaurantModel);
                } else {
                    inputAddress.setVisibility(View.GONE);
                    inputCity.setVisibility(View.GONE);
                    inputState.setVisibility(View.GONE);
                    inputZip.setVisibility(View.GONE);
                    tvDeliveryChargeAmount.setVisibility(View.GONE);
                    tvDeliveryCharge.setVisibility(View.GONE);
                    isDeliveryOn = false;
                    calculateTotalAmount(restaurantModel);
                }
            }
        });
        initRecyclerView(restaurantModel);
        calculateTotalAmount(restaurantModel);
    }

    private void calculateTotalAmount(RestaurantModel restaurantModel) {
        float subTotalAmount = 0f;

        for (Menu m : restaurantModel.getMenus()) {
            subTotalAmount += m.getPrice() * m.getTotalInCart();
        }

        tvSubtotalAmount.setText("$" + String.format("%.2f", subTotalAmount));
        if (isDeliveryOn) {
            tvDeliveryChargeAmount.setText("$" + String.format("%.2f", restaurantModel.getDelivery_charge()));
            subTotalAmount += restaurantModel.getDelivery_charge();
        }
        tvTotalAmount.setText("$" + String.format("%.2f", subTotalAmount));
    }

    private void onPlaceOrderButtonClick(RestaurantModel restaurantModel) {
        if (TextUtils.isEmpty(inputName.getText().toString())) {
            inputName.setError("Please enter name ");
            return;
        } else if (isDeliveryOn && TextUtils.isEmpty(inputAddress.getText().toString())) {
            inputAddress.setError("Please enter address ");
            return;
        } else if (isDeliveryOn && TextUtils.isEmpty(inputCity.getText().toString())) {
            inputCity.setError("Please enter city ");
            return;
        } else if (isDeliveryOn && TextUtils.isEmpty(inputState.getText().toString())) {
            inputState.setError("Please enter zip ");
            return;
        } else if (TextUtils.isEmpty(inputCardNumber.getText().toString())) {
            inputCardNumber.setError("Please enter card number ");
            return;
        } else if (TextUtils.isEmpty(inputCardExpiry.getText().toString())) {
            inputCardExpiry.setError("Please enter card expiry ");
            return;
        } else if (TextUtils.isEmpty(inputCardPin.getText().toString())) {
            inputCardPin.setError("Please enter card pin/cvv ");
            return;
        }
        //start success activity..
        Intent i = new Intent(PlaceYourOrderActivity.this, OrderSucceessActivity.class);
        i.putExtra("RestaurantModel", restaurantModel);
        startActivityForResult(i, 1000);

        Gson gson = new Gson();
        String ordersJson = gson.toJson(restaurantModel);

        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("restaurantModel", ordersJson);
        editor.apply();
    }

    private void initRecyclerView(RestaurantModel restaurantModel) {
        cartItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        placeYourOrderAdapter = new PlaceYourOrderAdapter(restaurantModel.getMenus());
        cartItemsRecyclerView.setAdapter(placeYourOrderAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 1000) {
            setResult(Activity.RESULT_OK);
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                //do nothing
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
}