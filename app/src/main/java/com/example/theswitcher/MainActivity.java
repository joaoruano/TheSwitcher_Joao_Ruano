package com.example.theswitcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.theswitcher.database.SwitcherDatabase;
import com.example.theswitcher.database.dao.DivisionDAO;
import com.example.theswitcher.entity.HouseDivision;
import com.example.theswitcher.fragment.Divisions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DivisionDAO divisionDAO;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout frameLayout = findViewById(R.id.fragment_container);

        actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle(R.string.app_name);

        SwitcherDatabase db = SwitcherDatabase.getDatabase(MainActivity.this);
        divisionDAO = db.divisionDAO();

        if (divisionDAO.getDivisionCount() == 0) {
            fillDatabase();
        }

        Divisions divisionsFrag = new Divisions();
        fragmentLoader(divisionsFrag, new Bundle(), Divisions.TAG);
    }

    public void fragmentLoader(Fragment fragment, Bundle bundle, String TAG) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment, TAG);
        ft.addToBackStack(null);
        ft.commit();

        fragment.setArguments(bundle);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (actionBar != null) {
                actionBar.setTitle(R.string.app_name);
                actionBar.setDisplayHomeAsUpEnabled(false);
            }

            onBackPressed();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Fragment divFrag = getSupportFragmentManager().findFragmentByTag(Divisions.TAG);
        if (divFrag != null && divFrag.isVisible()) {
            this.finish();
        } else {
            super.onBackPressed();
            if (actionBar != null) {
                actionBar.setTitle(R.string.app_name);
                actionBar.setDisplayHomeAsUpEnabled(false);
            }
        }
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