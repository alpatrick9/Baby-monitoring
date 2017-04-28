package com.patrick.developer.babymonitoring.model.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.patrick.developer.babymonitoring.tools.constant.Sexe;
import com.patrick.developer.babymonitoring.tools.date_manager.DateManager;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by developer on 4/26/17.
 */

@DatabaseTable(tableName = "BABY")
public class Baby implements Serializable {

    @DatabaseField(generatedId = true)
    protected Long id;

    @DatabaseField(columnName = "FIRSTNAME")
    protected String firstName;

    @DatabaseField(columnName = "LASTNAME")
    protected String lastName;

    @DatabaseField(columnName = "DATEOFBIRTH", dataType = DataType.DATE_STRING, format = "dd/MM/yyyy")
    protected Date datOfBirth;

    @DatabaseField(columnName = "SEXE")
    protected String sexe;

    public Baby() {
    }

    public Baby(String firstName, String lastName, Date datOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.datOfBirth = datOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDatOfBirth() {
        return datOfBirth;
    }

    public void setDatOfBirth(Date datOfBirth) {
        this.datOfBirth = datOfBirth;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    @Override
    public String toString() {
        String info = "";
        info += "<p>"+ this.getLastName() + " " + this.getFirstName() +"</p>";
        info += "<p>Né le "+ DateManager.dateToString(this.getDatOfBirth())+"</p>";
        info += "<p> Sexe: "+ this.getSexe() +"</p>";
        return info;
    }
}
