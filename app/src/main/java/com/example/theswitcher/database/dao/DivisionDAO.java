package com.example.theswitcher.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.theswitcher.entity.HouseDivision;

import java.util.List;

@Dao
public interface DivisionDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(HouseDivision houseDivision);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<HouseDivision> houseDivision);

    @Query("SELECT light FROM divisions_table WHERE division = :division")
    boolean getLight(String division);

    @Query("UPDATE divisions_table SET light = :lightSwitch WHERE division = :division")
    void updateLight(String division, boolean lightSwitch);

    @Query("SELECT * FROM divisions_table")
    List<HouseDivision> getAllHouseDivisions();

    @Query("SELECT COUNT(division) FROM divisions_table")
    int getDivisionCount();

    @Query("DELETE FROM divisions_table")
    void deleteAll();

}
