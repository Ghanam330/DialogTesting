package com.example.dialogcheckinternetconnection.ui.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dialogcheckinternetconnection.R;
import com.example.dialogcheckinternetconnection.ui.MainActivity2;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
    private Button signin;
    private TextView txtsignup, forgetpassword;
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextEmail = findViewById(R.id.edt_email);
        editTextPassword = findViewById(R.id.edt_password);
        signin = findViewById(R.id.btn_login);
        txtsignup = findViewById(R.id.txt_signup);
        forgetpassword = findViewById(R.id.forget_password);


        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity2.class));
            finish();
            return;
        }
        txtsignup.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUp.class);
            startActivity(intent);
        });
        signin.setOnClickListener(v -> signIn());

        forgetpassword.setOnClickListener(v -> {
            EditText resetMail = new EditText(v.getContext());
            AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
            passwordResetDialog.setTitle("Reset password?");
            passwordResetDialog.setMessage("Enter Your Email To Received Reset Link.");
            passwordResetDialog.setView(resetMail);
            passwordResetDialog.setPositiveButton("yes", (dialog, which) -> {
                String mail = resetMail.getText().toString();
                if (mail.isEmpty()){
                    resetMail.setError("please write email");
                    return;
                }
                auth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(LoginActivity.this, "Reset Link Sent To Your Email", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, "Error ! Reset Link is Not Sent"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            });
            passwordResetDialog.setNegativeButton("No", (dialog, which) -> {

            });
            passwordResetDialog.create().show();
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
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
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