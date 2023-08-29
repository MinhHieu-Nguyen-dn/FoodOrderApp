package com.example.foodorder;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;


import com.example.foodorder.databinding.ActivityLoginBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners() {
        binding.signinButton.setOnClickListener(v -> {
            String email = binding.signinEmail.getText().toString().trim(); // Trim to remove leading/trailing spaces
            String password = binding.signinPassword.getText().toString();

            boolean isValidated = validateData(email,password);
            if(!isValidated){
                return;
            }

            FirebaseFirestore database = FirebaseFirestore.getInstance();
            database.collection("account")
                    .whereEqualTo("email", email)
                    .whereEqualTo("password", password)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful() && task.getResult() != null && task.getResult().getDocuments().size() > 0) {
                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    });
        });

        binding.signupButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }

    boolean validateData(String email,String password){

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.signinEmail.setError("Email is invalid");
            return false;
        }
        if(password.length()<6){
            binding.signinPassword.setError("Password length is invalid");
            return false;
        }
        return true;
    }

}