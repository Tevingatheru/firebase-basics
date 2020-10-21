package com.example.firebase.basics.service;

import com.example.firebase.basics.domain.TravelDeal;
import com.example.firebase.basics.util.FirebaseUtil;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseUtilServiceImpl implements FirebaseUtilService{
    public static FirebaseDatabase firebaseDatabase;
    public static DatabaseReference databaseReference;
    ArrayList<TravelDeal> dealList;

    public FirebaseUtilServiceImpl(ArrayList<TravelDeal> dealList) {
        this.dealList = dealList;
    }

    @Override
    public void listenToFb() {
        firebaseDatabase = FirebaseUtil.firebaseDatabase;
        databaseReference = FirebaseUtil.databaseReference;
        dealList = FirebaseUtil.deals;
    }
}
