package com.androidemulatorcontroller.client;

import android.os.Bundle;
import android.view.Menu;

public class ControllerSelector extends BluetoothActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controllerselector_view);        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}
