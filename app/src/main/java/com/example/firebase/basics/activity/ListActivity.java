package com.example.firebase.basics.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.basics.R;
import com.example.firebase.basics.adapter.DealAdapter;
import com.example.firebase.basics.domain.TravelDeal;
import com.example.firebase.basics.service.MenuService;
import com.example.firebase.basics.util.FirebaseUtil;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    public static FirebaseDatabase firebaseDatabase;
    public static DatabaseReference databaseReference;
    ArrayList<TravelDeal> dealList;
    private MenuService menuService;
//    private FirebaseUtilService firebaseUtilService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_list_menu, menu);

        MenuItem insertMenu = menu.findItem(R.id.add_option);

        if (FirebaseUtil.isMember) {
            insertMenu.setVisible(false);
        } else {
            insertMenu.setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.add_option:
                intent = new Intent(this, InsertActivity.class);
                startActivity(intent);
                return true;
            case R.id.logout_option:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        menuService.logoutOption(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        FirebaseUtil.detachListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getList();
        FirebaseUtil.attachListener();
    }

    public void getList(){
        FirebaseUtil.openFbReference("traveldeals", this);
        RecyclerView rvDeals = (RecyclerView) findViewById(R.id.rvDealList);
        final DealAdapter adapter = new DealAdapter();
        rvDeals.setAdapter(adapter);

        LinearLayoutManager dealLinearLayout =
                new LinearLayoutManager( this, LinearLayoutManager.VERTICAL, false);
        rvDeals.setLayoutManager(dealLinearLayout);

    }

    public void showMenu() {
        invalidateOptionsMenu();
    }
}