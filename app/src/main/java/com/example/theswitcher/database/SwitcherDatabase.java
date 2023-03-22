package com.example.theswitcher.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.theswitcher.database.dao.DivisionDAO;
import com.example.theswitcher.entity.HouseDivision;

@Database(entities = {HouseDivision.class}, version = 1, exportSchema = false)
public abstract class SwitcherDatabase extends RoomDatabase {

    public abstract DivisionDAO divisionDAO();

    public static volatile SwitcherDatabase INSTANCE;

    public static SwitcherDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SwitcherDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    SwitcherDatabase.class, "word_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
