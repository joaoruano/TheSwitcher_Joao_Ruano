package com.example.theswitcher;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.theswitcher.database.SwitcherDatabase;
import com.example.theswitcher.database.dao.DivisionDAO;
import com.example.theswitcher.entity.HouseDivision;
import com.example.theswitcher.fragment.Divisions;
import com.example.theswitcher.fragment.LightOfDivision;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public Toolbar toolbar;
    private DivisionDAO divisionDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SwitcherDatabase db = SwitcherDatabase.getDatabase(MainActivity.this);
        divisionDAO = db.divisionDAO();

        if (divisionDAO.getDivisionCount() == 0) {
            fillDatabase();
        }

        setToolBar(View.INVISIBLE, getString(R.string.app_name));

        initUI();
    }

    @Override
    public void onBackPressed() {
        Fragment divFrag = getSupportFragmentManager().findFragmentByTag(Divisions.TAG);
        if (divFrag != null && divFrag.isVisible()) {
            this.finish();
        } else {
            super.onBackPressed();
            setToolBar(View.INVISIBLE, getString(R.string.app_name));
        }
    }

    private void initUI() {
        Divisions divisionsFrag = new Divisions();
        fragmentLoader(divisionsFrag, new Bundle(), Divisions.TAG);
    }

    public void fragmentLoader(Fragment fragment, Bundle bundle, String TAG) {

        if (TAG.equalsIgnoreCase(LightOfDivision.TAG)) {
            setToolBar(View.VISIBLE, bundle.getString("HouseDivision"));
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment, TAG);
        ft.addToBackStack(null);
        ft.commit();

        fragment.setArguments(bundle);
    }

    private void setToolBar(int backVis, String barDesc) {
        toolbar = findViewById(R.id.tool_bar);

        FrameLayout backFL = toolbar.findViewById(R.id.fl_back_image);
        TextView toolTitle = toolbar.findViewById(R.id.toolbar_title);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        toolTitle.setText(barDesc);
        backFL.setVisibility(backVis);

        backFL.setOnClickListener(view -> {
            toolTitle.setText(R.string.app_name);
            backFL.setVisibility(View.INVISIBLE);
            onBackPressed();
        });
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