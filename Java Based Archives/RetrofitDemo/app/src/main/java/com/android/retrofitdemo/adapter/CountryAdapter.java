package com.android.retrofitdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.retrofitdemo.R;
import com.android.retrofitdemo.model.Data;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {
    private ArrayList<Data> countriesList;

    public CountryAdapter(ArrayList<Data> countriesList) {
        this.countriesList = countriesList;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_country, parent, false);
        return new CountryViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        Data country = countriesList.get(position);
        String countryName = "Unknown";
        if (country.getName() != null) {
            countryName = String.valueOf(country.getName());
        }
        holder.countryNameTextView.setText(countryName);

        if (country.getCapital() != null) {
            holder.countryCapitalTextView.setText(country.getCapital().toString());
        } else {
            holder.countryCapitalTextView.setText("Unknown"); // Or any default value you prefer
        }
    }


    @Override
    public int getItemCount() {
        return countriesList.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder{

        TextView countryNameTextView;
        TextView countryCapitalTextView;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            countryNameTextView = itemView.findViewById(R.id.tv_country_name);
            countryCapitalTextView = itemView.findViewById(R.id.tv_country_capital);

        }
    }
}
