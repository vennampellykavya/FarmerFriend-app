package com.example.posting;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.posting.databinding.ActivityHome2Binding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.FirebaseApp;

public class Main2 extends AppCompatActivity {

    ActivityHome2Binding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHome2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        FirebaseApp.initializeApp(this);

        // Set default fragment
        replaceFragment(new Home());

        // Handle navigation item clicks
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                int itemid=item.getItemId();
                if(itemid==R.id.home){
                    selectedFragment = new Home();

                }
                else if(itemid==R.id.farmworker) {
                    selectedFragment = new FarmWorker();
                }
                else if(itemid==R.id.profile) {
                    selectedFragment = new Profile();
                }

                return replaceFragment(selectedFragment);
            }
        });
    }

    private boolean replaceFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment);
            fragmentTransaction.commit();
            return true;
        }
        return false;
    }
}
