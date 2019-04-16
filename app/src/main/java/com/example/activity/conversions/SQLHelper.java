package com.example.activity.conversions;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Parcel;

import java.util.ArrayList;


public class SQLHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "Conversions";
    public static final String TABLE_FAV_CURRENCY = "TABLE_FAV_CURRENCY";
    public static final String TABLE_FAV_UNITS = "TABLE_FAV_UNITS";

    private static final String dropTableFav_Currency = "DROP TABLE IF EXISTS " + TABLE_FAV_CURRENCY;
    private static final String dropTableFav_Units = "DROP TABLE IF EXISTS " + TABLE_FAV_UNITS;

    public static final String measureUnit = "measureUnit";
    public static final String conversionFROM = "conversionFROM";
    public static final String conversionTO = "conversionTO";
    public static final String id_localeFrom = "id_localeFrom";
    public static final String id_localeTo = "id_localeTo";
    public static final String id_localeMeasure = "id_localeMeasure";
    public SQLHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creacion de la BD
        String createTableFav_CURRENCY = "CREATE TABLE TABLE_FAV_CURRENCY (id_favorite INTEGER primary key autoincrement, id_localeFrom INTEGER, id_localeTo INTEGER , conversionFROM TEXT, conversionTO TEXT)";
        String createTableFav_UNITS    = "CREATE TABLE TABLE_FAV_UNITS (id_favorite INTEGER primary key autoincrement, id_localeFrom INTEGER, id_localeTo INTEGER , id_localeMeasure INTEGER, conversionFROM TEXT, conversionTO TEXT, measureUnit TEXT null)";
        db.execSQL(createTableFav_CURRENCY);
        db.execSQL(createTableFav_UNITS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(dropTableFav_Currency);
        db.execSQL(dropTableFav_Units);
        onCreate(db);
    }

    public ArrayList<Favorites> onSelectFavorites(String parameter){
        String query;
        ArrayList<Favorites> favorites = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        if(parameter.contentEquals(ConversionActivity.UNIT)){
            query = "Select * from "+TABLE_FAV_UNITS;
            cursor= db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    Favorites temp = new Favorites(Parcel.obtain());
                    temp.setId(cursor.getInt(0));
                    temp.setId_localeFrom(cursor.getInt(1));
                    temp.setId_localeTo(cursor.getInt(2));
                    temp.setId_localeMeasure(cursor.getInt(3));
                    temp.setConversionFROM(cursor.getString(4));
                    temp.setConversionTO(cursor.getString(5));
                    temp.setMeasureUnit(cursor.getString(6));
                    favorites.add(temp);
                } while (cursor.moveToNext());
            }
        }else if(parameter.contentEquals(ConversionActivity.CURRENCY)){
            query = "Select * from "+TABLE_FAV_CURRENCY;
            cursor= db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    Favorites temp = new Favorites(Parcel.obtain());
                    temp.setId(cursor.getInt(0));
                    temp.setId_localeFrom(cursor.getInt(1));
                    temp.setId_localeTo(cursor.getInt(2));
                    temp.setConversionFROM(cursor.getString(3));
                    temp.setConversionTO(cursor.getString(4));
                    favorites.add(temp);
                } while (cursor.moveToNext());
            }
        }
        if(cursor!=null) cursor.close();
        db.close();
        return favorites;
    }

    public boolean onValidateDuplicate(int FROM, int TO, int MEASURE, String parameter){
        SQLiteDatabase db = getReadableDatabase();
        String query="";
        Cursor cursor=null;
        if(parameter.contentEquals(ConversionActivity.CURRENCY)) {
            query = "Select * from "+TABLE_FAV_CURRENCY+" where "+id_localeFrom+" =? and "+id_localeTo+" =?";
            cursor = db.rawQuery(query, new String[]{String.valueOf(FROM), String.valueOf(TO)});
        }else if(parameter.contentEquals(ConversionActivity.UNIT)) {
            query = "Select * from "+TABLE_FAV_UNITS+" where "+id_localeFrom+" =? and "+id_localeTo+" =? and "
                    +id_localeMeasure+" =?";
            cursor = db.rawQuery(query, new String[]{String.valueOf(FROM), String.valueOf(TO), String.valueOf(MEASURE)});
        }

        if(cursor.moveToFirst()){
            return false;
//            do{
//                Favorites temp = new Favorites(Parcel.obtain());
//                temp.setConversionFROM(cursor.getString(1));
//                temp.setConversionTO(cursor.getString(2));
//                temp.setMeasureUnit(cursor.getString(3));
//                if(temp.getConversionFROM().equals(FROM) && temp.getConversionTO().equals(TO))
//                    return false;
//            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return true;
    }

}
