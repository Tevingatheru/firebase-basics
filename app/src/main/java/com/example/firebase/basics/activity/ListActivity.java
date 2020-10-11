package com.example.firebase.basics.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.basics.DealAdapter;
import com.example.firebase.basics.R;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        RecyclerView rvDeals = (RecyclerView) findViewById(R.id.rv_deals);
        final DealAdapter adapter = new DealAdapter();
        rvDeals.setAdapter(adapter);

        System.out.println(rvDeals == null);
        System.out.println(adapter == null);

        LinearLayoutManager dealLinearLayout =
                new LinearLayoutManager( ListActivity.this, LinearLayoutManager.VERTICAL, false);
        rvDeals.setLayoutManager(dealLinearLayout);
    }
}