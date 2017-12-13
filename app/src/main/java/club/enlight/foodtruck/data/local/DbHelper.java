package club.enlight.foodtruck.data.local;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;
import android.location.Location;
import android.view.Menu;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import club.enlight.foodtruck.data.model.*;
/**
 * Database helper class
 * Used to save and store data to database
 * Created by jzmai on 10/17/17.
 */

public class DbHelper extends SQLiteOpenHelper {
    private HashMap hp;

    public static final double LAT_LONG_OFFSET = 0;

    public static final String DATABASE_NAME = "MyDBName.db";

    public static final String FT_TABLE_NAME = "foodTrucks";
    public static final String FT_COLUMN_ID = "id";
    public static final String FT_COLUMN_NAME = "name";
    public static final String FT_COLUMN_SCHEDULE = "schedule";
    public static final String FT_COLUMN_MENU = "menu";
    public static final String FT_COLUMN_LAT = "latitude";
    public static final String FT_COLUMN_LONG = "longitude";

    public static final String FTM_TABLE_NAME = "foodTruckMenus";
    public static final String FTM_COLUMN_ID = "id";
    public static final String FTM_COLUMN_TRUCK_ID = "truckId";
    public static final String FTM_COLUMN_NAME = "name";
    public static final String FTM_COLUMN_CAT = "category";
    public static final String FTM_COLUMN_PRICE = "price";
    public static final String FTM_COLUMN_DESC = "Description";


    public static final String FTI_TABLE_NAME = "foodTruckImages";
    public static final String FTI_COLUMN_ID = "id";
    public static final String FTI_COLUMN_TRUCK_ID = "truckId";
    public static final String FTI_COLUMN_DATE = "date";
    public static final String FTI_COLUMN_DATA = "data";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create Tables for Trucks
        db.execSQL(
                "create table " + FT_TABLE_NAME + " (" +
                        FTI_COLUMN_ID + "INTEGER PRIMARY KEY, " +
                        FT_COLUMN_NAME + " TEXT, " +
                        FT_COLUMN_SCHEDULE + " TEXT, " +
                        FT_COLUMN_LAT + " INTEGER, " +
                        FT_COLUMN_LONG + " INTEGER)"
        );

        // Create table for Menus
        db.execSQL(
                "create table " + FTM_TABLE_NAME + " (" +
                        FTM_COLUMN_ID + "INTEGER PRIMARY KEY, " +
                        FTM_COLUMN_TRUCK_ID + " INTEGER, " +
                        FTM_COLUMN_NAME + " TEXT, " +
                        FTM_COLUMN_CAT + " TEXT, " +
                        FTM_COLUMN_PRICE + " REAL," +
                        FTM_COLUMN_DESC + " TEXT)"
        );

