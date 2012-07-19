package com.noveo.internship.androidFriends.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.maps.*;
import com.noveo.internship.androidFriends.R;
import com.noveo.internship.androidFriends.model.MapsItemizedOverlay;

import java.util.List;

public class GoogleMapsActivity extends MapActivity implements LocationListener {
    public static final int DEFAULT_LATITUDE = 54870000;
    public static final int DEFAULT_LONGITUDE = 83078900;

    MapsItemizedOverlay itemizedOverlay;
    List<Overlay> mapOverlays;

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);

        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);

        mapOverlays = mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.androidmarker);
        itemizedOverlay = new MapsItemizedOverlay(drawable, this);
        GeoPoint defaultUserPosition = new GeoPoint(DEFAULT_LATITUDE,DEFAULT_LONGITUDE);
        OverlayItem userOverlayItem = new OverlayItem(defaultUserPosition, getString(R.string.hello_title), getString(R.string.hello_body));

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria, false);
        locationManager.requestLocationUpdates(provider, 1000, 0, this);

        itemizedOverlay.addOverlay(userOverlayItem);
        mapOverlays.add(itemizedOverlay);

//        added for fan
//        Location location = locationManager.getLastKnownLocation(provider);
//        Toast.makeText(this, "new location", 10);
//        onLocationChanged(location);
    }

    public void onLocationChanged(Location location) {


        int lat = (int) (location.getLatitude()*1E6);
		int lng = (int) (location.getLongitude()*1E6);

        GeoPoint point = new GeoPoint(lat,lng);
        OverlayItem userOverlayItem = new OverlayItem(point, getString(R.string.hello_title), getString(R.string.hello_body));

        itemizedOverlay.replaceOverlayItem(userOverlayItem);
        replaceOverlay(itemizedOverlay);
    }
    public void replaceOverlay(MapsItemizedOverlay overlay) {
        if (mapOverlays.size() == 0) {
            mapOverlays.add(overlay);
        } else {
            mapOverlays.set(mapOverlays.size() - 1, overlay);
        }
    }

    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    public void onProviderEnabled(String s) {
    }

    public void onProviderDisabled(String s) {
    }
}
