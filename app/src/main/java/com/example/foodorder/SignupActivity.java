package com.example.foodorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText txtemail, txtpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txtemail = findViewById(R.id.signin_email);
        txtpassword = findViewById(R.id.signin_password);
        setListeners();
    }

    private void setListeners() {

        Button buttonSignUp = findViewById(R.id.signupButton);

        buttonSignUp.setOnClickListener(v -> {
            String email = txtemail.getText().toString();
            String password = txtpassword.getText().toString();

            boolean isValidated = validateData(email,password);
            if(!isValidated){
                return;
            }

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

    boolean validateData(String email,String password){

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txtemail.setError("Email is invalid");
            return false;
        }
        if(password.length()<6){
            txtpassword.setError("Password length is invalid");
            return false;
        }
        return true;
    }
}