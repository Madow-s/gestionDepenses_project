package com.tp.gestiondepenses.conf.database;

import android.content.Context;
import com.tp.gestiondepenses.conf.entity.*;
import com.tp.gestiondepenses.conf.dao.*;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {
        Categorie.class,
        Rubrique.class,
        Depense.class,
        Revenu.class,
        Budget.class
}, version = 1)


public abstract class AppDatabase extends RoomDatabase {

    public abstract CategorieDAO categorieDao();
    public abstract RubriqueDAO rubriqueDao();
    public abstract DepenseDAO depenseDao();
    public abstract RevenuDAO revenuDao();
    public abstract BudgetDAO budgetDao();


    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "app_database"
                    )
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}