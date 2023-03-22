package com.example.theswitcher.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "divisions_table")
public class HouseDivision {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "division")
    private String division;

    @ColumnInfo(name = "light")
    private boolean light;

    public HouseDivision(@NonNull String division, boolean light) {
        this.division = division;
        this.light = light;
    }

    @NonNull
    public String getDivision() {
        return division;
    }

    public void setDivision(@NonNull String division) {
        this.division = division;
    }

    public boolean isLight() {
        return light;
    }

    public void setLight(boolean light) {
        this.light = light;
    }
}
