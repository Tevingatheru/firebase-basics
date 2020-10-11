package com.example.firebase.basics;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.basics.util.FirebaseUtil;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DealAdapter extends RecyclerView.Adapter<DealAdapter.DealViewHolder> {

    ArrayList<TravelDeal> deals;
    public static FirebaseDatabase firebaseDatabase;
    public static DatabaseReference databaseReference;
    private ChildEventListener childEventListener;

    public DealAdapter() {
        listenToFB();
        deals = FirebaseUtil.deals;
        notifyItemInserted(deals.size() - 1);

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                TravelDeal travelDeal = snapshot.getValue(TravelDeal.class);
                Log.i("Deal: ", travelDeal.getTitle());
                travelDeal.setId(snapshot.getKey());
                deals.add(travelDeal);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.addChildEventListener(childEventListener);
    }

    @NonNull
    @Override
    public DealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rv_deals, parent, false);
        return new DealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DealViewHolder holder, int position) {
        TravelDeal deal = deals.get(position);
        holder.bind(deal);
    }

    @Override
    public int getItemCount() {
        return deals.size();
    }

    public class DealViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvPrice;
        TextView tvDescription;

        public DealViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
        }

        public void bind (TravelDeal travelDeal) {
            String price = travelDeal.getPrice() + " " + "KES";
            tvTitle.setText(travelDeal.getTitle());
            tvPrice.setText(price);
            tvDescription.setText(travelDeal.getDescription());
        }
    }

    private void listenToFB() {
        FirebaseUtil.openFbReference("traveldeals");
        firebaseDatabase = FirebaseUtil.firebaseDatabase;
        databaseReference = FirebaseUtil.databaseReference;
    }
}
