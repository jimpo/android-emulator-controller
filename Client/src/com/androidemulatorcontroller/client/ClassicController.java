package com.androidemulatorcontroller.client;

//import android.app.Activity;
import java.util.Arrays;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
/**import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;*/
import android.view.KeyEvent;
import android.widget.TextView;


public class ClassicController extends BluetoothActivity implements SensorEventListener {
    public static final String name = "Classic Controller";

    private static double PY = .00000001, PZ = .000000000001;
    private static double NY = 8, NZ = 6;
    private float[] calibration = null;


 // Create a constant to convert nanoseconds to seconds.
    private SensorManager mSensorManager;
    private Sensor mOrientation;

	public void ButtPress(View v) {
		switch (v.getId()) {
        case R.id.buttonup:
			writeKeyEvent(KeyEvent.KEYCODE_DPAD_UP);
			break;
        case R.id.buttonleft:
            writeKeyEvent(KeyEvent.KEYCODE_DPAD_LEFT);
            break;
		case R.id.buttondown:
            writeKeyEvent(KeyEvent.KEYCODE_DPAD_DOWN);
            break;
        case R.id.buttonright:
            writeKeyEvent(KeyEvent.KEYCODE_DPAD_RIGHT);
            break;
		case R.id.buttonA:
            writeKeyEvent(KeyEvent.KEYCODE_BUTTON_A);
            break;
		case R.id.buttonB:
            writeKeyEvent(KeyEvent.KEYCODE_BUTTON_B);
            break;
        }
    }

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.controller_use);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mOrientation = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
	}

	  @Override
	  protected void onResume() {
	    super.onResume();
	    mSensorManager.registerListener(this, mOrientation, SensorManager.SENSOR_DELAY_NORMAL);
	  }

	  @Override
	  protected void onPause() {
	    super.onPause();
	    mSensorManager.unregisterListener(this);
	  }
	
	public void onSensorChanged(SensorEvent event) {

		if (calibration == null)
			calibration = Arrays.copyOf(event.values, 3);
		
		event.values[1] -= calibration[1];
		event.values[2] -= calibration[2];
		
          if (Math.random() < PY * Math.pow(event.values[1] - calibration[1], NY)) {
        	 writeKeyEvent(event.values[1] -calibration[1]< 0 ? KeyEvent.KEYCODE_DPAD_RIGHT : KeyEvent.KEYCODE_DPAD_LEFT);
          }
          if (Math.random() < PZ * Math.pow(event.values[2] - calibration[2], NZ)) {
        	 writeKeyEvent(event.values[2] - calibration[2] < 0 ? KeyEvent.KEYCODE_DPAD_UP : KeyEvent.KEYCODE_DPAD_DOWN);
          }
		   }
	
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

}
