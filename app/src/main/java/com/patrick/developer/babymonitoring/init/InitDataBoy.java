package com.patrick.developer.babymonitoring.init;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by developer on 4/26/17.
 */

public class InitDataBoy {

    protected List<Double> sizeMin;
    protected List<Double> sizeMax;

    protected List<Double> weightMin;
    protected List<Double> weightMax;

    public InitDataBoy() {
        sizeMin = new ArrayList<>();
        sizeMax = new ArrayList<>();
        weightMin = new ArrayList<>();
        weightMax = new ArrayList<>();

        /** a la naissance **/
        sizeMin.add(2.6);
        sizeMax.add(4.0);
        weightMin.add(45.0);
        weightMax.add(55.0);

        /** 1 mois **/
        sizeMin.add(3.0);
        sizeMax.add(5.0);
        weightMin.add(49.0);
        weightMax.add(55.0);

        /** 2 mois **/
        sizeMin.add(3.7);
        sizeMax.add(5.9);
        weightMin.add(52.0);
        weightMax.add(59.5);

        /** 3 mois **/
        sizeMin.add(4.5);
        sizeMax.add(6.9);
        weightMin.add(55.0);
        weightMax.add(62.0);

        /** 4 mois **/
        sizeMin.add(5.1);
        sizeMax.add(7.7);
        weightMin.add(57.5);
        weightMax.add(65.0);

        /** 5 mois **/
        sizeMin.add(5.5);
        sizeMax.add(8.5);
        weightMin.add(59.5);
        weightMax.add(67.5);

        /** 6 mois **/
        sizeMin.add(6.0);
        sizeMax.add(9.1);
        weightMin.add(67.5);
        weightMax.add(69.0);

        /** 1 an **/
        sizeMin.add(7.6);
        sizeMax.add(11.8);
        weightMin.add(69.5);
        weightMax.add(77.5);
    }

    public List<Double> getSizeMin() {
        return sizeMin;
    }

    public void setSizeMin(List<Double> sizeMin) {
        this.sizeMin = sizeMin;
    }

    public List<Double> getSizeMax() {
        return sizeMax;
    }

    public void setSizeMax(List<Double> sizeMax) {
        this.sizeMax = sizeMax;
    }

    public List<Double> getWeightMin() {
        return weightMin;
    }

    public void setWeightMin(List<Double> weightMin) {
        this.weightMin = weightMin;
    }

    public List<Double> getWeightMax() {
        return weightMax;
    }

    public void setWeightMax(List<Double> weightMax) {
        this.weightMax = weightMax;
    }
}
