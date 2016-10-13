package android.doc.modasta.com.sqlitemodule.database.table;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.doc.modasta.com.sqlitemodule.Application;
import android.doc.modasta.com.sqlitemodule.database.pojo.User;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by vijay.hiremath on 18/09/16.
 */
public class UserTable
{
    public static final String TABLE_NAME = "user_table";

    public UserTable() {}

    /************************
     * TABLE COLUMNS
     ***********************/
    public final String COL_PRIMARY_KEY = "_id";
    public final String COL_TITLE       =  "title";
    public final String COL_NAME        =  "name";
    public final String COL_CREATED_AT  =  "createdat";
    /***********************/


    public String create()
    {
        String create_table_query =

                "CREATE TABLE "
                        + TABLE_NAME + " ("
                        + COL_PRIMARY_KEY + " INTEGER PRIMARY KEY, "
                        + COL_TITLE       + " VARCHAR(255) DEFAULT NULL, "
                        + COL_NAME        + " VARCHAR(255) DEFAULT NULL, "
                        + COL_CREATED_AT  + " VARCHAR(255) DEFAULT NULL); ";


        return create_table_query;
    }

    public ArrayList<User> getAll()
    {
        ArrayList<User> notificationList = new ArrayList<>();
        Cursor cursor = Application.getDatabase().getAllDescOrder( TABLE_NAME,COL_PRIMARY_KEY );
        if( cursor.moveToFirst() )
        {
            do
            {
                Integer primaryKey  = cursor.getInt(cursor.getColumnIndex(COL_PRIMARY_KEY));
                String  title       = cursor.getString(cursor.getColumnIndex(COL_TITLE));
                String  name        = cursor.getString(cursor.getColumnIndex(COL_NAME));
                String  createdAt   = cursor.getString( cursor.getColumnIndex( COL_CREATED_AT ) );

                notificationList.add( new User(primaryKey,title,name,createdAt) ) ;
            }
            while( cursor.moveToNext());
        }

        return notificationList;
    }

    public void deleteThisUser( int pk )
    {
        Application.getDatabase().deleteThis( TABLE_NAME , COL_PRIMARY_KEY , pk );
    }

    public void insert( User model )
    {
        ContentValues appCon = new ContentValues();
        appCon.put(COL_TITLE     , "" + model.getTitle() );
        appCon.put(COL_NAME   , "" + model.getName() );
        appCon.put(COL_CREATED_AT, "" + new Date().getTime() );
        Application.getDatabase().insert(appCon, UserTable.TABLE_NAME, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public void deleteAll()
    {
        Application.getDatabase().deleteAll(TABLE_NAME);
    }
}
