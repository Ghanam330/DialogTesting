package com.example.dialogcheckinternetconnection.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dialogcheckinternetconnection.MainActivity2;
import com.example.dialogcheckinternetconnection.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
   private Button signin;
   private TextView txtsignup, forgetpassword;
private FirebaseAuth auth =FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextEmail = findViewById(R.id.edt_email);
        editTextPassword = findViewById(R.id.edt_password);
        signin = findViewById(R.id.btn_login);
        txtsignup = findViewById(R.id.txt_signup);
        forgetpassword = findViewById(R.id.forget_password);


        if (auth.getCurrentUser() != null){
            startActivity(new Intent(LoginActivity.this, MainActivity2.class));
            finish();
            return;
        }
        txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignUp.class);
                startActivity(intent);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void signIn() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        if (email.isEmpty()) {
            editTextEmail.setError("please write email");
            return;
        } else {
            editTextEmail.setError(null);
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Please write password");
            return;
        } else {
            editTextPassword.setError(null);
        }
        if (password.length() < 6) {
            editTextPassword.setError("Please write Long password");
            return;
        } else {
            editTextPassword.setError(null);

        }
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity2.class));
                finish();
            } else {
                Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}