package com.noveo.internship.androidFriends.activity.map_in_fragment;

import android.app.Activity;
import com.noveo.internship.androidFriends.activity.GoogleMapsActivity;

public class MyMapFragment extends ActivityHostFragment {

    @Override
    public Class<? extends Activity> getActivityClass() {
        return GoogleMapsActivity.class;
    }
}