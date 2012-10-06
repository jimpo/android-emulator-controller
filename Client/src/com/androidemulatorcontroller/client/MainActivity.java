package com.androidemulatorcontroller.client;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private static final int REQUEST_CONNECT_DEVICE = 1;
	private final Context self = this;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Button button = (Button)findViewById(R.id.scan);
        // Register the onClick listener with the implementation above
        button.setOnClickListener(onScanButtonClick);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // MenuInflater inflater = getMenuInflater();
        // inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    private OnClickListener onScanButtonClick = new OnClickListener() {
        public void onClick(View v) {
            Intent serverIntent = new Intent(self, DeviceListActivity.class);
            startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (data != null)
    		startActivityForResult(data, REQUEST_CONNECT_DEVICE);
    }

}
