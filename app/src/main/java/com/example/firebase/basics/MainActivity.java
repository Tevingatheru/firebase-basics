package com.example.firebase.basics;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeContent();
    }

    public void initializeContent() {
        Intent insertActivity = new Intent(MainActivity.this, InsertActivity.class);
        startActivity(insertActivity);
    }
}