package edu.umsl.hester.sqlproject.app.friend;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.umsl.hester.sqlproject.data.Friend;
import edu.umsl.hester.sqlproject.data.FriendException;
import edu.umsl.hester.sqlproject.database.FriendSQLHandler;

/**
 * Created by Austin on 3/18/2017.
 *
 */

public class FriendModel {

    private List<Friend> mFriends = new ArrayList<>();
    private List<String> mFriendNames;
    private FriendSQLHandler mSQLHandler;

    public FriendModel(Context context) {
        mSQLHandler = FriendSQLHandler.sharedInstance(context);
    }

    public FriendModel(JSONArray friendsJson, Context context) {
        mSQLHandler = FriendSQLHandler.sharedInstance(context);
        ArrayList<Friend> newFriends = new ArrayList<>();

        for (int i = 0; i < friendsJson.length(); i++) {
            try {
                JSONObject friendObj = friendsJson.getJSONObject(i);
                Friend friend = new Friend(friendObj);
                newFriends.add(friend);

            } catch (JSONException | FriendException e) {
                e.printStackTrace();
            }
        }
        mSQLHandler.addFriends(newFriends);
    }

    public FriendModel(ArrayList<Friend> newFriends, Context context) {
        mSQLHandler = FriendSQLHandler.sharedInstance(context);

        mSQLHandler.addFriends(newFriends);
    }

    public List<String> getFriendNames() {
        if (mFriendNames == null) {
            mFriendNames = mSQLHandler.getFriendNames();
        }
        return mFriendNames;
    }

    public List<Friend> getFriends() {
        return mFriends;
    }
}
