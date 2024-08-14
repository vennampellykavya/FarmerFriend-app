package com.example.posting;

 // Replace with your actual package name

//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentTransaction;
//
//import com.example.posting.ViewHolder.ProductViewHolder;
//
//public class Profile extends Fragment {
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_profile, container, false);
//
//        // Find the buttons in the layout
//        Button addButton = view.findViewById(R.id.button_add);
//        Button viewButton = view.findViewById(R.id.button_view);
//
//        // Set up the click listeners for the buttons
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle the Add button click
//                // Toast.makeText(getActivity(), "Add button clicked", Toast.LENGTH_SHORT).show();
//                // Add your logic here
//                AddProducts addProductFragment = new AddProducts();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_container,new  AddProducts()); // Ensure this matches your container ID
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });
//
//        viewButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle the View button click
//                //Toast.makeText(getActivity(), "View button clicked", Toast.LENGTH_SHORT).show();
//                // Add your logic here
//                Fragment viewProductsFragment = new ProductViewHolder();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_container, viewProductsFragment); // Ensure this matches your container ID
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });
//
//        return view;
//    }
//}


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Profile extends Fragment {

    private Button btnAddProduct;
    private Button viewbtn;

    private TextView userNameTextView, userPhoneTextView,userAddressTextView,userEmail,username;

    private FirebaseAuth mAuth;
    private DatabaseReference usersRef;
    private FirebaseUser currentUser;
    private ProgressDialog loadingBar;

    private Button lgot;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile, container, false);


        userNameTextView = view.findViewById(R.id.userNameTextView);
        userPhoneTextView = view.findViewById(R.id.userPhoneTextView);
        userAddressTextView=view.findViewById(R.id.addressdiaplay);
        userEmail=view.findViewById(R.id.displayemail);
        username=view.findViewById(R.id.username);
        btnAddProduct = view.findViewById(R.id.button_add);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        usersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser.getUid());


        loadingBar = new ProgressDialog(getActivity());

        loadUserData();
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open AddProducts activity
                Intent intent = new Intent(getActivity(), Category.class);
                startActivity(intent);
            }
        });


        viewbtn = view.findViewById(R.id.button_view);
        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open MyProducts activity to view added products
                Intent intent = new Intent(getActivity(), MyProducts.class);
                startActivity(intent);
            }
        });

        lgot= view.findViewById(R.id.button_logout);
        lgot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }


        });

        return view;
    }

    private void loadUserData() {
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String name = dataSnapshot.child("name").getValue().toString();
                    String phone = dataSnapshot.child("phone").getValue().toString();
                    String email=dataSnapshot.child("email").getValue().toString();
                    String address=dataSnapshot.child("address").getValue().toString();
                    String uname=dataSnapshot.child("name").getValue().toString();
                    //String profileImage = dataSnapshot.child("profileImage").getValue().toString();

                    userNameTextView.setText(name);
                    userPhoneTextView.setText(phone);
                    userEmail.setText(email);
                    userAddressTextView.setText(address);
                    username.setText(uname);

                    // Load profile image using Glide library
//                    Glide.with(getActivity())
//                            .load(profileImage)
//                            .placeholder(R.drawable.baseline_account_circle_24)
//                            .into(profileImageView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Failed to load user data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }




}








