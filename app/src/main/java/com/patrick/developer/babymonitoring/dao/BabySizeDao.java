package com.patrick.developer.babymonitoring.dao;

import android.content.Context;

import com.patrick.developer.babymonitoring.config.DaoManager;
import com.patrick.developer.babymonitoring.model.entity.BabySize;

import java.sql.SQLException;

/**
 * Created by developer on 4/26/17.
 */

public class BabySizeDao extends AbstractDao<BabySize, Long> {

    Context context;

    public BabySizeDao(Context context) {
        this.context = context;
        try {
            this.dao = new DaoManager(context).getBabySizeDao();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
