package com.example.lora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class basepage extends AppCompatActivity {
    Button searchbus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basepage);

        searchbus= (Button) findViewById(R.id.searchbusbtn);
        searchbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(basepage.this,busdetails.class);
                startActivity(intent);
            }
        });
    }
}