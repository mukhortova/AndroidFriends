package com.noveo.internship.androidFriends.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.noveo.internship.androidFriends.R;
import com.noveo.internship.androidFriends.model.FriendsAndMeetingAdapter;

public class FriendsAndMeetingsActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_layout);

        final ListView listView = (ListView)findViewById(R.id.friends_list);
        FriendsAndMeetingAdapter adapter = new FriendsAndMeetingAdapter(this, new String[]{"Vasya Pupkin", "Katya Katina", "Petya Petin"});

        listView.setAdapter(adapter);

    }
}