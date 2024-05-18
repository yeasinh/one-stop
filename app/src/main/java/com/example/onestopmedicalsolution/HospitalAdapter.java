package com.example.onestopmedicalsolution;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder>
{
    private ArrayList<Hospital> hospitals;
    ItemClicked activity;

    public interface ItemClicked
    {
        void onItemClicked(int index);
    }

    public HospitalAdapter(Context context,ArrayList<Hospital> list)
    {
        hospitals = list;

        activity = (ItemClicked) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvHosName,tvHosPhn,tvHosAv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvHosName = itemView.findViewById(R.id.tvHosName);
            tvHosPhn = itemView.findViewById(R.id.tvHosPhn);
            tvHosAv = itemView.findViewById(R.id.tvHosAv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    activity.onItemClicked(hospitals.indexOf((Hospital) v.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public HospitalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hospital_list,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalAdapter.ViewHolder holder, int position) {

        holder.itemView.setTag(hospitals.get(position));
        holder.tvHosName.setText(hospitals.get(position).getName());
        holder.tvHosPhn.setText(hospitals.get(position).getPhoneNumber());
        holder.tvHosAv.setText(hospitals.get(position).getAv_seats());
    }

    @Override
    public int getItemCount() {
        return hospitals.size();
    }
}
