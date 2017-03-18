package edu.umsl.hester.sqlproject.app.friend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.List;

import edu.umsl.hester.sqlproject.R;
import edu.umsl.hester.sqlproject.data.FriendHolder;

/**
 * Created by Austin on 3/18/2017.
 *
 */

public class FriendListViewFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private WeakReference<FriendListViewDataSource> mDataSrc;

    interface FriendListViewDataSource {
        List<String> getFriendNames();
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

        return view;
    }

    private class FriendAdapter extends RecyclerView.Adapter<FriendHolder> {

        @Override
        public FriendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.friend_model_item_layout, parent, false);
            return new FriendHolder(view);
        }

        @Override
        public void onBindViewHolder(FriendHolder holder, int position) {
            if (mDataSrc != null) {
                holder.bindFriend(mDataSrc.get().getFriendNames().get(position));
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
