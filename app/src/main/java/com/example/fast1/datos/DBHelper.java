package com.example.fast1.datos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context) {
        super(context, "flash.db", null, 1);
        sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PRODUCTOS(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "NAME VARCHAR, " +
                "IMAGE BLOB," +
                "DESCRIPTION VARCHAR," +
                "PRICE STRING)");
        db.execSQL("CREATE TABLE SUCURSALES(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name VARCHAR," +
                    "description VARCHAR," +
                    "location VARCHAR," +
                    "image BLOB" +
                    ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS PRODUCTOS");
        db.execSQL("DROP TABLE IF EXISTS SUCURSALES");
    }

    //Funciones presonalizadas
    public void insertProductos(String name, byte[] image, String description, String price){
        String sql = "INSERT INTO PRODUCTOS VALUES(null, ?, ?, ?, ?)";
        SQLiteStatement statement= sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindBlob(2, image);
        statement.bindString(3, description);
        statement.bindString(4, price);

        statement.executeInsert();
    }

    public void insertSucursal(String name, String description, String localization, byte[] image, String table){
        String sql = "INSERT INTO "+ table +" VALUES(null, ?, ?, ?, ?)";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, description);
        statement.bindString(3, localization);
        statement.bindBlob(4, image);

        statement.executeInsert();
    }

    public Cursor getProductos(){
        Cursor cursor= sqLiteDatabase.rawQuery("SELECT * FROM PRODUCTOS", null);
        return cursor;
    }

    public Cursor getSucursales(){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SUCURSALES", null);
        return cursor;
    }

    public Cursor getProductosById(String id){
        Cursor cursor= sqLiteDatabase.rawQuery("SELECT * FROM PRODUCTOS WHERE ID="+id, null);
        return cursor;
    }

    public void deleteProductos(String id){
        String[] args = new String[]{id};
        sqLiteDatabase.delete("PRODUCTOS", "ID=?", args);
    }

    public void updateProductos(String id, String name, byte[] image, String description, String price){
        String sql = "UPDATE PRODUCTOS " +
                "SET NAME=?," +
                "IMAGE=?," +
                "DESCRIPTION=?," +
                "PRICE=?";
        SQLiteStatement statement= sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindBlob(2, image);
        statement.bindString(3, description);
        statement.bindString(4, price);

        statement.executeUpdateDelete();
    }
}
