package gst.trainingcourse.mocktest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="vehicles.db";
    private static final int DATABASE_VERSION=1;
    public SQLiteHelper(@Nullable Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE vehicles (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT," +
                "type TEXT," +
                "price DOUBLE)";
        db.execSQL(sql);
    }

    public long addVehicles(Vehicles vehicles){
        ContentValues v = new ContentValues();
        v.put("name", vehicles.getName());
        v.put("type",vehicles.getType());
        v.put("price",vehicles.getPrice());
        SQLiteDatabase sld = getWritableDatabase();
        return sld.insert("vehicles",null,v);
    }

    public List<Vehicles> getAll(){
        List<Vehicles> list = new ArrayList<>();
        SQLiteDatabase statement = getReadableDatabase();
        Cursor c = statement.query("vehicles",null,null,null,null,null,null);
        while((c!=null) && c.moveToNext()){
            int id = c.getInt(0);
            String name = c.getString(1);
            String type = c.getString(2);
            double price = c.getDouble(3);
            list.add(new Vehicles(id,name,type,price));
        }
        return list;
    }

    public int updateVehicles(Vehicles vehicles){
        ContentValues v = new ContentValues();
        v.put("name",vehicles.getName());
        v.put("type",vehicles.getType());
        v.put("price", vehicles.getPrice());
        SQLiteDatabase slq = getWritableDatabase();
        String wClause = "id=?";
        String[] wArgs = {String.valueOf(vehicles.getId())};
        return slq.update("vehicles",v,wClause,wArgs);
    }

    public int deleteVehicles(int id){
        String wClause = "id=?";
        String[] wArgs = {String.valueOf(id)};
        SQLiteDatabase sql = getWritableDatabase();
        return sql.delete("vehicles",wClause,wArgs);
    }

    public List<Vehicles> getByName(String n){
        String sql = "name like ?";
        String[] args = {"%" + n + "%"};
        SQLiteDatabase slq = getReadableDatabase();
        Cursor c = slq.query("vehicles",null, sql, args, null, null, null);
        List<Vehicles> list = new ArrayList<>();
        while((c!=null) && c.moveToNext()){
            int id = c.getInt(0);
            String name = c.getString(1);
            String type = c.getString(2);
            double price= c.getDouble(3);
            list.add(new Vehicles(id,name,type,price));
        }
        return list;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}

