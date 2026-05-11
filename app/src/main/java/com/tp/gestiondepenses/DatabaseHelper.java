package com.tp.gestiondepenses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "gestion_depenses.db";
    private static final int DATABASE_VERSION = 2;

    // Table depenses
    public static final String TABLE_DEPENSES = "depenses";
    public static final String COL_DEPENSE_ID = "id";
    public static final String COL_DEPENSE_TITRE = "titre";
    public static final String COL_DEPENSE_MONTANT = "montant";
    public static final String COL_DEPENSE_CATEGORIE = "categorie";
    public static final String COL_DEPENSE_DATE = "date";

    // Table budgets
    public static final String TABLE_BUDGETS = "budgets";
    public static final String COL_BUDGET_ID = "id";
    public static final String COL_BUDGET_CATEGORIE = "categorie";
    public static final String COL_BUDGET_PLAFOND = "plafond";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Table depenses
        String CREATE_DEPENSES_TABLE = "CREATE TABLE " + TABLE_DEPENSES + "("
                + COL_DEPENSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_DEPENSE_TITRE + " TEXT, "
                + COL_DEPENSE_MONTANT + " REAL, "
                + COL_DEPENSE_CATEGORIE + " TEXT, "
                + COL_DEPENSE_DATE + " TEXT"
                + ")";
        db.execSQL(CREATE_DEPENSES_TABLE);

        // Table budgets
        String CREATE_BUDGETS_TABLE = "CREATE TABLE " + TABLE_BUDGETS + "("
                + COL_BUDGET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_BUDGET_CATEGORIE + " TEXT UNIQUE, "
                + COL_BUDGET_PLAFOND + " REAL"
                + ")";
        db.execSQL(CREATE_BUDGETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEPENSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUDGETS);
        onCreate(db);
    }

    // ==================== METHODES DEPENSES ====================

    public long addDepense(String titre, double montant, String categorie, String date) {
        ContentValues values = new ContentValues();
        values.put(COL_DEPENSE_TITRE, titre);
        values.put(COL_DEPENSE_MONTANT, montant);
        values.put(COL_DEPENSE_CATEGORIE, categorie);
        values.put(COL_DEPENSE_DATE, date);
        SQLiteDatabase db = this.getWritableDatabase();
        return db.insert(TABLE_DEPENSES, null, values);
    }

    public List<Depense> getAllDepenses() {
        List<Depense> depenses = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_DEPENSES + " ORDER BY " + COL_DEPENSE_DATE + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_DEPENSE_ID));
                String titre = cursor.getString(cursor.getColumnIndexOrThrow(COL_DEPENSE_TITRE));
                double montant = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_DEPENSE_MONTANT));
                String categorie = cursor.getString(cursor.getColumnIndexOrThrow(COL_DEPENSE_CATEGORIE));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(COL_DEPENSE_DATE));
                depenses.add(new Depense(id, titre, montant, categorie, date));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return depenses;
    }

    public double getTotalDepenses() {
        String query = "SELECT SUM(" + COL_DEPENSE_MONTANT + ") FROM " + TABLE_DEPENSES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        double total = 0;
        if (cursor.moveToFirst() && cursor.getDouble(0) > 0) {
            total = cursor.getDouble(0);
        }
        cursor.close();
        return total;
    }

    public boolean deleteDepense(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_DEPENSES, COL_DEPENSE_ID + " = ?", new String[]{String.valueOf(id)}) > 0;
    }

    // ==================== METHODES BUDGETS ====================

    public long addBudget(Budget budget) {
        ContentValues values = new ContentValues();
        values.put(COL_BUDGET_CATEGORIE, budget.getCategorie());
        values.put(COL_BUDGET_PLAFOND, budget.getPlafond());
        SQLiteDatabase db = this.getWritableDatabase();
        return db.insert(TABLE_BUDGETS, null, values);
    }

    public List<Budget> getAllBudgets() {
        List<Budget> budgets = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_BUDGETS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_BUDGET_ID));
            String categorie = cursor.getString(cursor.getColumnIndexOrThrow(COL_BUDGET_CATEGORIE));
            double plafond = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_BUDGET_PLAFOND));
            
            double depenses = getTotalDepensesByCategorie(categorie);
            
            budgets.add(new Budget(id, categorie, plafond, depenses));
        }
        cursor.close();
        return budgets;
    }

    public boolean updateBudget(Budget budget) {
        ContentValues values = new ContentValues();
        values.put(COL_BUDGET_CATEGORIE, budget.getCategorie());
        values.put(COL_BUDGET_PLAFOND, budget.getPlafond());
        SQLiteDatabase db = this.getWritableDatabase();
        return db.update(TABLE_BUDGETS, values, COL_BUDGET_ID + " = ?",
                new String[]{String.valueOf(budget.getId())}) > 0;
    }

    public boolean deleteBudget(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_BUDGETS, COL_BUDGET_ID + " = ?",
                new String[]{String.valueOf(id)}) > 0;
    }

    private double getTotalDepensesByCategorie(String categorie) {
        String query = "SELECT SUM(" + COL_DEPENSE_MONTANT + ") FROM " + TABLE_DEPENSES 
                + " WHERE " + COL_DEPENSE_CATEGORIE + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{categorie});
        double total = 0;
        if (cursor.moveToFirst() && cursor.getDouble(0) > 0) {
            total = cursor.getDouble(0);
        }
        cursor.close();
        return total;
    }
}
