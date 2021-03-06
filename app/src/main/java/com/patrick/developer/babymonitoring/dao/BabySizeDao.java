package com.patrick.developer.babymonitoring.dao;

import android.content.Context;

import com.patrick.developer.babymonitoring.config.DaoManager;
import com.patrick.developer.babymonitoring.model.entity.Baby;
import com.patrick.developer.babymonitoring.model.entity.BabySize;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<BabySize> getAllMinsOrMaxs(String constraint) {
        List<BabySize> results = new ArrayList<>();
        try {
            results = dao.queryBuilder().where().like("OBS",constraint).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public List<BabySize> findByBaby(Baby baby) {
        List<BabySize> results = new ArrayList<>();
        try {
            results = dao.queryBuilder().where().like("BABY_ID",baby.getId())
                    .and().like("OBS", baby.getSexe()).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
