package edu.umsl.hester.sqlproject.data;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import edu.umsl.hester.sqlproject.R;

/**
 * Created by Austin on 3/18/2017.
 *
 */

public class FriendHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView mFriendNameText;
    private TextView mFriendInfoText;

    public FriendHolder(View itemView) {
        super(itemView);
        mFriendNameText = (TextView) itemView.findViewById(R.id.friend_name_text);
        mFriendInfoText = (TextView) itemView.findViewById(R.id.friend_info_text);
        itemView.setOnClickListener(this);
    }

    public void bindFriend(String friendString) {
        mFriendNameText.setText(friendString);
    }

    @Override
    public void onClick(View v) {
        Log.d("FRIEND_HOLDER", "Clicked: " + this + " @ pos: " + getAdapterPosition());
    }
}
