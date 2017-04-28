package com.patrick.developer.babymonitoring.init;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by developer on 4/26/17.
 */

public class InitDataBoy {

    protected List<Float> wMin;
    protected List<Float> wMax;

    protected List<Float> sMin;
    protected List<Float> sMax;

    public InitDataBoy() {
        wMin = new ArrayList<>();
        wMax = new ArrayList<>();
        sMin = new ArrayList<>();
        sMax = new ArrayList<>();

        /** a la naissance **/
        wMin.add((float)2.6);
        wMax.add((float)4.0);
        sMin.add((float)45.0);
        sMax.add((float)55.0);

        /** 1 mois **/
        wMin.add((float)3.0);
        wMax.add((float)5.0);
        sMin.add((float)49.0);
        sMax.add((float)55.0);

        /** 2 mois **/
        wMin.add((float)3.7);
        wMax.add((float)5.9);
        sMin.add((float)52.0);
        sMax.add((float)59.5);

        /** 3 mois **/
        wMin.add((float)4.5);
        wMax.add((float)6.9);
        sMin.add((float)55.0);
        sMax.add((float)62.0);

        /** 4 mois **/
        wMin.add((float)5.1);
        wMax.add((float)7.7);
        sMin.add((float)57.5);
        sMax.add((float)65.0);

        /** 5 mois **/
        wMin.add((float)5.5);
        wMax.add((float)8.5);
        sMin.add((float)59.5);
        sMax.add((float)67.5);

        /** 6 mois **/
        wMin.add((float)6.0);
        wMax.add((float)9.1);
        sMin.add((float)67.5);
        sMax.add((float)69.0);

        /** 1 an **/
        wMin.add((float)7.6);
        wMax.add((float)11.8);
        sMin.add((float)69.5);
        sMax.add((float)77.5);
    }

    public List<Float> getwMin() {
        return wMin;
    }

    public void setwMin(List<Float> wMin) {
        this.wMin = wMin;
    }

    public List<Float> getwMax() {
        return wMax;
    }

    public void setwMax(List<Float> wMax) {
        this.wMax = wMax;
    }

    public List<Float> getsMin() {
        return sMin;
    }

    public void setsMin(List<Float> sMin) {
        this.sMin = sMin;
    }

    public List<Float> getsMax() {
        return sMax;
    }

    public void setsMax(List<Float> sMax) {
        this.sMax = sMax;
    }
}
