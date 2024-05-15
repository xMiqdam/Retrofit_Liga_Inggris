package com.example.api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    Context context;
    List<Team> english;

    public Adapter(Context context, List<Team> english) {
        this.context = context;
        this.english = english;
    }
    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item, parent, false);
        return new Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {
        Team team = english.get(position);
        holder.tvNama.setText(team.getStrTeam());
        holder.tvStadion.setText(team.getStrStadium());
        Glide.with(context).load(team.getStrTeamBadge()).into(holder.ivClub);
    }

    @Override
    public int getItemCount() {
        return english.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivClub;

        TextView tvNama;

        TextView tvStadion;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivClub = itemView.findViewById(R.id.ivItem);
            tvNama = itemView.findViewById(R.id.tvNamaObject);
            tvStadion = itemView.findViewById(R.id.tvStadion);
        }
    }
}
