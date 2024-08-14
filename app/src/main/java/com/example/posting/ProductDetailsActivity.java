//package com.example.posting;
//
//import android.os.Bundle;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//public class ProductDetailsActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_product_details);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//    }
//}

package com.example.posting;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ProductDetailsActivity extends AppCompatActivity {

    private ImageView productImageView;
    private TextView productNameTextView, productDescriptionTextView, productPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productImageView = findViewById(R.id.product_image);
        productNameTextView = findViewById(R.id.product_name);
        //productDescriptionTextView = findViewById(R.id.product_description);
        productPriceTextView = findViewById(R.id.product_price);

        // Get product details from intent extras
        if (getIntent().hasExtra("pid")) {
            String pid = getIntent().getStringExtra("pid");
            String image = getIntent().getStringExtra("image");
            String name = getIntent().getStringExtra("pname");
            //String description = getIntent().getStringExtra("description");
            String price = getIntent().getStringExtra("price");

            // Set the retrieved data to the views
            Picasso.get().load(image).into(productImageView); // Using Picasso for image loading
            productNameTextView.setText(name);
            //productDescriptionTextView.setText(description);
            productPriceTextView.setText("Price: " + price);
        }
    }
}
