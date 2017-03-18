package edu.umsl.hester.sqlproject.data;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by Austin on 3/18/2017.
 */

public class FriendHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public FriendHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    public void bindFriend(String friendString) {

    }

    @Override
    public void onClick(View v) {
        Log.d("FRIEND_HOLDER", "Clicked: " + this + " @ pos: " + getAdapterPosition());
    }
}
