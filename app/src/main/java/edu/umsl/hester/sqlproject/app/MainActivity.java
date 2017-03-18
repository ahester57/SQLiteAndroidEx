package edu.umsl.hester.sqlproject.app;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.umsl.hester.sqlproject.R;

public class MainActivity extends AppCompatActivity implements MainViewFragment.MainViewFragmentDelegate {

    private MainViewFragment mViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        mViewFragment = (MainViewFragment) fm.findFragmentById(R.id.frag_container);

        FragmentTransaction ft = fm.beginTransaction();

        if (mViewFragment == null) {
            mViewFragment = new MainViewFragment();
            ft.add(R.id.frag_container, mViewFragment);
        }

        ft.commit();
        mViewFragment.setDelegate(this);


    }


    @Override
    public void showFriends() {

    }

    @Override
    public void fetchFriendData() {

    }
}
