package com.example.tempsign.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tempsign.R;
import com.example.tempsign.models.ParkingLot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotAdapter extends RecyclerView.Adapter<ParkingLotAdapter.MyViewHolder> {

    static ArrayList<ParkingLot> dataset;
    static ArrayList<ParkingLot> datasetFull; // Backup of the original dataset for filtering


    public ParkingLotAdapter(ArrayList<ParkingLot> dataSet) {
        this.dataset = dataSet;
        this.datasetFull = new ArrayList<>(dataSet); // Copy the dataset for filtering
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //יוצר אצ הקארד

        // Assuming parent is a ViewGroup or a View object
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_card_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) { //עוברים על תאי המערך ומשבצים על גבי הרשומות

        holder.textName.setText(dataset.get(position).getName());
        holder.textAdress.setText(dataset.get(position).getAddress());
        holder.textParksnum.setText(dataset.get(position).getNumOfParks());
        holder.textDisablenum.setText(dataset.get(position).getNumOfDisabled());
    }

    @Override
    public int getItemCount() {

        return dataset.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textName;
        TextView textAdress;
        TextView textParksnum;
        TextView textDisablenum;

        public MyViewHolder(@NonNull View itemView) { //תוכן הקארד
            super(itemView);

            textName = itemView.findViewById(R.id.name);
            textAdress = itemView.findViewById(R.id.adress);
            textParksnum = itemView.findViewById(R.id.parksnum);
            textDisablenum = itemView.findViewById(R.id.disablesNum);

            Button button = itemView.findViewById(R.id.tocardcomment);
            button.setOnClickListener(new View.OnClickListener() { //מה אני רוצה שיקרה כשלוחצת על הכפתור1
                @Override
                public void onClick(View v) {
                    String parkId = dataset.get(getAdapterPosition()).getParkId();

                    Bundle bundle = new Bundle();
                    String valueName = textName.getText().toString();
                    String valueAdress = textAdress.getText().toString();
                    String valueNum = textParksnum.getText().toString();
                    String valueDisable = textDisablenum.getText().toString();

                    bundle.putString("name", valueName);
                    bundle.putString("address", valueAdress);
                    bundle.putString("number", valueNum);
                    bundle.putString("disable", valueDisable);
                    bundle.putString("oid", parkId);
                    Navigation.findNavController(itemView).navigate(R.id.action_global_fragmentInfoParking,bundle);
                }
            });
        }
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<ParkingLot> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(datasetFull);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (ParkingLot item : datasetFull) {
                        if (item.getName().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                dataset.clear();
                dataset.addAll((List<ParkingLot>) results.values);
                notifyDataSetChanged();
            }
        };
    }
}
