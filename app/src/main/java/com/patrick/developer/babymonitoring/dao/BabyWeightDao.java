package com.patrick.developer.babymonitoring.dao;

import android.content.Context;

import com.patrick.developer.babymonitoring.config.DaoManager;
import com.patrick.developer.babymonitoring.model.entity.BabyWeight;

import java.sql.SQLException;

/**
 * Created by developer on 4/26/17.
 */

public class BabyWeightDao extends AbstractDao<BabyWeight, Long> {

    Context context;

    public BabyWeightDao(Context context) {
        this.context = context;
        try {
            this.dao = new DaoManager(context).getBabyWeightDao();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
