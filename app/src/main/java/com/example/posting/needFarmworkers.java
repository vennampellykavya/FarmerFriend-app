package com.example.posting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.posting.Model.Work;
public class needFarmworkers extends AppCompatActivity {

    private EditText editTextWorkType;
    private EditText editTextWage;
    private EditText editTextAddress;
    private Button submitButton;

    private DatabaseReference workDatabaseRef;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_farmworkers);

        editTextWorkType = findViewById(R.id.textwork);
        editTextWage = findViewById(R.id.wagetext);
        editTextAddress = findViewById(R.id.addresstext);
        submitButton = findViewById(R.id.submit_button);

        auth = FirebaseAuth.getInstance();
        workDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Work");

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitWork();
            }
        });
    }

    private void submitWork() {
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser == null) {
            // User not authenticated, handle appropriately
            Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show();
            return;
        }

        String userId = currentUser.getUid();
        String workType = editTextWorkType.getText().toString().trim();
        String wage = editTextWage.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();

        if (workType.isEmpty() || wage.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new Work object
        Work work = new Work(userId, workType, wage, address);

        // Generate a new key for the work entry
        String workId = workDatabaseRef.push().getKey();

        if (workId == null) {
            Toast.makeText(this, "Failed to generate work ID", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save the work under Work node with the generated workId
        workDatabaseRef.child(workId).setValue(work)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(needFarmworkers.this, "Work submitted successfully", Toast.LENGTH_SHORT).show();
                    // Clear EditText fields after submission
                    editTextWorkType.setText("");
                    editTextWage.setText("");
                    editTextAddress.setText("");
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(needFarmworkers.this, "Failed to submit work: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}

