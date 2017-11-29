package club.enlight.foodtruck.data.local;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android .database.Cursor;

import java.util.HashMap;

import club.enlight.foodtruck.data.model.FoodTruck;
import club.enlight.foodtruck.data.model.Menu;
import club.enlight.foodtruck.data.model.Schedule;

/**
 * Database helper class
 * Used to save and store data to database
 * Created by jzmai on 10/17/17.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String FT_TABLE_NAME = "foodTrucks";
    public static final String FT_COLUMN_ID = "id";
    public static final String FT_COLUMN_NAME = "name";
    public static final String FT_COLUMN_SCHEDULE = "schedule";
    public static final String FT_COLUMN_MENU = "menu";
    private HashMap hp;

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
        db.execSQL(
                "create table foodTrucks " +
                        "(id integer primary key, name text, schedule text, reviews text)"
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
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS foodTrucks");
        onCreate(db);
    }




    /**
     * Takes new FoodTruck object and inserts it into the database
     *
     * @param truck to insert in database
     * @return true if successful
     */
    public boolean insertFoodTruck(FoodTruck truck) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FT_COLUMN_NAME, truck.getName());
        contentValues.put(FT_COLUMN_SCHEDULE, truck.getSchedule().toString());
        contentValues.put(FT_COLUMN_MENU, truck.getMenu().toString());
        long rowId = db.insert(FT_TABLE_NAME, null, contentValues);

        Cursor cursor = db.rawQuery("select * from " + FT_TABLE_NAME + " where ROWID =" + rowId + "", null);
        if (cursor != null && cursor.moveToFirst()) {
            truck.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(FT_COLUMN_ID))));
        }
        return true;
    }

    /**
     * Takes existing FoodTruck object and updates data in DB
     *
     * @param truck to obtain id from
     * @return true if successful
     */
    public boolean updateFoodTruck(FoodTruck truck){
        // update data in FoodTruck object from database
        int id = truck.getId();
        if( id < 0 ) return false;

        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FT_COLUMN_NAME, truck.getName());
        contentValues.put(FT_COLUMN_SCHEDULE, truck.getSchedule().toString());
        contentValues.put(FT_COLUMN_MENU, truck.getMenu().toString());
        db.update(FT_TABLE_NAME, contentValues, FT_COLUMN_ID+" = ? ", new String[] { Integer.toString(id) } );
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
        Cursor cursor =  db.rawQuery( "select * from "+FT_TABLE_NAME+" where id="+id+"", null );
        if (cursor != null){
            if (cursor.moveToFirst()){
                FoodTruck result = new FoodTruck();

                result.setId( Integer.parseInt( cursor.getString(
                        cursor.getColumnIndex(FT_COLUMN_ID)
                )));

                result.setName( cursor.getString(cursor.getColumnIndex(FT_COLUMN_NAME)));

                result.setMenu( new Menu(
                        cursor.getString(cursor.getColumnIndex(FT_COLUMN_MENU))
                ));

                result.setSchedule( new Schedule(
                        cursor.getString(cursor.getColumnIndex(FT_COLUMN_SCHEDULE))
                ));

                return result;
            }
        }
        return null;
    }





}
