package edu.umsl.hester.sqlproject.app.friend;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import edu.umsl.hester.sqlproject.R;

/**
 * Created by Austin on 3/18/2017.
 *
 */

public class FriendHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView mFriendNameText;
    private TextView mFriendInfoText;

    private WeakReference<FriendHolderListener> mListener;

    interface FriendHolderListener {
        void goToDetails(String fName, String fEmail);
    }


    public FriendHolder(View itemView, FriendHolderListener listener) {
        super(itemView);
        this.mListener = new WeakReference<>(listener);
        mFriendNameText = (TextView) itemView.findViewById(R.id.friend_name_text);
        mFriendInfoText = (TextView) itemView.findViewById(R.id.friend_info_text);
        itemView.setOnClickListener(this);
    }

    public void bindFriend(String fName, String fEmail) {
        mFriendNameText.setText(fName);
        mFriendInfoText.setText(fEmail);
    }

    @Override
    public void onClick(View v) {
        Log.d("FRIEND_HOLDER", "Clicked: " + this + " @ pos: " + getAdapterPosition());
        mListener.get().goToDetails(mFriendNameText.getText().toString(),
                                        mFriendInfoText.getText().toString());
    }
}
