package com.patrick.developer.babymonitoring.tools;

import com.github.mikephil.charting.data.Entry;

import java.util.List;

/**
 * Created by developer on 4/21/17.
 */

public class LingeGraphTool {

    public static void addPoint(List<Entry> entries, float x, float y) {
        Entry entry = new Entry(x, y);
        entries.add(entry);
    }
}
