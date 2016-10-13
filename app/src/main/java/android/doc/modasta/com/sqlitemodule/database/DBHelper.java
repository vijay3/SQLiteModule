package android.doc.modasta.com.sqlitemodule.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.doc.modasta.com.sqlitemodule.database.table.UserTable;
import android.util.Log;

/**
 * Created by vijay.hiremath on 18/09/16.
 */
public class DBHelper extends SQLiteOpenHelper
{
    /*************************************************
     * Increase DB_VERSION to upgrade the app database
     *************************************************/
    private static final int DB_VERSION  =  1;
    /*************************************************/

    private static final String DB_NAME =  "SqliteModule_native_db.db";

    public static String TAG = DBHelper.class.getSimpleName();

    private SQLiteDatabase db;

    public DBHelper( Context context )
    {
        super( context , context.getExternalFilesDir(null).getAbsolutePath() + "/" + DB_NAME, null, DB_VERSION);
    }

    public void initialize()
    {
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase )
    {
        Log.e(TAG, "************");
        Log.e(TAG, "onCreate");
        Log.e(TAG, "************");
        sqLiteDatabase.execSQL(new UserTable().create());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.e(TAG,"************");
        Log.e( TAG , "onUpgrade" );
        Log.e(TAG, "************");
    }

    public Cursor getAll( String table_name )
    {
        String selectQuery = "SELECT * from " + table_name + " ;" ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

    public Cursor getAllDescOrder( String table_name , String col_name )
    {
        String selectQuery = "SELECT * from " + table_name + " order by " + col_name + " DESC ;" ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

    public void insert( ContentValues cons , String table_name , int flag )
    {
        try
        {
            db.beginTransaction();
            db.insertWithOnConflict( table_name , null , cons , flag );
            db.setTransactionSuccessful();
            db.endTransaction();
        }
        catch(Exception e)
        {
            Log.e( TAG , "Problem inserting One : " + e );
        }
    }


    public void deleteAll( String table_name)
    {
        db.execSQL("delete from " + table_name );
    }

    public void deleteThis( String table_name , String col_name , int id )
    {
        db.execSQL( "delete from " + table_name + " where " + col_name + " = " + id );
    }
}
