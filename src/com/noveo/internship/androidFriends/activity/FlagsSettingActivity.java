package com.noveo.internship.androidFriends.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.noveo.internship.androidFriends.R;
import com.noveo.internship.androidFriends.model.FlagsSettingAdapter;

public class FlagsSettingActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flags_layout);

        final ListView listView = (ListView)findViewById(R.id.flags_list);

        FlagsSettingAdapter adapter = new FlagsSettingAdapter(this, new String[]{"walk","drink","sleep","friends","work","bar","cafe","club"});
        listView.setAdapter(adapter);

    }
}