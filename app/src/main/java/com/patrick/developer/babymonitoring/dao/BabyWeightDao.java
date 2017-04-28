package com.patrick.developer.babymonitoring.dao;

import android.content.Context;

import com.patrick.developer.babymonitoring.config.DaoManager;
import com.patrick.developer.babymonitoring.model.entity.Baby;
import com.patrick.developer.babymonitoring.model.entity.BabyWeight;
import com.patrick.developer.babymonitoring.tools.constant.Constant;
import com.patrick.developer.babymonitoring.tools.constant.Sexe;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<BabyWeight> getAllMinsOrMaxs(String constraint) {
        List<BabyWeight> results = new ArrayList<>();
        try {
            results = dao.queryBuilder().where().like("OBS",constraint).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public List<BabyWeight> findByBaby(Baby baby) {
        List<BabyWeight> results = new ArrayList<>();
        try {
            results = dao.queryBuilder().where().like("BABY_ID",baby.getId())
                    .and().like("OBS", baby.getSexe()).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
