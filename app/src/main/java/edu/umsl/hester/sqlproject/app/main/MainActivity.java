package edu.umsl.hester.sqlproject.app.main;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import edu.umsl.hester.sqlproject.R;
import edu.umsl.hester.sqlproject.app.friend.FriendListActivity;

public class MainActivity extends AppCompatActivity implements
                MainViewFragment.MainViewFragmentDelegate,
                MainControllerFragment.MainFragmentListener {

    private final String TAG = this.getClass().getSimpleName();

    private MainControllerFragment mControllerFragment;
    private static final String CONTROLLER_TAG = "CONTROLLER_FRAGMENT";
    private MainViewFragment mViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        mViewFragment = (MainViewFragment) fm.findFragmentById(R.id.frag_container);
        if (mViewFragment == null) {
            mViewFragment = new MainViewFragment();
            ft.add(R.id.frag_container, mViewFragment);
        }

        mControllerFragment = (MainControllerFragment) fm.findFragmentByTag(CONTROLLER_TAG);
        if (mControllerFragment == null) {
            mControllerFragment = new MainControllerFragment();
            ft.add(mControllerFragment, CONTROLLER_TAG);
        }

        ft.commit();
        mViewFragment.setDelegate(this);
        mControllerFragment.setListener(this);
    }


    @Override
    public void showFriends() {
        friendDataDLComplete();
    }

    @Override
    public void fetchFriendData() {
        Log.d(TAG, "fetching friends.");
        mControllerFragment.startFriendDownloadTask();
    }

    @Override
    public void friendDataDLComplete() {
        Log.d(TAG, "Download finished");
        mControllerFragment.prepareForActivityChange();
        Intent intent = new Intent(this, FriendListActivity.class);
        startActivity(intent);
        //finish
    }
}
