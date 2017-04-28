package com.patrick.developer.babymonitoring.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.patrick.developer.babymonitoring.R;
import com.patrick.developer.babymonitoring.adapter.BabyListAdapter;
import com.patrick.developer.babymonitoring.dao.BabyDao;
import com.patrick.developer.babymonitoring.model.entity.Baby;
import com.patrick.developer.babymonitoring.tools.dialog_manager.AddBabyDialogBox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by developer on 4/21/17.
 */

public class HomeFragment extends Fragment {

    View rootView;
    Toolbar toolbar;
    FloatingActionButton addButton;
    ListView babyListView;

    List<Baby> babies = new ArrayList<>();
    BabyDao babyDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.home_fragment, container, false);

        setToolBar();

        setView();

        initializeProviders();

        setData();

        bindDataView();

        return rootView;
    }

    protected void setToolBar() {
        toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
    }

    protected void setView() {
        addButton = (FloatingActionButton) rootView.findViewById(R.id.addBabyButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Baby baby = new Baby();
                AddBabyDialogBox.addBaby(getActivity(), baby);
            }
        });

        babyListView = (ListView) rootView.findViewById(R.id.babyList);
    }

    protected void initializeProviders() {
        babyDao = new BabyDao(getActivity());
    }

    protected void setData() {
        try {
            babies = babyDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void bindDataView() {
        BabyListAdapter adapter = new BabyListAdapter(getActivity(), babies);
        babyListView.setAdapter(adapter);
    }
}
