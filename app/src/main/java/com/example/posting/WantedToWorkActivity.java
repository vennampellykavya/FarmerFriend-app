package com.example.posting;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.posting.Model.Work;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WantedToWorkActivity extends AppCompatActivity {

    private RecyclerView recyclerViewWorkers;
    private WorkAdapter workersAdapter;
    private List<Work> workerList;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wanted_to_work);

        // Initialize RecyclerView and adapter
        recyclerViewWorkers = findViewById(R.id.recyclerViewWorkers);
        recyclerViewWorkers.setLayoutManager(new LinearLayoutManager(this));
        workerList = new ArrayList<>();
        workersAdapter = new WorkAdapter(workerList);
        recyclerViewWorkers.setAdapter(workersAdapter);

        // Initialize Firebase Database reference
        dbRef = FirebaseDatabase.getInstance().getReference("Work");

        // Fetch data from Firebase
        fetchWorkersData();
    }

    private void fetchWorkersData() {
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    workerList.clear(); // Clear list before adding new data
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Work worker = snapshot.getValue(Work.class);
                        workerList.add(worker);
                    }
                    workersAdapter.notifyDataSetChanged(); // Notify adapter of data change
                } else {
                    Log.d(TAG, "No data available");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(WantedToWorkActivity.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
