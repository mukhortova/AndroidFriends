package com.noveo.internship.androidFriends;

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

import java.util.List;

public class GoogleMaps extends MapActivity implements LocationListener {
    public static final int XCOORDINATE = 54870000;
    public static final int YCOORDINATE = 83078900;

    HelloItemizedOverlay itemizedOverlay;
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
        itemizedOverlay = new HelloItemizedOverlay(drawable, this);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria, false);
        locationManager.requestLocationUpdates(provider, 1000, 0, this);

        GeoPoint point = new GeoPoint(XCOORDINATE,YCOORDINATE);
        OverlayItem overlayitem = new OverlayItem(point, getString(R.string.helloTitle), getString(R.string.helloBody));


        itemizedOverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedOverlay);

        Button buttonFlag = (Button) findViewById(R.id.buttonFlagsOnGoogleMap);
        if (buttonFlag == null) {
            Log.d("buttonFlag","is NULL");
            return;
        }

        buttonFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GoogleMaps.this, FlagsSetting.class);
                startActivity(intent);
            }
        });
    }

    public void onLocationChanged(Location location) {

        int lat = (int) (location.getLatitude()*1E6);
		int lng = (int) (location.getLongitude()*1E6);

        GeoPoint point = new GeoPoint(lat,lng);
        OverlayItem overlayitem = new OverlayItem(point, getString(R.string.helloTitle), getString(R.string.helloBody));

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
