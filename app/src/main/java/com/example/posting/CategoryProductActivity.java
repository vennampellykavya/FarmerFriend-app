package com.example.posting;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.posting.Model.Products;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryProductActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Products> productList;
    private DatabaseReference ProductsRef;
    private String categoryName;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_layout);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(productList, this);
        recyclerView.setAdapter(productAdapter);

        if (getIntent() != null) {
            categoryName = getIntent().getStringExtra("category");
            Log.d(TAG, "Category received: " + categoryName);
        }

        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");

        fetchProductsByCategory(categoryName);
    }

    private void fetchProductsByCategory(String category) {
        ProductsRef.orderByChild("category").equalTo(category).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Products product = snapshot.getValue(Products.class);
                    if(product != null)
                        productList.add(product);
                }
                if (productList.isEmpty()) {
                    // Handle case where no products are found for the category
                    Toast.makeText(CategoryProductActivity.this, "No products found for category: " + category, Toast.LENGTH_SHORT).show();
                }
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CategoryProductActivity.this, "Failed to load products", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
