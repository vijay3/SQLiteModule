package android.doc.modasta.com.sqlitemodule;

import android.content.Context;
import android.doc.modasta.com.sqlitemodule.database.DBHelper;
import android.util.Log;

/**
 * Created by vijay.hiremath on 18/09/16.
 */
public class Application extends android.app.Application
{
    String TAG = "Application";

    private static DBHelper db;
    private static Context mAppContext;

    @Override
    public void onCreate()
    {
        super.onCreate();

        setAppContext(getApplicationContext());

        initDB();
    }

    private void initDB()
    {
        if (db == null)
        {
            db = new DBHelper(getAppContext());
            db.initialize();
        }
    }

    public static Context getAppContext()
    {
        return mAppContext;
    }

    public void setAppContext(Context mAppContext)
    {
        this.mAppContext = mAppContext;
    }

    public static DBHelper getDatabase()
    {
        if (db == null)
        {
            db = new DBHelper(getAppContext());
            db.initialize();
        }

        return db;
    }
}
