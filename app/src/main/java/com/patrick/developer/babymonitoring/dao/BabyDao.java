package com.patrick.developer.babymonitoring.dao;

import android.content.Context;

import com.patrick.developer.babymonitoring.config.DaoManager;
import com.patrick.developer.babymonitoring.model.entity.Baby;

import java.sql.SQLException;

/**
 * Created by developer on 4/26/17.
 */

public class BabyDao extends AbstractDao<Baby, Long> {

    Context context;

    public BabyDao(Context context) {
        this.context = context;
        try {
            this.dao = new DaoManager(context).getBabyDao();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
