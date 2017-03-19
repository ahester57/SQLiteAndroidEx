package edu.umsl.hester.sqlproject.app.friend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import edu.umsl.hester.sqlproject.R;
import edu.umsl.hester.sqlproject.app.ModelHolder;

/**
 * Created by Austin on 3/18/2017.
 *
 */

public class FriendListActivity extends AppCompatActivity
            implements FriendListViewFragment.FriendListViewDataSource {

    private final String TAG = this.getClass().getSimpleName();
    private FriendListViewFragment mViewFragment;
    private FriendModel mFriendModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            mFriendModel = (FriendModel) ModelHolder.getInstance().getModel(ModelHolder.FRIEND_MODEL);
            if (mFriendModel == null) {
                mFriendModel = new FriendModel(this);
            }
        } catch (ClassCastException e) {
            Log.e(TAG, "Not a friend model class.");
        }

        // fragment stuff
        FragmentManager fm = getSupportFragmentManager();
        mViewFragment = (FriendListViewFragment) fm.findFragmentById(R.id.frag_container);
        if (mViewFragment == null) {
            mViewFragment = new FriendListViewFragment();
            fm.beginTransaction()
                    .add(R.id.frag_container, mViewFragment, "FRIEND_LIST")
                    .commit();
        }
        mViewFragment.setDataSource(this);
    }

    @Override
    public List<String> getFriendNames() {
        return mFriendModel.getFriendNames();
    }

    @Override
    public void addFriendButton() {
        FragmentManager fm = getSupportFragmentManager();
        FriendCreateViewFragment mCreateView = new FriendCreateViewFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.addToBackStack("FRIEND_LIST");
        ft.replace(R.id.frag_container, mCreateView, "CREATE_VIEW").commit();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ModelHolder.getInstance().saveModel(ModelHolder.FRIEND_MODEL, mFriendModel);
    }
}
