package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.foodorder.databinding.ActivitySignupBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

//    private ActivitySignupBinding binding;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setListeners();
    }

    private void setListeners() {

        Button buttonSignUp = findViewById(R.id.signupButton);



        buttonSignUp.setOnClickListener(v -> {
            EditText txtemail = findViewById(R.id.signin_email);
            String email = txtemail.getText().toString();

            EditText txtpassword = findViewById(R.id.signin_password);
            String password = txtpassword.getText().toString();

            Map<String, String> objectMap = new HashMap<>();
            objectMap.put("email", email);
            objectMap.put("password", password);
            db.collection("account").add(objectMap)
                    .addOnSuccessListener(documentReference -> {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    })
                    .addOnFailureListener(e -> {
                        // Handle error
                    });
        });
    }
}