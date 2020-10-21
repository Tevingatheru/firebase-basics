package com.example.firebase.basics.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase.basics.R;
import com.example.firebase.basics.domain.TravelDeal;
import com.example.firebase.basics.service.MenuService;
import com.example.firebase.basics.util.FirebaseUtil;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditActivity extends AppCompatActivity {
    EditText title;
    EditText description;
    EditText price;

    public static FirebaseDatabase firebaseDatabase;
    public static DatabaseReference databaseReference;

    private TravelDeal deal;
    private Intent intent;
    private MenuService menuService;
//    private FirebaseUtilService firebaseUtilService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        listenToFB();
        initializeContent(savedInstanceState);

        title.setText(deal.getTitle());
        description.setText(deal.getDescription());
        price.setText(deal.getPrice());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_option:
                editDeal();
                Toast.makeText(this, "Deal Edited", Toast.LENGTH_LONG).show();
                clean();
                backToList();
                return true;

            case R.id.delete_option:
                deleteDeal();
                Toast.makeText(this, "Deal Deleted", Toast.LENGTH_LONG).show();
                backToList();
                return true;

            case R.id.view_deals_option:
                backToList();
                return true;

            case R.id.logout_option:
                logout();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_edit_menu, menu);

        if (FirebaseUtil.isMember) {
            menu.findItem(R.id.delete_option).setVisible(false);
        } else {
            menu.findItem(R.id.delete_option).setVisible(true);
        }
        return true;
    }

    private void clean() {
        title.setText("");
        price.setText("");
        description.setText("");
        title.requestFocus();
    }

    private void editDeal() {
        deal.setTitle(title.getText().toString());
        deal.setDescription(description.getText().toString());
        deal.setPrice(price.getText().toString());
        if(deal.getId()==null) {
            throw new NullPointerException();
        }
        Log.d("Edit: ", deal.getId());

        databaseReference.child(deal.getId()).setValue(deal);
    }

    private void deleteDeal() {
        if(deal.getId()==null) {
            Toast.makeText(this, "Deal does not exist", Toast.LENGTH_LONG).show();
            return;
        }
        Log.d("Delete: ", deal.getId());
        databaseReference.child(deal.getId()).removeValue();
    }

    private void backToList() {
        intent = new Intent(this, ListActivity.class);
        Log.d("Back: ", deal.getId());
        startActivity(intent);
    }

    private void initializeContent(Bundle savedInstanceState) {
        title = (EditText) findViewById(R.id.edit_title);
        description = (EditText) findViewById(R.id.edit_description) ;
        price = (EditText) findViewById(R.id.edit_price);

        intent = getIntent();
        this.deal = (TravelDeal) intent.getSerializableExtra("Deal");
        assert deal != null;
    }

    private void listenToFB() {
        FirebaseUtil.openFbReference("traveldeals", new ListActivity());
        firebaseDatabase = FirebaseUtil.firebaseDatabase;
        databaseReference = FirebaseUtil.databaseReference;
//        firebaseUtilService.listenToFb();
    }

    private void logout() {
        menuService.logoutOption(this);
    }
}