        // Create table for images
        db.execSQL(
                "create table " + FTI_TABLE_NAME + " (" +
                        FTI_COLUMN_ID + "INTEGER PRIMARY KEY, " +
                        FTI_COLUMN_TRUCK_ID + " INTEGER, " +
                        FTI_COLUMN_DATE + " INTEGER, " +
                        FTI_COLUMN_DATA + " BLOB)"
        );
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + FT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + FTM_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + FTI_TABLE_NAME);

        onCreate(db);
    }


    /**
     * Takes new FoodTruck object and inserts it into the database
     *
     * @param truck to insert in database
     * @return rowId of truck, -1 if error occurred
     */
    public long insertFoodTruck(FoodTruck truck) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FT_COLUMN_NAME, truck.getName());
        contentValues.put(FT_COLUMN_SCHEDULE, truck.getSchedule().toString());
        contentValues.put(FT_COLUMN_MENU, truck.getMenu().toString());
        long rowId = db.insert(FT_TABLE_NAME, null, contentValues);

        return rowId;
    }

    /**
     * Takes existing FoodTruck object and updates data in DB
     *
     * @param truck to obtain id from
     * @return true if successful
     */
    public boolean updateFoodTruck(FoodTruck truck){
        // update data in FoodTruck object from database
        double id = truck.id;
        if( id < 0 ) return false;

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FT_COLUMN_NAME, truck.getName());
        contentValues.put(FT_COLUMN_SCHEDULE, truck.getSchedule().toString());
        contentValues.put(FT_COLUMN_MENU, truck.getMenu().toString());
        db.update(FT_TABLE_NAME, contentValues, FT_COLUMN_ID+" = ? ", new String[] { Double.toString(id) } );
        return true;
    }

    /**
     * Constructs FoodTruck object from data queried from database based on id parameter
     *
     * @param id Unique ID of FoodTruck to query from database
     * @return FoodTruck with matching ID. Null if no truck exists
     */
    public FoodTruck getTruckById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery(
                "select * from "+FT_TABLE_NAME+" where "+FT_COLUMN_ID + "="+id+""
                , null
        );

        if (cursor != null){
            if (cursor.moveToFirst()){

                id = cursor.getInt(cursor.getColumnIndex(FT_COLUMN_ID)) ;

                FoodTruck result = new FoodTruck(id);

                result.setName( cursor.getString(cursor.getColumnIndex(FT_COLUMN_NAME)));

                result.setMenu( getMenu(result.id) );

                result.setSchedule( new Schedule(
                        cursor.getString(cursor.getColumnIndex(FT_COLUMN_SCHEDULE))
                ));

                return result;
            }
        }
        return null;
    }

    /**
     * Returns ArrayList of FoodTruck objects located near location in param
     *
     * @param location location to look near
     * @return ArrayList of FoodTruck objects
     */
    public ArrayList<FoodTruck> getNearbyTrucks(Location location){

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        double lat_offset = 0.05;
        double long_offset = 0.05/Math.cos(latitude);


        double ulat = latitude + lat_offset;
        double llat = latitude - lat_offset;
        double ulong = longitude + long_offset;
        double llong = longitude - long_offset;

        ArrayList<FoodTruck> trucks = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery(
                "select * from "+FT_TABLE_NAME+" where " +
                        FT_COLUMN_LAT + " > " + ulat + " AND " +
                        FT_COLUMN_LAT + " < " + llat + " AND " +
                        FT_COLUMN_LONG + " > " + ulong + " AND " +
                        FT_COLUMN_LONG + " < " + llong
                , null
        );


        if (cursor != null){
            if (cursor.moveToFirst()){

                int id = cursor.getInt(cursor.getColumnIndex(FT_COLUMN_ID)) ;

                FoodTruck result = new FoodTruck(id);

                result.setName( cursor.getString(cursor.getColumnIndex(FT_COLUMN_NAME)));

                result.setLatitude( cursor.getDouble(cursor.getColumnIndex(FT_COLUMN_LAT)));

                result.setLongitude( cursor.getDouble(cursor.getColumnIndex(FT_COLUMN_LONG)));

                result.setMenu( getMenu(result.id) );

                result.setSchedule( new Schedule(
                        cursor.getString(cursor.getColumnIndex(FT_COLUMN_SCHEDULE))
                ));

                trucks.add(result);
            }
        }
        return trucks;
    }


    /**
     * Inserts image into database
     *
     * @param doodle image to insert
     * @return rowId of Image, or -1 if an error occurred
     */
    public long insertImage(Doodle doodle){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(FTI_COLUMN_TRUCK_ID, doodle.truckId);
        contentValues.put(FTI_COLUMN_DATE, doodle.getTimestamp());
        contentValues.put(FTI_COLUMN_DATA, doodle.getRawImage());

        long rowId = db.insert(FTI_TABLE_NAME, null, contentValues);

        return rowId;
    }

    /**
     * Returns ArrayList of images that belong to a given food truck
     *
     * @param truckId truck that images belong to
     * @return ArrayList of images
     */
    public ArrayList<Doodle> getImagesByTruck(double truckId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery(
                "select * from "+FTI_TABLE_NAME+" where "+FTI_COLUMN_TRUCK_ID + "="+truckId+""
                , null
        );

        ArrayList<Doodle> doodles = new ArrayList<Doodle>();
        if (cursor != null){
            if (cursor.moveToFirst()){

                int id = cursor.getInt(cursor.getColumnIndex(FTI_COLUMN_ID)) ;

                Doodle result = new Doodle(id, truckId);

                result.setRawImage( cursor.getBlob(cursor.getColumnIndex(FTI_COLUMN_DATA)));

                doodles.add(result);
            }
        }
        return doodles;

    }


    /**
     * inserts MenuItem into table
     *
     * @param item to insert
     * @return rowid of inserted item
     */
    public long insertMenuItem(MenuItem item){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(FTM_COLUMN_TRUCK_ID, item.truckId);
        contentValues.put(FTM_COLUMN_NAME, item.getName());
        contentValues.put(FTM_COLUMN_CAT, item.getCategory());
        contentValues.put(FTM_COLUMN_DESC, item.getDescription());
        contentValues.put(FTM_COLUMN_PRICE, item.getPrice());

        long rowId = db.insert(FTI_TABLE_NAME, null, contentValues);

        return rowId;
    }

    /**
     * Updates existing menu item
     *
     * @param item item to update
     * @return true if successful
     */
    public boolean updateMenuItem(MenuItem item){

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(FTM_COLUMN_NAME, item.getName());
        contentValues.put(FTM_COLUMN_NAME, item.getName());
        contentValues.put(FTM_COLUMN_CAT, item.getCategory());
        contentValues.put(FTM_COLUMN_DESC, item.getDescription());
        contentValues.put(FTM_COLUMN_PRICE, item.getPrice());

        db.update(FTM_TABLE_NAME, contentValues, FTM_COLUMN_ID+" = ? ", new String[] { Double.toString(item.id) } );
        return true;
    }



    /**
     * Updates the menu of truck of given ID
     *
     * @param menu ArrayList of items to insert/update
     * @param truckId ID of truck the menu belongs to
     * @return true if no errors
     */
    public boolean insertMenu(ArrayList<MenuItem> menu, int truckId){
        Iterator<MenuItem> itr = menu.iterator();
        while(itr.hasNext()){
            MenuItem item = itr.next();
            if (item.id == -1) insertMenuItem(item);
            else updateMenuItem(item);
        }
        return true;
    }

    /**
     * Returns menu for truck of given ID
     *
     * @param truckId ID of truck to grab menu for
     * @return ArrayList of MenuItems
     */
    public ArrayList<MenuItem> getMenu(double truckId){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery(
                "select * from "+FTM_TABLE_NAME+" where "+FTM_COLUMN_TRUCK_ID + "="+truckId+""
                , null
        );

        ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
        if (cursor != null){
            if (cursor.moveToFirst()){

                int id = cursor.getInt(cursor.getColumnIndex(FT_COLUMN_ID)) ;

                MenuItem result = new MenuItem(id, truckId);

                result.setName( cursor.getString(cursor.getColumnIndex(FTM_COLUMN_NAME)));

                result.setCategory( cursor.getString(cursor.getColumnIndex(FTM_COLUMN_CAT)));

                result.setDescription( cursor.getString(cursor.getColumnIndex(FTM_COLUMN_DESC)));

                result.setPrice( cursor.getFloat(cursor.getColumnIndex(FTM_COLUMN_PRICE)));

                menu.add(result);
            }
        }
        return menu;
    }
}
