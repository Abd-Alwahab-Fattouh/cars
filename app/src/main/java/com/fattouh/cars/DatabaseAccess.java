package com.fattouh.cars;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseAccess {
    private SQLiteDatabase database;
    private SQLiteOpenHelper openHelper;
    private static DatabaseAccess instance;
    private DatabaseAccess (Context context){
        this.openHelper=new MyDatabase(context);
    }
    public static DatabaseAccess getInstance(Context context){
        if (instance==null){
            instance=new DatabaseAccess(context);
        }
        return instance;
    }
    public void open(){
        this.database=this.openHelper.getWritableDatabase();
    }
    public void close(){
        if (this.database!=null){

            this.database.close();
        }
    }
    public boolean insert(Car car){
        //شبيه بال bandil
        ContentValues values=new ContentValues();
        values.put(MyDatabase.CAR_CLN_MODEL,car.getModel());
        values.put(MyDatabase.CAR_CLN_COLOR,car.getColor());
        values.put(MyDatabase.CAR_CLN_DBL,car.getDpl());
        values.put(MyDatabase.CAR_CLN_IMAGE,car.getImage());
        values.put(MyDatabase.CAR_CLN_DESCRIPTION,car.getDescription());
        long result= database.insert(MyDatabase.CAR_TB_NAME,null , values);
        //اذا تمت اضافة الجدول true
        return result != -1;
    }
    public boolean updateProduct(Car car){
        //شبيه بال bandil
        ContentValues values=new ContentValues();
        values.put(MyDatabase.CAR_CLN_MODEL,car.getModel());
        values.put(MyDatabase.CAR_CLN_COLOR,car.getColor());
        values.put(MyDatabase.CAR_CLN_DBL,car.getDpl());
        values.put(MyDatabase.CAR_CLN_IMAGE,car.getImage());
        values.put(MyDatabase.CAR_CLN_DESCRIPTION,car.getDescription());
        //للحماية من ادخال خاطة او اختراق
        String args[]={String.valueOf(car.getId())};
        long result= database.update(MyDatabase.CAR_TB_NAME,values ,MyDatabase.CAR_CLN_ID+"=?",args);
        //اتعيد عدد الصفوف التي تم التعديل عليها
        return result > 0;
    }
    public long getcount(){
        return DatabaseUtils.queryNumEntries(database,MyDatabase.CAR_TB_NAME);
    }
    public boolean delete(Car car){
        String args[]={String.valueOf(car.getId())};
        int result=database.delete(MyDatabase.CAR_TB_NAME,"id=?",args);
        return result > 0;
    }
    public ArrayList<Car> getAllCars(){
        ArrayList<Car>cars=new ArrayList<>();
        Cursor cursor=database.rawQuery("SELECT * FROM "+MyDatabase.CAR_TB_NAME,null);
        if (cursor !=null && cursor.moveToFirst()){
            do {
                int id=cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabase.CAR_CLN_ID));
                String model=cursor.getString(cursor.getColumnIndexOrThrow(MyDatabase.CAR_CLN_MODEL));
                String color=cursor.getString(cursor.getColumnIndexOrThrow(MyDatabase.CAR_CLN_COLOR));
                String image=cursor.getString(cursor.getColumnIndexOrThrow(MyDatabase.CAR_CLN_IMAGE));
                String description=cursor.getString(cursor.getColumnIndexOrThrow(MyDatabase.CAR_CLN_DESCRIPTION));
                Double dpl=cursor.getDouble(cursor.getColumnIndexOrThrow(MyDatabase.CAR_CLN_DBL));
                Car car=new Car(id,model,color,dpl,image,description);
                cars.add(car);
            }
            while (cursor.moveToNext());
        }
        return cars;
    }
    public ArrayList<Car> getCars(String modelSearch){
        ArrayList<Car>cars=new ArrayList<>();
        Cursor cursor=database.rawQuery("SELECT * FROM "+MyDatabase.CAR_TB_NAME+  " WHERE "+MyDatabase.CAR_CLN_MODEL+
                " LIKE ?",new String[]{modelSearch+"%"});
        if (cursor !=null && cursor.moveToFirst()){
            do {
                int id=cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabase.CAR_CLN_ID));
                String model=cursor.getString(cursor.getColumnIndexOrThrow(MyDatabase.CAR_CLN_MODEL));
                String color=cursor.getString(cursor.getColumnIndexOrThrow(MyDatabase.CAR_CLN_COLOR));
                String image=cursor.getString(cursor.getColumnIndexOrThrow(MyDatabase.CAR_CLN_IMAGE));
                String description=cursor.getString(cursor.getColumnIndexOrThrow(MyDatabase.CAR_CLN_DESCRIPTION));
                double dpl=cursor.getDouble(cursor.getColumnIndexOrThrow(MyDatabase.CAR_CLN_DBL));
                Car car=new Car(id,model,color,dpl,image,description);
                cars.add(car);
            }
            while (cursor.moveToNext());
        }
        return cars;
    }
    public Car getCar(int carId){
        Cursor cursor=database.rawQuery("SELECT * FROM "+MyDatabase.CAR_TB_NAME
                + " WHERE "+MyDatabase.CAR_CLN_ID+ " =?" ,new String[]{String.valueOf(carId)});
        if (cursor !=null && cursor.moveToFirst()){
                int id=cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabase.CAR_CLN_ID));
                String model=cursor.getString(cursor.getColumnIndexOrThrow(MyDatabase.CAR_CLN_MODEL));
                String color=cursor.getString(cursor.getColumnIndexOrThrow(MyDatabase.CAR_CLN_COLOR));
                String image=cursor.getString(cursor.getColumnIndexOrThrow(MyDatabase.CAR_CLN_IMAGE));
                String description=cursor.getString(cursor.getColumnIndexOrThrow(MyDatabase.CAR_CLN_DESCRIPTION));
                Double dpl=cursor.getDouble(cursor.getColumnIndexOrThrow(MyDatabase.CAR_CLN_DBL));
                Car car=new Car(id,model,color,dpl,image,description);
                cursor.close();
                return car;
        }
        return null;
    }
}
