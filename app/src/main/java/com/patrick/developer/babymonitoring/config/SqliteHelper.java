package com.patrick.developer.babymonitoring.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.patrick.developer.babymonitoring.model.entity.Baby;
import com.patrick.developer.babymonitoring.model.entity.BabySize;
import com.patrick.developer.babymonitoring.model.entity.BabyWeight;

import java.sql.SQLException;

/**
 * Created by developer on 10/5/16.
 */

public class SqliteHelper extends OrmLiteSqliteOpenHelper {
    protected static final String DATABASE_NAME = "baby_monitoring_db";
    protected static final int DATABASE_VERSION = 1;

    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {

            /**
             * creates database table
             */
            TableUtils.createTable(connectionSource, Baby.class);

            TableUtils.createTable(connectionSource, BabyWeight.class);

            TableUtils.createTable(connectionSource, BabySize.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            /**
             * Recreates the database when onUpgrade is called by the framework
             */
            TableUtils.dropTable(connectionSource, Baby.class, false);
            TableUtils.dropTable(connectionSource, BabyWeight.class, false);
            TableUtils.dropTable(connectionSource, BabySize.class, false);
            onCreate(database, connectionSource);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}