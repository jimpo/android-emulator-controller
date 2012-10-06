package com.androidemulatorcontroller.client;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.view.View;


public class ControllerSelector extends BluetoothActivity{
    public static String EXTRA_DEVICE_ADDRESS = "device_address";	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controllersselector_view);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, R.layout.controller_name);
        final BluetoothAdapter mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        final Context self = this;
        
        OnItemClickListener mDeviceClickListener = new OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
                // Cancel discovery because it's costly and we're about to connect
                mBtAdapter.cancelDiscovery();

                // Get the device MAC address, which is the last 17 chars in the View
                String controller = ((TextView) v).getText().toString();

                // Create the result Intent and include the MAC address
                Intent intent = new Intent(self, Class.forName(controller));

                // Set result and finish this Activity
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.controllerselector_view, menu);
        return true;
    }

}
