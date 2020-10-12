package com.example.firebase.basics.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebase.basics.R;
import com.example.firebase.basics.TravelDeal;
import com.example.firebase.basics.util.FirebaseUtil;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertActivity extends AppCompatActivity {
    EditText title;
    EditText description;
    EditText price;

    public static FirebaseDatabase firebaseDatabase;
    public static DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        listenToFB();
        initializeContent();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_menu:
                saveDeal();
                Toast.makeText(this, "Deal Saved", Toast.LENGTH_LONG).show();
                clean();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    private void clean() {
        title.setText("");
        price.setText("");
        description.setText("");
        title.requestFocus();
    }

    private void saveDeal() {
        String txtTitle = title.getText().toString();
        String txtDescription = title.getText().toString();
        String txtPrice = price.getText().toString();
        TravelDeal deal = new TravelDeal(txtTitle, txtPrice, txtDescription, "");
        databaseReference.push().setValue(deal);
    }

    private void initializeContent() {
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        price = findViewById(R.id.price);
    }

    private void listenToFB() {
        FirebaseUtil.openFbReference("traveldeals");
        firebaseDatabase = FirebaseUtil.firebaseDatabase;
        databaseReference = FirebaseUtil.databaseReference;
    }
}