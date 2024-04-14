package com.example.tempsign.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tempsign.R;
import com.example.tempsign.adapters.ParkingLotAdapter;
import com.example.tempsign.models.ParkingLot;
import com.example.tempsign.services.DataService;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentParkingLot#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentParkingLot extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<ParkingLot> dataSet; //המערך רשומות
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager; //ההגדרות שלו (למעלה למטה\שמאל ימין)
    private ParkingLotAdapter adapter;
    private SearchView searchView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Define interface for communication

    public fragmentParkingLot() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragmentParkingLot.
     */
    // TODO: Rename and change types and number of parameters
    public static fragmentParkingLot newInstance(String param1, String param2) {
        fragmentParkingLot fragment = new fragmentParkingLot();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parking_lot, container, false);

        //build rv:
        dataSet = new ArrayList<>();
        recyclerView = view.findViewById(R.id.resview);
        linearLayoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator()); // add data to your array


        dataSet= DataService.getArrParkingLots(); //Add data to your array using DataService
        adapter=new ParkingLotAdapter(dataSet);
        recyclerView.setAdapter(adapter);


        searchView = view.findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String query) {
                // Handle search submission (if needed)
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Handle text changes in the search view
                filter(newText); // Implement your filtering logic
                return true;
            }
        });

        return view;

    }
    private void filter(String query) {

        adapter.getFilter().filter(query);
    }
}