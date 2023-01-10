package com.example.lora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class busdetails extends AppCompatActivity {

    Button payint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busdetails);


        payint= (Button) findViewById(R.id.pay1btn);
        payint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(busdetails.this,payinterface.class);
                startActivity(intent);
            }
        });
    }
}