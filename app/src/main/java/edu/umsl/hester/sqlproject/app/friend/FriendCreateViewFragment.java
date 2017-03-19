package edu.umsl.hester.sqlproject.app.friend;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import edu.umsl.hester.sqlproject.R;

/**
 * Created by Austin on 3/18/2017.
 */

public class FriendCreateViewFragment extends Fragment {

    private TextView fName;
    private TextView fEmail;
    private Button saveFriend;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.friend_create_fragment, container, false);
        fName = (TextView) view.findViewById(R.id.new_name_text);
        fEmail = (TextView) view.findViewById(R.id.new_email_text);

        saveFriend = (Button) view.findViewById(R.id.save_button);



        return view;
    }
}
