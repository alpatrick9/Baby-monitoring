package com.patrick.developer.babymonitoring.model.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by developer on 4/26/17.
 */

@DatabaseTable(tableName = "BABYSIZE")
public class BabySize {

    @DatabaseField(generatedId = true)
    protected Long id;

    @DatabaseField(columnName = "SIZE")
    protected Double size;

    @DatabaseField(columnName = "MOUNTH", dataType = DataType.INTEGER, defaultValue = "0")
    protected int mounth;

    @DatabaseField(foreign = true, canBeNull = true)
    protected Baby baby;

    public BabySize() {
    }

    public BabySize(Double size, int mounth, Baby baby) {
        this.size = size;
        this.mounth = mounth;
        this.baby = baby;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
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
