package edu.umsl.hester.sqlproject.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import edu.umsl.hester.sqlproject.data.Friend;

/**
 * Created by Austin on 3/17/2017.
 */

public class FriendCursorWrapper extends CursorWrapper {

    public FriendCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Friend getFriend() {
        int friendId = getInt(getColumnIndex(FriendSchema.Columns.FRIEND_ID));
        String firstName = getString(getColumnIndex(FriendSchema.Columns.FIRST_NAME));
        String lastName = getString(getColumnIndex(FriendSchema.Columns.LAST_NAME));
        String email = getString(getColumnIndex(FriendSchema.Columns.EMAIL));
        Friend friend = new Friend(friendId, firstName, lastName, email);
        return friend;
    }

}
