package com.example.theswitcher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.theswitcher.R;
import com.example.theswitcher.database.SwitcherDatabase;
import com.example.theswitcher.database.dao.DivisionDAO;
import com.example.theswitcher.entity.HouseDivision;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;

public class DivisionListAdapter extends RecyclerView.Adapter<DivisionListAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<HouseDivision> houseDivList;

    public DivisionListAdapter(Context context, ArrayList<HouseDivision> houseDivList) {
        this.context = context;
        this.houseDivList = houseDivList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.division_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.divisionText.setText(houseDivList.get(position).getDivision());
        holder.lightSwitch.setChecked(houseDivList.get(position).isLight());

        holder.lightSwitch.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            SwitcherDatabase db = SwitcherDatabase.getDatabase(context);
            DivisionDAO divisionDAO = db.divisionDAO();

            divisionDAO.updateLight(houseDivList.get(holder.getAdapterPosition()).getDivision(), isChecked);
        });
    }

    @Override
    public int getItemCount() {
        return houseDivList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView divisionText;
        SwitchMaterial lightSwitch;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            divisionText = itemView.findViewById(R.id.division_textview);
            lightSwitch = itemView.findViewById(R.id.light_switch);
        }
    }
}

