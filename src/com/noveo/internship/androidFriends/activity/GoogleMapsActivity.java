package com.noveo.internship.androidFriends.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.android.maps.*;
import com.noveo.internship.androidFriends.model.MapsItemizedOverlay;
import com.noveo.internship.androidFriends.R;

import java.util.List;

public class GoogleMapsActivity extends MapActivity implements LocationListener {

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

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria, false);
        locationManager.requestLocationUpdates(provider, 1000, 0, this);
        Location location = locationManager.getLastKnownLocation(provider);

        GeoPoint point = new GeoPoint(54870000,83078900);
        OverlayItem overlayitem = new OverlayItem(point, "Hello!", "I'm in Akadem!");

        itemizedOverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedOverlay);

        Button buttonFlag = (Button) findViewById(R.id.buttonFlagsOnGoogleMap);
        Log.d("buttonFlag",String.valueOf(buttonFlag==null));
        buttonFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GoogleMapsActivity.this, FlagsSettingActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onLocationChanged(Location location) {

        System.out.println("loc " + location);
        int lat = (int) (location.getLatitude()*1E6);
		int lng = (int) (location.getLongitude()*1E6);

        GeoPoint point = new GeoPoint(lat,lng);
        OverlayItem overlayitem = new OverlayItem(point, "Hello!", "I'm in Akadem!");

        itemizedOverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedOverlay);
    }

    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    public void onProviderEnabled(String s) {
    }

    public void onProviderDisabled(String s) {
    }
}
