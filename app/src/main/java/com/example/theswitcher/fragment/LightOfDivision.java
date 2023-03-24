package com.example.theswitcher.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.theswitcher.R;
import com.example.theswitcher.database.SwitcherDatabase;
import com.example.theswitcher.database.dao.DivisionDAO;

public class LightOfDivision extends Fragment {

    public final static String TAG = "LightOfDivision";
    private static final String ARG_DIVISION = "HouseDivision";

    private String mDivParam;
    private DivisionDAO divisionDAO;
    private ImageView lightImg;
    private TextView divTxt;
    private TextView lightTxt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwitcherDatabase db = SwitcherDatabase.getDatabase(getContext());
        divisionDAO = db.divisionDAO();

        if (getArguments() != null) {
            mDivParam = getArguments().getString(ARG_DIVISION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_light_of_divison, container, false);

        lightImg = view.findViewById(R.id.light_image);
        divTxt = view.findViewById(R.id.division_text);
        lightTxt = view.findViewById(R.id.light_text);

        initUI();

        return view;
    }

    private void initUI() {
        if (divisionDAO.getLight(mDivParam)) {
            lightImg.setImageResource(R.drawable.light_on);
            lightTxt.setText(R.string.on);
        } else {
            lightImg.setImageResource(R.drawable.light_off);
            lightTxt.setText(R.string.off);
        }

        divTxt.setText(getString(R.string.your_division_light_is).replace("DIVISION", mDivParam));
    }
}