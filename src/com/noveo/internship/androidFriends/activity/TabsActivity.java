package com.noveo.internship.androidFriends.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import com.noveo.internship.androidFriends.R;


public class TabsActivity extends TabActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs_layout);
        
        TabHost tabHost = getTabHost();
        TabHost.TabSpec tabSpec;

        tabSpec = tabHost.newTabSpec(getString(R.string.map_on_tab));
        tabSpec.setIndicator("MAP");
        tabSpec.setContent(new Intent(this, GoogleMapsActivity.class));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec(getString(R.string.map_on_tab));
        tabSpec.setIndicator("FLAGS");
        tabSpec.setContent(new Intent(this, FlagsSettingActivity.class));
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec(getString(R.string.map_on_tab));
        tabSpec.setIndicator("FRIENDS");
        tabSpec.setContent(new Intent(this, FriendsAndMeetingsActivity.class));
        tabHost.addTab(tabSpec);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
