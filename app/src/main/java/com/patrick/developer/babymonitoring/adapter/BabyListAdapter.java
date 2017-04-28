package com.patrick.developer.babymonitoring.adapter;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.patrick.developer.babymonitoring.R;
import com.patrick.developer.babymonitoring.fragment.SizeFragment;
import com.patrick.developer.babymonitoring.fragment.WeightFragment;
import com.patrick.developer.babymonitoring.model.entity.Baby;
import com.patrick.developer.babymonitoring.tools.FragmentTool;

import java.util.List;

/**
 * Created by developer on 4/27/17.
 */

public class BabyListAdapter extends BaseAdapter {

    protected Context context;
    List<Baby> babies;

    public BabyListAdapter(Context context, List<Baby> babies) {
        this.context = context;
        this.babies = babies;
    }

    @Override
    public int getCount() {
        return babies.size();
    }

    @Override
    public Object getItem(int i) {
        return babies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.baby_items_layout, null);
        }
        Baby currentBaby = babies.get(i);
        TextView fullNameTextView = (TextView) view.findViewById(R.id.fullName);
        fullNameTextView.setText(currentBaby.getFirstName() + " " + currentBaby.getLastName());

        Button modifyButton = (Button) view.findViewById(R.id.modify);
        modifyButton.setTag("modify_" + i);
        modifyButton.setOnClickListener(modifyListener);

        Button memoButton = (Button) view.findViewById(R.id.memo);
        memoButton.setTag("memo_" + i);
        memoButton.setOnClickListener(memoListener);

        Button weightButton = (Button) view.findViewById(R.id.weight);
        weightButton.setTag("weight_" + i);
        weightButton.setOnClickListener(weightListener);

        Button sizeButton = (Button) view.findViewById(R.id.size);
        sizeButton.setTag("size_" + i);
        sizeButton.setOnClickListener(sizeListener);

        return view;
    }

    protected View.OnClickListener modifyListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String currentTag = (String) view.getTag();
            Integer index = Integer.parseInt(currentTag.split("_")[1].trim());

            Toast.makeText(context, "Modification de " + babies.get(index).getFirstName(),Toast.LENGTH_SHORT).show();
        }
    };

    protected View.OnClickListener memoListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String currentTag = (String) view.getTag();
            Integer index = Integer.parseInt(currentTag.split("_")[1].trim());

            Toast.makeText(context, "Mémo de " + babies.get(index).getFirstName(),Toast.LENGTH_SHORT).show();
        }
    };

    protected View.OnClickListener weightListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String currentTag = (String) view.getTag();
            Integer index = Integer.parseInt(currentTag.split("_")[1].trim());

            final Fragment fragment = new WeightFragment();
            final Bundle bundle = new Bundle();
            bundle.putSerializable("baby", babies.get(index));
            fragment.setArguments(bundle);
            FragmentTool.replaceFragment(context, fragment);
        }
    };

    protected View.OnClickListener sizeListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String currentTag = (String) view.getTag();
            Integer index = Integer.parseInt(currentTag.split("_")[1].trim());

            final Fragment fragment = new SizeFragment();
            final Bundle bundle = new Bundle();
            bundle.putSerializable("baby", babies.get(index));
            fragment.setArguments(bundle);
            FragmentTool.replaceFragment(context, fragment);
        }
    };
}
