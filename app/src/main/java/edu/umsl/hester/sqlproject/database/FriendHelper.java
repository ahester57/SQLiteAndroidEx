package edu.umsl.hester.sqlproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Austin on 3/17/2017.
 */

public class FriendHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "friendDB";

    public FriendHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + FriendSchema.NAME + " ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FriendSchema.Columns.FRIEND_ID + " INTEGER, "
                + FriendSchema.Columns.FIRST_NAME + " TEXT, "
                + FriendSchema.Columns.LAST_NAME + " TEXT, "
                + FriendSchema.Columns.EMAIL + " TEXT" + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
