package rentedbanana.stressrepelant.database;

import android.provider.BaseColumns;

/**
 * Created by Austin on 3/28/2018.
 */

public class User_T implements BaseColumns
{
    private User_T() {} // Makes class non-instantiable
    public static final String TABLE_NAME = "users";
    /* Columns */
    // The BaseColumn interface provides a field named _ID
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String NAME = "name";
    public static final String RACE = "race";
    public static final String AGE = "age";
    public static final String GENDER = "gender";

    /* SQL Statements */
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + _ID + " INTEGER PRIMARY KEY, "
                    + USERNAME + " TEXT NOT NULL UNIQUE, "
                    + PASSWORD + " TEXT NOT NULL, "
                    + RACE + " TEXT NOT NULL, "
                    + AGE + " TEXT NOT NULL, "
                    + GENDER + " TEXT NOT NULL, "
                    + NAME + " TEXT NOT NULL)";

    public static final String DELETE_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final String GET_PASSWORD =
            "SELECT " + PASSWORD
                    + " FROM " + TABLE_NAME
                    + " WHERE " + USERNAME + "= ?";

    public static final String GET_USER =
            "SELECT *"
                    + " FROM " + TABLE_NAME
                    + " WHERE " + USERNAME + "= ?";

    public static final String GET_NAME =
            "SELECT " + NAME
                    + " FROM " + TABLE_NAME
                    + " WHERE " + USERNAME + "= ?";
}
