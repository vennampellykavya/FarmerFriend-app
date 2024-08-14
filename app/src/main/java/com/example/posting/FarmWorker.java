package com.example.posting;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class FarmWorker extends Fragment {

    private Button btnNeedFarmWorkers;
    private Button btnWantedToWork;

    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_farm_worker);
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_farm_worker, container, false);

        btnNeedFarmWorkers = view.findViewById(R.id.button);
        btnWantedToWork = view.findViewById(R.id.button2);

        btnNeedFarmWorkers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to NeedWorkersFragment or any other activity for needing farm workers
                Intent intent = new Intent(getActivity(), needFarmworkers.class);
                startActivity(intent);
            }
        });

        btnWantedToWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to WantedToWorkActivity or any other activity for wanted to work
                Intent intent = new Intent(getActivity(), WantedToWorkActivity.class);
                startActivity(intent);

            }
        });
        return view;
    }
}

