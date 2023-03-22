package com.example.theswitcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Adapter;

import com.example.theswitcher.adapter.DivisionListAdapter;
import com.example.theswitcher.database.SwitcherDatabase;
import com.example.theswitcher.database.dao.DivisionDAO;
import com.example.theswitcher.entity.HouseDivision;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DivisionDAO divisionDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.divisions_list);


        SwitcherDatabase db = SwitcherDatabase.getDatabase(MainActivity.this);
        divisionDAO = db.divisionDAO();

        if (divisionDAO.getDivisionCount() == 0) {
            fillDatabase();
        }

        ArrayList<HouseDivision> l = (ArrayList<HouseDivision>) divisionDAO.getAllHouseDivisions();

        DivisionListAdapter a = new DivisionListAdapter(getApplicationContext(), l);

        //divList.setAdapter(new DivisionListAdapter(MainActivity.this, (ArrayList<HouseDivision>) divisionDAO.getAllHouseDivisions()));
        recyclerView.setAdapter(a);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void fillDatabase() {
        List<HouseDivision> houseDivisions = new ArrayList<>();

        HouseDivision kitchen = new HouseDivision("Kitchen", false);
        HouseDivision livRoom = new HouseDivision("Living room", false);
        HouseDivision mastRoom = new HouseDivision("Master bedroom", false);
        HouseDivision guestRoom = new HouseDivision("Guest's bedroom", false);
        houseDivisions.add(kitchen);
        houseDivisions.add(livRoom);
        houseDivisions.add(mastRoom);
        houseDivisions.add(guestRoom);

        divisionDAO.insertAll(houseDivisions);
    }
}