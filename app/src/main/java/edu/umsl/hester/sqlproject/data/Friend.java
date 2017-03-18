package edu.umsl.hester.sqlproject.data;

import org.json.JSONException;
import org.json.JSONObject;

import edu.umsl.hester.sqlproject.database.FriendSchema;

/**
 * Created by Austin on 3/17/2017.
 *
 */

public class Friend {

    private final int _id;
    private final String firstName;
    private final String lastName;
    private final String email;

    public Friend(int _id, String firstName, String lastName, String email) {
        this._id = _id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Friend(JSONObject obj) throws FriendException {
        try {
            this._id = obj.getInt(FriendSchema.Columns.FRIEND_ID);
            this.firstName = obj.getString(FriendSchema.Columns.FIRST_NAME);
            this.lastName = obj.getString(FriendSchema.Columns.LAST_NAME);
            this.email = obj.getString(FriendSchema.Columns.EMAIL);
        } catch (JSONException e) {
            throw new FriendException(e);
        }
    }

    public int get_id() {
        return _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
