package edu.umsl.hester.sqlproject.app.main;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.umsl.hester.sqlproject.app.ModelHolder;
import edu.umsl.hester.sqlproject.app.friend.FriendModel;

/**
 * Created by Austin on 3/17/2017.
 *
 */

public class MainControllerFragment extends Fragment {

    private static final String ENDPOINT_URL = "http://stin.tech/friends.json";
    private FriendModel mFriendModel;

    private WeakReference<MainFragmentListener> mListener;

    interface MainFragmentListener {
        void friendDataDLComplete();
    }

    public void setListener(MainFragmentListener mListener) {
        this.mListener = new WeakReference<>(mListener);
    }

    void prepareForActivityChange() {
        ModelHolder.getInstance().saveModel(ModelHolder.FRIEND_MODEL, mFriendModel);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("mCONTROLLER", "destroyed");
    }

    void startFriendDownloadTask() {
        ConnectivityManager conn = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conn.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            new DownloadFriendsTask().execute(ENDPOINT_URL);
        }
    }

    private class DownloadFriendsTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return downloadFriendsFromUrl(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("DOWNLOAD", s);
            try {
                JSONArray jsonArray = new JSONArray(s);
                mFriendModel = new FriendModel(jsonArray, getActivity());
                if (mListener != null) {
                    mListener.get().friendDataDLComplete();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private String downloadFriendsFromUrl(String reqUrl) {
        InputStream in = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int response = conn.getResponseCode();

            switch (response) {
                case 200:
                case 201:
                    in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

                    StringBuilder builder = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    reader.close();
                    conn.disconnect();
                    return builder.toString();
                default:
                    conn.disconnect();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
