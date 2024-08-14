package com.example.posting;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.posting.Model.Products;
import com.example.posting.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.Manifest;

public class ProductDetailsActivity2 extends AppCompatActivity {

    private ImageView productImage;
    private TextView productName, productPrice, productDescription, productOwner, productPhone, productTime,productDate;
    private DatabaseReference UsersRef;
    private Button callOwnerButton;

    private ValueEventListener userValueEventListener;

    private static final int REQUEST_CALL_PHONE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details2);

        productImage = findViewById(R.id.product_detail_image);
        productName = findViewById(R.id.product_detail_name);
        productPrice = findViewById(R.id.product_detail_price);
        productDescription = findViewById(R.id.product_detail_description);
        productOwner = findViewById(R.id.product_detail_owner);
        productPhone = findViewById(R.id.product_detail_phone);
        productTime = findViewById(R.id.product_detail_time);
        callOwnerButton = findViewById(R.id.button_call_owner);


        Button callOwnerButton = findViewById(R.id.button_call_owner);

        Products product = (Products) getIntent().getSerializableExtra("product");

        if (product != null) {
            productName.setText(product.getPname());
            productPrice.setText(product.getPrice());
            productDescription.setText(product.getDescription());

            productTime.setText(product.getDate()+" "+product.getTime());

            Glide.with(this).load(product.getImage()).into(productImage);

            String ownerId = product.getUid();
            if (ownerId != null && !ownerId.isEmpty()) {
                UsersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(ownerId);
                UsersRef.addListenerForSingleValueEvent(new ValueEventListener() {
                //userValueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Users user = dataSnapshot.getValue(Users.class);
                        if (user != null) {
                            productOwner.setText(user.getName());
                            productPhone.setText(user.getPhone());

                            callOwnerButton.setTag(user.getPhone());
                        } else {
                            Toast.makeText(ProductDetailsActivity2.this, "User details not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(ProductDetailsActivity2.this, "Failed to load user details", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "Owner ID is null or empty", Toast.LENGTH_SHORT).show();
            }
            callOwnerButton.setOnClickListener(v -> {
                String phone = (String) callOwnerButton.getTag();
                if (phone != null && !phone.isEmpty()) {
                    // Check for permission to make phone call
                    if (ContextCompat.checkSelfPermission(ProductDetailsActivity2.this, Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED) {
                        // Permission not granted, request it
                        ActivityCompat.requestPermissions(ProductDetailsActivity2.this,
                                new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
                    } else {
                        // Permission already granted, proceed with call
                        makePhoneCall(phone);
                    }
                } else {
                    Toast.makeText(ProductDetailsActivity2.this, "Phone number not available", Toast.LENGTH_SHORT).show();
                }
            });

            } else {
                Toast.makeText(this, "Product details not available", Toast.LENGTH_SHORT).show();
            }
        }

    private void makePhoneCall(String productPhone) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + productPhone));
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No app to handle phone calls", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CALL_PHONE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, make phone call
                String phone = (String) callOwnerButton.getTag();
                if (phone != null && !phone.isEmpty()) {
                    makePhoneCall(phone);
                } else {
                    Toast.makeText(ProductDetailsActivity2.this, "Phone number not available", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Permission denied
                Toast.makeText(this, "Permission denied to make phone calls", Toast.LENGTH_SHORT).show();
            }
        }
    }
}











