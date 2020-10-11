package com.example.firebase.basics.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase.basics.R;

public class MainActivity extends AppCompatActivity {
    private TextView hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hello = findViewById(R.id.hello);

        initializeContent();
    }

    public void initializeContent() {

        Intent insertActivity = new Intent(MainActivity.this, InsertActivity.class);
        startActivity(insertActivity);
    }
}