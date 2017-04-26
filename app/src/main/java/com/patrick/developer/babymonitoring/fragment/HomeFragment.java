package com.patrick.developer.babymonitoring.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.patrick.developer.babymonitoring.R;
import com.patrick.developer.babymonitoring.tools.LingeGraphTool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by developer on 4/21/17.
 */

public class HomeFragment extends Fragment {

    View rootView;
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.home_fragment, container, false);

        setToolBar();

        setView();

        return rootView;
    }

    protected void setToolBar() {
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
    }

    protected void setView() {

    }
}
