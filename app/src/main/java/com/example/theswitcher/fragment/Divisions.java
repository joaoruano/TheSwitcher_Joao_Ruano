package com.example.theswitcher.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.theswitcher.R;
import com.example.theswitcher.adapter.DivisionListAdapter;
import com.example.theswitcher.database.SwitcherDatabase;
import com.example.theswitcher.database.dao.DivisionDAO;
import com.example.theswitcher.entity.HouseDivision;

import java.util.ArrayList;

public class Divisions extends Fragment {

    public final static String TAG = "Divisions";

    private DivisionDAO divisionDAO;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwitcherDatabase db = SwitcherDatabase.getDatabase(getContext());
        divisionDAO = db.divisionDAO();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_divisions, container, false);

        recyclerView = view.findViewById(R.id.divisions_list);

        initUI();

        return view;
    }

    private void initUI() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setAdapter(new DivisionListAdapter(getContext(), (ArrayList<HouseDivision>) divisionDAO.getAllHouseDivisions()));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), layoutManager.getOrientation()));
    }
}