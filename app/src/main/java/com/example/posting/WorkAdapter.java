package com.example.posting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.posting.Model.Work;
import com.example.posting.Model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.WorkerViewHolder> {

    private List<Work> workerList;
    private DatabaseReference usersRef;
    private FirebaseAuth auth;

    public WorkAdapter(List<Work> workerList) {
        this.workerList = workerList;
        usersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public WorkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.worker_item, parent, false);
        return new WorkerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerViewHolder holder, int position) {
        Work worker = workerList.get(position);

        // Set work details
        holder.workType.setText("WorkType: "+worker.getWorkType());
        holder.wage.setText("Wages: "+worker.getWage());
        holder.address.setText("Address: "+worker.getAddress());

        // Fetch and set user details based on userId
        fetchUserDetails(worker.getWorkerId(), holder);
    }

    @Override
    public int getItemCount() {
        return workerList.size();
    }

    private void fetchUserDetails(String userId, final WorkerViewHolder holder) {
        usersRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Users user = dataSnapshot.getValue(Users.class);
                    if (user != null) {
                        holder.userName.setText("Name: "+user.getName());
                        holder.phoneNumber.setText("Phone number: "+user.getPhone());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle potential errors here
            }
        });
    }

    public static class WorkerViewHolder extends RecyclerView.ViewHolder {
        TextView userName, phoneNumber, workType, wage, address;

        public WorkerViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.textViewUserName);
            phoneNumber = itemView.findViewById(R.id.textViewPhoneNumber);
            workType = itemView.findViewById(R.id.textViewWorkType);
            wage = itemView.findViewById(R.id.textViewWage);
            address = itemView.findViewById(R.id.textViewAddress);
        }
    }
}
