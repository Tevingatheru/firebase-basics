package com.example.firebase.basics.activity;

import android.content.Intent;
import android.net.Uri;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase.basics.R;
import com.example.firebase.basics.domain.TravelDeal;
import com.example.firebase.basics.util.FirebaseUtil;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

public class InsertActivity extends AppCompatActivity {
    EditText title;
    EditText description;
    EditText price;

    public static FirebaseDatabase firebaseDatabase;
    public static DatabaseReference databaseReference;
    public static final int PICTURE_RESULT = 42;
    private Button btnImage;
    private TravelDeal deal;
    private String uri;

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
                startActivityForResult(intent.createChooser(intent, "Insert Picture"), PICTURE_RESULT);
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
            menu.findItem(R.id.save_option).setVisible(false);
            enableEditText(false);
        } else {
            menu.findItem(R.id.save_option).setVisible(true);
            enableEditText(true);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICTURE_RESULT && resultCode == RESULT_OK) {
            assert data != null;
            Uri imageUri = data.getData();
            StorageReference reference = FirebaseUtil.storageReference.child(Objects.requireNonNull(imageUri.getLastPathSegment()));
            reference.putFile(imageUri).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    uri = taskSnapshot.getUploadSessionUri().toString();
                }
            });
        }
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

        deal = new TravelDeal(txtTitle, txtPrice, txtDescription, uri);
        databaseReference.push().setValue(deal);
    }

    private void initializeContent() {
        title = findViewById(R.id.insert_title);
        description = findViewById(R.id.insert_description);
        price = findViewById(R.id.insert_price);
        btnImage = findViewById(R.id.btnImage);
    }

    private void listenToFB() {
        FirebaseUtil.openFbReference("traveldeals", new ListActivity());
        firebaseDatabase = FirebaseUtil.firebaseDatabase;
        databaseReference = FirebaseUtil.databaseReference;
    }

    private void enableEditText(boolean isEnabled) {
        title.setEnabled(isEnabled);
        price.setEnabled(isEnabled);
        description.setEnabled(isEnabled);
    }
}