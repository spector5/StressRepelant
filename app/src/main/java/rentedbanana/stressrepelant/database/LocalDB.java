package rentedbanana.stressrepelant.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Austin on 3/28/2018.
 */

public class LocalDB
{
    // ERROR LOGGING TAG
    protected static final String TAG = "LocalDB";
    // DB INFO
    protected static final String DATABASE_NAME = "StressRepellent";
    protected static final int DATABASE_VERSION = 4;
    // DB OBJECTS
    private static DatabaseHelper dBHelper = null;
    private static SQLiteDatabase db = null;
    // RETURN CODES FOR USER METHODS
    public static final int FAILURE = -1;
    public static final int SUCCESS = 0;
    public static final int USER_ALREADY_EXISTS = 1;

    @Nullable
    public static String getFirstName(String username) {
        assert(db != null);
        String query = User_T.GET_NAME;
        String[] data = {username};
        Cursor c = db.rawQuery(query, data);
        if (c == null || c.getCount() == 0) {
            return null;
        }
        c.moveToFirst();
        String firstname = c.getString(c.getColumnIndex(User_T.NAME));
        return firstname;
    }

    @Nullable
    public static User getUser(String name) {
        assert(db != null);
        String query = User_T.GET_USER;
        String[] data = {name};
        Cursor c = db.rawQuery(query, data);
        if (c == null || c.getCount() == 0) {
            Log.d("db stuff", "return null " + c.getCount());
            return null;
        }
        c.moveToFirst();
        String username = c.getString(c.getColumnIndex(User_T.USERNAME));
        String password = c.getString(c.getColumnIndex(User_T.PASSWORD));
        String firstname = c.getString(c.getColumnIndex(User_T.NAME));
        String race = c.getString(c.getColumnIndex(User_T.RACE));
        String gender = c.getString(c.getColumnIndex(User_T.GENDER));
        String age = c.getString(c.getColumnIndex(User_T.AGE));
        Log.d("db stuff..........", "reuturn user with u,p,n " + username + password + name);
        return new User(firstname, username, password, age, race, gender);
    }

    @Nullable
    public static String getPass(String name) {
        assert(db != null);
        String query = User_T.GET_PASSWORD;
        String[] data = {name};
        Cursor c = db.rawQuery(query, data);
        if (c == null || c.getCount() == 0) {
            Log.d("db stuff............", "reutnr null pass");
            return null;
        }
        c.moveToFirst();
        String password = c.getString(c.getColumnIndex(User_T.PASSWORD));
        Log.d("db stuff............", "reutnr pass " + password);
        return password;
    }

    public static boolean nameExists(String name)
    {
        assert(db != null);

        String query = User_T.GET_USER;
        String[] data = {name};
        Cursor c = db.rawQuery(query, data);
        if (c == null || c.getCount() == 0) {
            return false;
        }
        else {
            return true;
        }
    }

    public static int addUser(User user)
    {
        assert(db != null);
        if (nameExists(user.getUsername()))
            return FAILURE;
        ContentValues values = new ContentValues(5);
        values.put(User_T.USERNAME, user.getUsername());
        values.put(User_T.PASSWORD, user.getPassword());
        values.put(User_T.NAME, user.getName());
        values.put(User_T.RACE, user.getRace());
        values.put(User_T.AGE, user.getAge());
        values.put(User_T.GENDER, user.getGender());
        long results = db.insert(User_T.TABLE_NAME, null, values);
        if (results == -1)
            return FAILURE;
        else
            return SUCCESS;
    }

    public static void openDB(Context c)
    {
        if (db == null) {
            dBHelper = new DatabaseHelper(c);
            db = dBHelper.getWritableDatabase();
        }
    }

    public static void closeDB()
    {
        db = null;
        dBHelper.close();
    }

    public static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(User_T.CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading application's database from version "
                    + oldVersion + " to " + newVersion
                    + ", which will destroy all old data!");
            _db.execSQL(User_T.DELETE_TABLE);
            onCreate(_db);
        }
    }
}
