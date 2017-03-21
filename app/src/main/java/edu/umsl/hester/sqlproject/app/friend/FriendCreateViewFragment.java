package edu.umsl.hester.sqlproject.app.friend;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import edu.umsl.hester.sqlproject.R;
import edu.umsl.hester.sqlproject.data.Friend;

/**
 * Created by Austin on 3/18/2017.
 *
 */

public class FriendCreateViewFragment extends Fragment {

    private String name = "";
    private String email = "";
    private TextView fName;
    private TextView fEmail;
    private Button saveButton;
    private Button saveAsButton;
    private Button discardButton;

    private WeakReference<FriendCreateViewListener> mListener;

    interface FriendCreateViewListener {
        void addFriend(Friend friend);
        int getNumFriends();
        void removeFriend(String email);
    }

    void setListener(FriendCreateViewListener mListener) {
        this.mListener = new WeakReference<>(mListener);
    }

    void setFriendInfo(String friendName, String friendEmail) {
        name = friendName;
        email = friendEmail;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.friend_create_fragment, container, false);
        fName = (TextView) view.findViewById(R.id.new_name_text);
        fEmail = (TextView) view.findViewById(R.id.new_email_text);

        if (!(name == null || email == null)) {
            fName.setText(name);
            fEmail.setText(email);
        }

        saveButton = (Button) view.findViewById(R.id.save_button);
        saveButton.setOnClickListener(saveListen);

        saveAsButton = (Button) view.findViewById(R.id.save_as_button);
        saveAsButton.setOnClickListener(saveListen);

        discardButton = (Button) view.findViewById(R.id.discard_button);
        discardButton.setOnClickListener(discardListen);


        return view;
    }


    private View.OnClickListener saveListen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String fullName = fName.getText().toString();
            int space = fullName.indexOf(' ');

            int id = mListener.get().getNumFriends() + 1;
            String firstName;
            String lastName;
            String femail;

            try {
                // check for first & last or just first
                if (space != -1) {
                    firstName = fullName.substring(0, space);
                    lastName = fullName.substring(space+1);
                } else {
                    firstName = fullName.toString();
                    lastName = "";
                }
                femail = fEmail.getText().toString();

                if (fullName.trim().isEmpty() || femail.trim().isEmpty()) {
                    throw new StringIndexOutOfBoundsException();
                }
            } catch (StringIndexOutOfBoundsException e) {
                Toast.makeText(getActivity(), "Please fill in the required fields",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            if (v.getId() != R.id.save_as_button) {
                mListener.get().removeFriend(email);
            }
            Log.d("HEY", "saved");
            Friend newFriend = new Friend(id, firstName, lastName, femail);
            mListener.get().addFriend(newFriend);
        }
    };


    private View.OnClickListener discardListen = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            new AlertDialog.Builder(getActivity())
                    .setTitle("Delete " + name + "?")
                    .setMessage("Are you sure you want to delete " + name + "?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mListener.get().removeFriend(email);
                            Log.d("HEY", "removed");
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();


        }
    };


}
