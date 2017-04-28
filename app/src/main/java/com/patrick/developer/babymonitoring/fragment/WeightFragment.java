package com.patrick.developer.babymonitoring.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.patrick.developer.babymonitoring.R;
import com.patrick.developer.babymonitoring.init.InitChart;
import com.patrick.developer.babymonitoring.model.entity.Baby;
import com.patrick.developer.babymonitoring.tools.constant.BackStatus;
import com.patrick.developer.babymonitoring.tools.constant.PreviousStatus;

/**
 * Created by developer on 4/27/17.
 */

public class WeightFragment extends Fragment {
    View rootView;
    Toolbar toolbar;

    LineChart chart;
    LineData lineData;

    TextView infoBabyTextView;
    TextView titleFormTextView;
    EditText valueEditText;
    EditText monthEditText;
    Button saveButton;

    Baby baby;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.baby_evolution_graph_fragment, container, false);

        setToolBar();

        setView();

        getArgument();

        setInfoBaby();

        lineData = initializeChart();

        initializeChartBaby();

        chart.setData(lineData);
        chart.invalidate();

        addWeight();

        return rootView;
    }

    protected void setToolBar() {
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Suivi des poids");
        BackStatus.previous = PreviousStatus.home.name();
    }

    protected void setView() {
        chart = (LineChart) rootView.findViewById(R.id.chart);
        infoBabyTextView = (TextView) rootView.findViewById(R.id.infoBaby);

        titleFormTextView = (TextView) rootView.findViewById(R.id.title_form);
        titleFormTextView.setText("Nouvelle poids");

        valueEditText = (EditText) rootView.findViewById(R.id.value);
        valueEditText.setHint("Poids en Kg");

        monthEditText = (EditText) rootView.findViewById(R.id.month);
        monthEditText.setHint("Mois du bébé");
        saveButton = (Button) rootView.findViewById(R.id.saveButton);
    }

    protected void getArgument() {
        Bundle bundle = getArguments();
        if(bundle.containsKey("baby")) {
            baby = (Baby) bundle.get("baby");
        }
    }

    protected void setInfoBaby() {
        infoBabyTextView.setText(Html.fromHtml(baby.toString()));
    }

    protected LineData initializeChart() {
        LineData lineData = InitChart.initChartWeight(getActivity(), baby);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawLabels(true);
        xAxis.setValueFormatter(iAxisValueFormatterX);

        YAxis yAxisLeft = chart.getAxisLeft();
        yAxisLeft.setValueFormatter(iAxisValueFormatterY);

        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setValueFormatter(iAxisValueFormatterY);
        return lineData;
    }

    protected IAxisValueFormatter iAxisValueFormatterX = new IAxisValueFormatter() {
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return (int)value + " mois";
        }
    };

    protected IAxisValueFormatter iAxisValueFormatterY = new IAxisValueFormatter() {
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return value + " Kg";
        }
    };

    protected void initializeChartBaby() {
        lineData = InitChart.initCharBabyWeight(getActivity(), lineData, baby);
    }

    protected void addWeight() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Ajouter", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
