package com.noveo.internship.androidFriends;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 7/17/12
 * Time: 5:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class FlagsSetting extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flags_layout);
        Button buttonMap = (Button)findViewById(R.id.buttonMapOnFlags);
        buttonMap.setOnClickListener(new View.OnClickListener()  {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FlagsSetting.this, GoogleMaps.class);
                startActivity(intent);
            }
        });
    }
}