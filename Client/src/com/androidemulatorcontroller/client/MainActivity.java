package com.androidemulatorcontroller.client;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	private static final int REQUEST_CONNECT_DEVICE = 1;
	private final Context self = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    public void scanDevices(View v) {
        Intent serverIntent = new Intent(self, DeviceListActivity.class);
        startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (data != null) {
    		startActivityForResult(data, REQUEST_CONNECT_DEVICE);
        }
    }

}
