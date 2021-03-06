package com.example.firebase.basics.util;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;

public class MenuUtil {

    public MenuUtil() {
    }

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
        FirebaseUtil.detachListener();
    }
}
