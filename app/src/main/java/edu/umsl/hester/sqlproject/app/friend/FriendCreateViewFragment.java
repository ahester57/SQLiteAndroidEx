package edu.umsl.hester.sqlproject.app.friend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import edu.umsl.hester.sqlproject.R;
import edu.umsl.hester.sqlproject.data.Friend;

/**
 * Created by Austin on 3/18/2017.
 */

public class FriendCreateViewFragment extends Fragment {

    private TextView fName;
    private TextView fEmail;
    private Button saveFriend;

    private WeakReference<FriendCreateViewListener> mListener;

    interface FriendCreateViewListener {
        void addFriend(Friend friend);
        int getNumFriends();
    }

    void setListener(FriendCreateViewListener mListener) {
        this.mListener = new WeakReference<FriendCreateViewListener>(mListener);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.friend_create_fragment, container, false);
        fName = (TextView) view.findViewById(R.id.new_name_text);
        fEmail = (TextView) view.findViewById(R.id.new_email_text);

        saveFriend = (Button) view.findViewById(R.id.save_button);
        saveFriend.setOnClickListener(saveListen);


        return view;
    }

    private View.OnClickListener saveListen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String fullName = fName.getText().toString();
            int space = fullName.indexOf(' ');
            String firstName = fullName.substring(0, space);
            String lastName = fullName.substring(space+1);
            String email = fEmail.getText().toString();

            int id = mListener.get().getNumFriends() + 1;
            Friend newFriend = new Friend(id, firstName, lastName, email);
            mListener.get().addFriend(newFriend);
        }
    };
}
