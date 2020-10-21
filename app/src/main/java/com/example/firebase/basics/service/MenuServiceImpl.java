package com.example.firebase.basics.service;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.firebase.basics.util.FirebaseUtil;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;

public class MenuServiceImpl implements MenuService {

    private MenuService menuService;

    public MenuServiceImpl(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public void logoutOption(Context context) {
        AuthUI.getInstance()
                .signOut(context)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                        Log.d("Logout", "User Logged Out");
                        FirebaseUtil.attachListener();
                    }
                });
    }
}
