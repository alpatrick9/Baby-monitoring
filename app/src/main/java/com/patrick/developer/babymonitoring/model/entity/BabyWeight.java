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
    protected Double weight;

    @DatabaseField(columnName = "MOUNTH", dataType = DataType.INTEGER, defaultValue = "0")
    protected int mounth;

    @DatabaseField(foreign = true, canBeNull = true)
    protected Baby baby;

    public BabyWeight() {
    }

    public BabyWeight(Double weight, int mounth, Baby baby) {
        this.weight = weight;
        this.mounth = mounth;
        this.baby = baby;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public int getMounth() {
        return mounth;
    }

    public void setMounth(int mounth) {
        this.mounth = mounth;
    }

    public Baby getBaby() {
        return baby;
    }

    public void setBaby(Baby baby) {
        this.baby = baby;
    }
}
