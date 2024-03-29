package com.example.bill;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String COST_MONEY = "cost_money";//金额
    public static final String COST_TITLE = "cost_title";//标题
    public static final String COST_DATE = "cost_date";//日期
    public static final String BILL_COST = "Bill_cost";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Bill_daily", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists Bill_cost(" +
                "id integer primary key," +
                "cost_title varchar," +
                "cost_date varchar," +
                "cost_money varchar)");
    }

    public void insertCost(CostBean costBean){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COST_TITLE,costBean.costTitle);
        cv.put(COST_DATE,costBean.costDate);
        cv.put(COST_MONEY,costBean.costMoney);
        database.insert(BILL_COST,null,cv);
    }

    public Cursor getAllCostData(){
        SQLiteDatabase database = getWritableDatabase();
        return database.query("BILL_COST",null,null,null,null,null,COST_DATE+" ASC");

    }
    public void deleteAllData(){
        SQLiteDatabase database = getWritableDatabase();
        database.delete("BILL_COST",null,null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
