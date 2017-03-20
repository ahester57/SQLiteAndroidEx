package edu.umsl.hester.sqlproject.app.friend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.lang.ref.WeakReference;

import edu.umsl.hester.sqlproject.app.main.ModelHolder;

/**
 * Created by Austin on 3/19/2017.
 */

public class FriendControllerFragment extends Fragment {

    private FriendModel mFriendModel;

    private WeakReference<FriendControllerListener> mListener;
    interface FriendControllerListener {
        void friendDataDLComplete();
    }

    public void setListener(FriendControllerListener mListener) {
        this.mListener = new WeakReference<>(mListener);
    }

    void saveFriendModel() {
        ModelHolder.getInstance().saveModel(ModelHolder.FRIEND_MODEL, mFriendModel);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


}
