package com.example.lora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {

    EditText remail,rpass,namee;
    Button register;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register= findViewById(R.id.registerbtn);
        remail=findViewById(R.id.remail);
        rpass=findViewById(R.id.rpass);
        namee= findViewById(R.id.Adminname);

        mAuth = FirebaseAuth.getInstance();



        mAuth = FirebaseAuth.getInstance();
        register.setOnClickListener(view->{
            createUser();
        });
    }
    public void createUser(){

        FirebaseAuth mAuth;

        String name = namee.getText().toString();
        String email = remail.getText().toString();
        String password =rpass.getText().toString();
        mAuth = FirebaseAuth.getInstance();

        if (TextUtils.isEmpty(email)){
            remail.setError("E-mail cannot be Empty");
            remail.requestFocus();
        } else if(TextUtils.isEmpty(password)){
            rpass.setError("Password cannot be Empty");
            rpass.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){


                                String userId = task.getResult().getUser().getUid();
                                DatabaseReference staffDbRef;

                                Admin admin = new Admin(name,email);

                                staffDbRef= FirebaseDatabase.getInstance("https://lora-f8b64-default-rtdb.firebaseio.com/").getReference("Admin/"+userId);

                                staffDbRef.setValue(admin);

                                Toast.makeText(register.this, "User register successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(register.this,MainActivity.class));
                            }
                            else{
                                Toast.makeText(register.this, "Registration error :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }
    }
}