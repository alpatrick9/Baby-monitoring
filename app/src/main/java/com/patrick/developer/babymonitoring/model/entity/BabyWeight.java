package com.patrick.developer.babymonitoring.model.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by developer on 4/26/17.
 */

@DatabaseTable(tableName = "BABYWEIGHT")
public class BabyWeight {

    @DatabaseField(generatedId = true)
    protected Long id;

    @DatabaseField(columnName = "WEIGHT")
    protected Float weight;

    @DatabaseField(columnName = "MONTH", dataType = DataType.INTEGER, defaultValue = "0")
    protected int month;

    @DatabaseField(columnName = "OBS", canBeNull = true)
    protected String obs;

    @DatabaseField(columnName ="BABY_ID" ,foreign = true, canBeNull = true)
    protected Baby baby;

    public BabyWeight() {
    }

    public BabyWeight(Float weight, int month, Baby baby) {
        this.weight = weight;
        this.month = month;
        this.baby = baby;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Baby getBaby() {
        return baby;
    }

    public void setBaby(Baby baby) {
        this.baby = baby;
    }
}
