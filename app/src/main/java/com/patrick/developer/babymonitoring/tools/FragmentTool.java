package com.patrick.developer.babymonitoring.tools;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.widget.RelativeLayout;

import com.patrick.developer.babymonitoring.R;

/**
 * Created by developer on 4/21/17.
 */

public class FragmentTool {

    public static void replaceFragment(Context context, Fragment fragment) {
        if (fragment != null) {
            RelativeLayout maLayout = (RelativeLayout) ((Activity)context).findViewById(R.id.contenaire);
            maLayout.removeAllViews();
            FragmentManager fragmentManager = ((Activity)context).getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.contenaire, fragment).commit();
        }
    }
}
