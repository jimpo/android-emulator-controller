package com.androidemulatorcontroller.client;

import android.os.Bundle;
import android.view.Menu;

import com.example.asdf.R;

public class ControllerSelector extends BluetoothActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controllerSelector_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.controllerSelector_view, menu);
        return true;
    }

}
