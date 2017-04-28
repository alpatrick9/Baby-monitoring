package com.patrick.developer.babymonitoring.init;

import android.content.Context;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.patrick.developer.babymonitoring.R;
import com.patrick.developer.babymonitoring.dao.BabySizeDao;
import com.patrick.developer.babymonitoring.dao.BabyWeightDao;
import com.patrick.developer.babymonitoring.model.entity.Baby;
import com.patrick.developer.babymonitoring.model.entity.BabySize;
import com.patrick.developer.babymonitoring.model.entity.BabyWeight;
import com.patrick.developer.babymonitoring.tools.constant.Constant;
import com.patrick.developer.babymonitoring.tools.constant.Sexe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by developer on 4/27/17.
 */

public class InitChart {

    public static LineData initChartWeight(Context context, Baby baby) {
        List<BabyWeight> min, max;

        BabyWeightDao dao = new BabyWeightDao(context);

        if(baby.getSexe() == Sexe.Fille.name()) {
            min = dao.getAllMinsOrMaxs(Constant.min_girl.name());
            max = dao.getAllMinsOrMaxs(Constant.max_girl.name());
        } else {
            min = dao.getAllMinsOrMaxs(Constant.min_boy.name());
            max = dao.getAllMinsOrMaxs(Constant.max_boy.name());
        }

        List<Entry> entriesMin = new ArrayList<>();
        for (int i = 0; i < min.size(); i++) {
            BabyWeight b = min.get(i);
            Entry e = new Entry(b.getMonth(), b.getWeight());
            entriesMin.add(e);
        }
        LineDataSet dataMin = new LineDataSet(entriesMin, "Valeur de réf. minimum");
        dataMin.setColor(context.getResources().getColor(R.color.minColor));
        dataMin.setValueTextColor(context.getResources().getColor(android.R.color.transparent));
        dataMin.setDrawCircles(false);

        List<Entry> entriesMax = new ArrayList<>();
        for (int i = 0; i < max.size(); i++) {
            BabyWeight b = max.get(i);
            Entry e = new Entry(b.getMonth(), b.getWeight());
            entriesMax.add(e);
        }
        LineDataSet dataMax = new LineDataSet(entriesMax, "Valeur de réf. maximum");
        dataMax.setColor(context.getResources().getColor(R.color.maxColor));
        dataMax.setValueTextColor(context.getResources().getColor(android.R.color.transparent));
        dataMax.setDrawCircles(false);

        LineData lineData = new LineData();
        lineData.addDataSet(dataMax);
        lineData.addDataSet(dataMin);
        return lineData;
    }

    public static LineData initChartSize(Context context, Baby baby) {
        List<BabySize> min, max;
        BabySizeDao dao = new BabySizeDao(context);

        if(baby.getSexe() == Sexe.Fille.name()) {
            min = dao.getAllMinsOrMaxs(Constant.min_girl.name());
            max = dao.getAllMinsOrMaxs(Constant.max_girl.name());
        } else {
            min = dao.getAllMinsOrMaxs(Constant.min_boy.name());
            max = dao.getAllMinsOrMaxs(Constant.max_boy.name());
        }

        List<Entry> entriesMin = new ArrayList<>();
        for (int i = 0; i < min.size(); i++) {
            BabySize b = min.get(i);
            Entry e = new Entry(b.getMonth(), b.getSize());
            entriesMin.add(e);
        }
        LineDataSet dataMin = new LineDataSet(entriesMin, "Valeur de réf. minimum");
        dataMin.setColor(context.getResources().getColor(R.color.minColor));
        dataMin.setValueTextColor(context.getResources().getColor(android.R.color.transparent));
        dataMin.setDrawCircles(false);

        List<Entry> entriesMax = new ArrayList<>();
        for (int i = 0; i < max.size(); i++) {
            BabySize b = max.get(i);
            Entry e = new Entry(b.getMonth(), b.getSize());
            entriesMax.add(e);
        }
        LineDataSet dataMax = new LineDataSet(entriesMax, "Valeur de réf. maximum");
        dataMax.setColor(context.getResources().getColor(R.color.maxColor));
        dataMax.setValueTextColor(context.getResources().getColor(android.R.color.transparent));
        dataMax.setDrawCircles(false);

        LineData lineData = new LineData();
        lineData.addDataSet(dataMax);
        lineData.addDataSet(dataMin);
        return lineData;
    }
}
