package com.fattouh.cars;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {
    public static final String DB_NAME="MY_DB";
    public static final int VERSION=1;
    public static final String CAR_TB_NAME="cars";
    public static final String CAR_CLN_ID="id";
    public static final String CAR_CLN_MODEL="model";
    public static final String CAR_CLN_COLOR="color";
    public static final String CAR_CLN_DBL="dbl";
    public static final String CAR_CLN_DESCRIPTION="description";
    public static final String CAR_CLN_IMAGE="image";

    public MyDatabase(Context context){
        super(context,DB_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+CAR_TB_NAME+ "("
                + CAR_CLN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CAR_CLN_MODEL + " TEXT  NOT NULL, "
                + CAR_CLN_COLOR + " TEXT  NOT NULL, "
                + CAR_CLN_DBL +   "  TEXT NOT NULL, "
                + CAR_CLN_IMAGE + " TEXT NOT NULL, "
                + CAR_CLN_DESCRIPTION + " TEXT NOT NULL)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+CAR_TB_NAME);
        onCreate(db);
    }
}
