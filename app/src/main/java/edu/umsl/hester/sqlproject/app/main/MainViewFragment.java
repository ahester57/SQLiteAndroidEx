package edu.umsl.hester.sqlproject.app.main;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.lang.ref.WeakReference;

import edu.umsl.hester.sqlproject.R;

/**
 * Created by Austin on 3/17/2017.
 *
 */

public class MainViewFragment extends Fragment {

    private Button mShowFriends;
    private WeakReference<MainViewFragmentDelegate> mDelegate;

    interface MainViewFragmentDelegate {
        void showFriends();
        void fetchFriendData();
    }

    public void setDelegate(MainViewFragmentDelegate delegate) {
        mDelegate = new WeakReference<>(delegate);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        mShowFriends = (Button) view.findViewById(R.id.show_friends_button);
        mShowFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDelegate != null) {
                    mDelegate.get().fetchFriendData();
                    mDelegate.get().showFriends();
                }
            }
        });
        return view;
    }
}
