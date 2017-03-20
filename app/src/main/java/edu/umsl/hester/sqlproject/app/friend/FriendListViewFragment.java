package edu.umsl.hester.sqlproject.app.friend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.lang.ref.WeakReference;
import java.util.List;

import edu.umsl.hester.sqlproject.R;

/**
 * Created by Austin on 3/18/2017.
 *
 */

public class FriendListViewFragment extends Fragment {

    private Button mAddFriendbutton;
    private RecyclerView mRecyclerView;
    private WeakReference<FriendListViewDataSource> mDataSrc;

    interface FriendListViewDataSource {
        List<String> getFriendNames();
        List<String> getFriendEmails();
        void addFriendButton();
        void editFriend(String name, String email);
        void removeFriend(String email);
    }

    public void setDataSource(FriendListViewDataSource src) {
        mDataSrc = new WeakReference<>(src);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friend_list_fragment, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.friend_list_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new FriendAdapter());

        mAddFriendbutton = (Button) view.findViewById(R.id.add_friend_button);
        mAddFriendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataSrc.get().addFriendButton();
            }
        });

        return view;
    }




    private class FriendAdapter extends RecyclerView.Adapter<FriendHolder> implements
                        FriendHolder.FriendHolderListener {

        @Override
        public FriendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.friend_model_item_layout, parent, false);
            FriendHolder fHolder = new FriendHolder(view, this);
            return fHolder;
        }

        @Override
        public void goToDetails(String fName, String fEmail) {
            mDataSrc.get().editFriend(fName, fEmail);
        }

        @Override
        public void onBindViewHolder(FriendHolder holder, int position) {
            if (mDataSrc != null) {
                try {
                    holder.bindFriend(mDataSrc.get().getFriendNames().get(position),
                            mDataSrc.get().getFriendEmails().get(position));
                } catch (IndexOutOfBoundsException e) {
                    Log.e("WHOOPS", "idk");
                }
            }
        }

        @Override
        public int getItemCount() {
            if (mDataSrc != null) {
                return mDataSrc.get().getFriendNames().size();
            }
            return 0;
        }
    }
}
