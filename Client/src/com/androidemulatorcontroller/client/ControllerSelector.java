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
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;


public class ControllerSelector extends BluetoothActivity{
    public static String EXTRA_DEVICE_ADDRESS = "device_address";	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controllerselector_view);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, R.layout.controller_name);
        mAdapter.add(PressDatButton.class.getName());
        
        final BluetoothAdapter mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        final Context self = this;
        
        OnItemClickListener mDeviceClickListener = new OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
                // Cancel discovery because it's costly and we're about to connect
                mBtAdapter.cancelDiscovery();

                // Get the device MAC address, which is the last 17 chars in the View
                String controller = ((TextView) v).getText().toString();

                // Create the result Intent and include the MAC address
                Intent intent = null;
				try {
					intent = new Intent(self, Class.forName(controller));
				} catch (ClassNotFoundException e) {
					System.out.println("You done fucked up");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                //intent.putExtra("BLUETOOTH_DEVICE", mBluetoothDevice);

                // Set result and finish this Activity
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        };
        
        ListView controllerTypes = (ListView) findViewById(R.id.controller_type);
        controllerTypes.setAdapter(mAdapter);
        controllerTypes.setOnItemClickListener(mDeviceClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}
