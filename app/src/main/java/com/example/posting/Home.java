package com.example.posting;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        ImageView seedsImageView = view.findViewById(R.id.seeds);
        ImageView vehiclesImageView = view.findViewById(R.id.vehicles);
        ImageView organicManureImageView = view.findViewById(R.id.organicmanure);
        ImageView toolsImageView = view.findViewById(R.id.tools);

        ImageView saplingsImageView = view.findViewById(R.id.saplings);
        ImageView vegetablesImageView = view.findViewById(R.id.vegetables);
        ImageView flowersImageView = view.findViewById(R.id.flowers);
        ImageView fruitsImageView = view.findViewById(R.id.fruits);


        seedsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoryProductActivity("seeds");
            }
        });

        saplingsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoryProductActivity("saplings");
            }
        });

        organicManureImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoryProductActivity("organicmanure");
            }
        });

        vegetablesImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoryProductActivity("vegetables");
            }
        });

    fruitsImageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openCategoryProductActivity("fruits");
        }
    });

    flowersImageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        openCategoryProductActivity("flowers");
    }
});

    toolsImageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        openCategoryProductActivity("tools");
    }
});

   vehiclesImageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        openCategoryProductActivity("vehicles");
    }
});
        return view;
    }

    private void openCategoryProductActivity(String category) {
        Intent intent = new Intent(getActivity(), CategoryProductActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);


    }



}
