package com.patrick.developer.babymonitoring.config;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.patrick.developer.babymonitoring.model.entity.Baby;
import com.patrick.developer.babymonitoring.model.entity.BabySize;
import com.patrick.developer.babymonitoring.model.entity.BabyWeight;

import java.sql.SQLException;

/**
 * Created by developer on 10/5/16.
 */

public class DaoManager {

    SqliteHelper sqliteHelper;

    Dao<Baby, Long> babyDao;

    Dao<BabyWeight, Long> babyWeightDao;

    Dao<BabySize, Long> babySizeDao;

    public DaoManager(Context context) {
        sqliteHelper = OpenHelperManager.getHelper(context, SqliteHelper.class);
    }

    public Dao<Baby, Long> getBabyDao() throws SQLException {
        if(babyDao == null)
            babyDao = sqliteHelper.getDao(Baby.class);
        return babyDao;
    }

    public Dao<BabyWeight, Long> getBabyWeightDao() throws SQLException {
        if(babyWeightDao == null)
            babyWeightDao = sqliteHelper.getDao(BabyWeight.class);
        return babyWeightDao;
    }

    public Dao<BabySize, Long> getBabySizeDao() throws SQLException {
        if(babySizeDao == null)
            babySizeDao = sqliteHelper.getDao(BabySize.class);
        return babySizeDao;
    }
}
