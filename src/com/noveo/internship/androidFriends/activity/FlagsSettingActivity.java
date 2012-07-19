package com.noveo.internship.androidFriends.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.noveo.internship.androidFriends.R;

public class FlagsSettingActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flags_layout);
        Button buttonMap = (Button)findViewById(R.id.buttonMapOnFlags);
        buttonMap.setOnClickListener(new View.OnClickListener()  {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FlagsSettingActivity.this, GoogleMapsActivity.class);
                startActivity(intent);
            }
        });
    }
}