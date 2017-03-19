package edu.umsl.hester.sqlproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.umsl.hester.sqlproject.data.Friend;

/**
 * Created by Austin on 3/17/2017.
 *
 */

public class FriendSQLHandler {

    private SQLiteDatabase mDatabase;
    private Context mContext;

    private static FriendSQLHandler sPersistence;

    public static FriendSQLHandler sharedInstance(Context context) {
        if (sPersistence == null) {
            sPersistence = new FriendSQLHandler(context);
        }
        return sPersistence;
    }

    private FriendSQLHandler(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new FriendHelper(context).getWritableDatabase();
    }

    private FriendCursorWrapper queryFriends(String where, String[] args) {
        Cursor cursor = mDatabase.query(FriendSchema.NAME, null, where, args, null, null, null);
        return new FriendCursorWrapper(cursor);
    }

    public List<Friend> getFriends() {
        ArrayList<Friend> friends = new ArrayList<>();
        try (FriendCursorWrapper fCursor = queryFriends(null, null)) {
            while (fCursor.moveToNext()) {
                friends.add(fCursor.getFriend());
            }
        }
        return friends;
    }

    public List<String> getFriendNames() {
        ArrayList<String> fNames = new ArrayList<>();

        try (Cursor cursor = mDatabase.query(FriendSchema.NAME,
                new String[]{FriendSchema.Columns.FIRST_NAME, FriendSchema.Columns.LAST_NAME},
                null, null, FriendSchema.Columns.FIRST_NAME, null, "firstName asc")) {
            while (cursor.moveToNext()) {
                fNames.add(cursor.getString(0) + " " + cursor.getString(1));
            }
        }
        return fNames;
    }

    public List<String> getFriendEmails() {
        ArrayList<String> fEmails = new ArrayList<>();

        try (Cursor cursor = mDatabase.query(FriendSchema.NAME,
                new String[] {FriendSchema.Columns.FIRST_NAME, FriendSchema.Columns.EMAIL}, null, null,
                FriendSchema.Columns.EMAIL, null, "firstName asc")) {
            while (cursor.moveToNext()) {
                fEmails.add(cursor.getString(1));
            }
        }
        return fEmails;
    }

    public void addFriends(List<Friend> friends) {
        dropIfExists();
        mDatabase.beginTransaction();
        for (Friend friend : friends) {
            ContentValues fValues = getContentValues(friend);
            mDatabase.insert(FriendSchema.NAME, null, fValues);
        }
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
    }

    private static ContentValues getContentValues(Friend friend) {
        ContentValues cV = new ContentValues();
        cV.put(FriendSchema.Columns.FRIEND_ID, friend.get_id());
        cV.put(FriendSchema.Columns.FIRST_NAME, friend.getFirstName());
        cV.put(FriendSchema.Columns.LAST_NAME, friend.getLastName());
        cV.put(FriendSchema.Columns.EMAIL, friend.getEmail());
        return cV;
    }

    private void dropIfExists() {
        mDatabase.beginTransaction();
        for (Friend friend : getFriends()) {
            mDatabase.delete(FriendSchema.NAME, "friendId = ?",
                    new String[] { Integer.valueOf(friend.get_id()).toString() }
            );
        }
        mDatabase.setTransactionSuccessful();
        mDatabase.endTransaction();
    }
}
