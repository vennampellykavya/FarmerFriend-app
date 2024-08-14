package com.example.posting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Category extends AppCompatActivity {
    private ImageView seeds,vegetables,saplings,vehicles,fruits,flowers;
    private ImageView organicmanure,tools;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category);

        seeds=(ImageView) findViewById(R.id.seeds);
        saplings=(ImageView) findViewById(R.id.saplings);
        organicmanure=(ImageView) findViewById(R.id.organicmanure);
        tools=(ImageView) findViewById(R.id.tools);
        vehicles=(ImageView) findViewById(R.id.vehicles);
        vegetables=(ImageView) findViewById(R.id.vegetables);
        fruits=(ImageView) findViewById(R.id.fruits);
        flowers=(ImageView) findViewById(R.id.flowers);


        seeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Category.this,AddProducts.class);
                intent.putExtra("category","seeds");
                startActivity(intent);

            }
        });

        saplings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Category.this,AddProducts.class);
                intent.putExtra("category","saplings");
                startActivity(intent);

            }
        });

        organicmanure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Category.this,AddProducts.class);
                intent.putExtra("category","organicmanure");
                startActivity(intent);

            }
        });

        vegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Category.this,AddProducts.class);
                intent.putExtra("category","vegetables");
                startActivity(intent);

            }
        });

        fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Category.this,AddProducts.class);
                intent.putExtra("category","fruits");
                startActivity(intent);

            }
        });

        flowers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Category.this,AddProducts.class);
                intent.putExtra("category","flowers");
                startActivity(intent);

            }
        });

        tools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Category.this,AddProducts.class);
                intent.putExtra("category","tools");
                startActivity(intent);

            }
        });

        vehicles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Category.this,AddProducts.class);
                intent.putExtra("category","vehicles");
                startActivity(intent);

            }
        });
    }
}