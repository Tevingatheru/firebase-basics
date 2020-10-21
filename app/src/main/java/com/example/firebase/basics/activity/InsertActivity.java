package com.example.firebase.basics.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebase.basics.R;
import com.example.firebase.basics.domain.TravelDeal;
import com.example.firebase.basics.service.FirebaseUtilService;
import com.example.firebase.basics.util.FirebaseUtil;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertActivity extends AppCompatActivity {
    EditText title;
    EditText description;
    EditText price;

    public static FirebaseDatabase firebaseDatabase;
    public static DatabaseReference databaseReference;
    private FirebaseUtilService firebaseUtilService;
    private Button btnImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        listenToFB();
        initializeContent();

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(intent.createChooser(intent, "Insert Picture"), 42);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_option:
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
        inflater.inflate(R.menu.activity_insert_menu, menu);

        if (FirebaseUtil.isMember) {
            menu.findItem(R.id.delete_option).setVisible(false);
            menu.findItem(R.id.add_option).setVisible(false);
            enableEditText(false);
        } else {
            menu.findItem(R.id.delete_option).setVisible(true);
            menu.findItem(R.id.add_option).setVisible(true);
            enableEditText(true);
        }
        menu.findItem(R.id.logout_option).setVisible(true);
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
        String txtDescription = description.getText().toString();
        String txtPrice = price.getText().toString();
        TravelDeal deal = new TravelDeal(txtTitle, txtPrice, txtDescription, "");
        databaseReference.push().setValue(deal);
    }

    private void initializeContent() {
        title = findViewById(R.id.insert_title);
        description = findViewById(R.id.insert_description);
        price = findViewById(R.id.insert_price);
        btnImage = findViewById(R.id.btnImage);
    }

    private void listenToFB() {
        firebaseUtilService.listenToFb();
    }

    private void enableEditText(boolean isEnabled) {
        title.setEnabled(isEnabled);
        price.setEnabled(isEnabled);
        description.setEnabled(isEnabled);
    }
}