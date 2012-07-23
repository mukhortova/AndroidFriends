package com.noveo.internship.androidFriends.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import com.noveo.internship.androidFriends.model.FriendsAndMeetingAdapter;

public class FriendsAndMeetingsActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the list fragment and add it as our sole content.
        if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {
            FriendsList list = new FriendsList();
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, list).commit();
        }
    }

    public static class FriendsList extends ListFragment {

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            FriendsAndMeetingAdapter adapter = new FriendsAndMeetingAdapter(getActivity().getApplicationContext(), new String[]{"walk","drink","sleep","friends","work","bar","cafe","club"});
            setListAdapter(adapter);
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            Log.i("FragmentList", "Item clicked: " + id);
        }
    }
}