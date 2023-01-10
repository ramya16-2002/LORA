package com.example.lora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    public Button regbtn, register,logreg;
    EditText rmemail,rmpass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        register= findViewById(R.id.register1);
        logreg= findViewById(R.id.login);
        rmemail=findViewById(R.id.staffemail);
        rmpass=findViewById(R.id.staffpass);

        mAuth = FirebaseAuth.getInstance();

        logreg.setOnClickListener(view -> {
            loginUser();
        });
        register.setOnClickListener(view ->{
            startActivity(new Intent(MainActivity.this,register.class));
        });

        regbtn= (Button) findViewById(R.id.register1);
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,register.class);
                startActivity(intent);
            }
        });
    }
    private void loginUser() {
        String email = rmemail.getText().toString();
        String password = rmpass.getText().toString();

        if (TextUtils.isEmpty(email)) {
            rmemail.setError("Email cannot be empty");
            rmemail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            rmpass.setError("Password cannot be empty");
            rmpass.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "User loged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, basepage.class));
                    } else {
                        Toast.makeText(MainActivity.this, "Login error :" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}