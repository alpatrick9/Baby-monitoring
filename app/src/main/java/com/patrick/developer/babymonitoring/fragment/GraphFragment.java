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
 * Created by developer on 4/26/17.
 */

public class GraphFragment extends Fragment {

    View rootView;
    Toolbar toolbar;
    LineChart chart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.graph_fragment, container, false);

        setToolBar();

        setView();

        initializeLineGraph();

        return rootView;
    }

    protected void setToolBar() {
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
    }

    protected void setView() {
        chart = (LineChart) rootView.findViewById(R.id.chart);
    }

    protected void initializeLineGraph() {
        List<Entry> entries = new ArrayList<>();

        float max = 20;
        float min = 0;

        for(int i = 0; i < 10; i++) {
            Random r = new Random();
            float x = (float) i;

            float y = r.nextFloat() * (max - min) + min;
            LingeGraphTool.addPoint(entries, x, y);
        }

        LineDataSet dataSet = new LineDataSet(entries, "Valeur de reférence"); // add entries to dataset
        dataSet.setColor(getResources().getColor(R.color.red));

        LineData lineData = new LineData(dataSet);

        List<Entry> entries2 = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            Random r = new Random();
            float x = (float) i;

            float y = r.nextFloat() * (max - min) + min;
            LingeGraphTool.addPoint(entries2, x, y);
        }

        LineDataSet dataSet2 = new LineDataSet(entries2, "Autre"); // add entries to dataset
        dataSet2.setColor(getResources().getColor(R.color.defaultColor));

        lineData.addDataSet(dataSet2);

        chart.setData(lineData);
        chart.invalidate();

    }
}
