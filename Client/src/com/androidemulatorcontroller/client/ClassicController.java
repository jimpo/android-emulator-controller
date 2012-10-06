package com.androidemulatorcontroller.client;

//import android.app.Activity;
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
    
    private static double PY = 4, PZ = 10;
    private static double NY = 4, NZ = 4;
    private float[] cal = {0, 0, 0};
    
 // Create a constant to convert nanoseconds to seconds.
    private static final float NS2S = 1.0f / 1000000000.0f;
    private static double EPSILON = 0.0000001;
    private final float[] deltaRotationVector = new float[4];
    private float timestamp;
    private float[] rotationCurrent = {0, 0, 1};
    private TextView outputX, outputY, outputZ;

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
		
		SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		Sensor mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
		mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME);
		
	    outputX = (TextView) findViewById(R.id.TextView01);
	    outputY = (TextView) findViewById(R.id.TextView02);
	    outputZ = (TextView) findViewById(R.id.TextView03);
	}
	
	public void onSensorChanged(SensorEvent event) {
		  // This timestep's delta rotation to be multiplied by the current rotation
		  // after computing it from the gyro sample data.
		  if (timestamp != 0) {
		    final float dT = (event.timestamp - timestamp) * NS2S;
		    // Axis of the rotation sample, not normalized yet.
		    float axisX = event.values[0];
		    float axisY = event.values[1];
		    float axisZ = event.values[2];

		    // Calculate the angular speed of the sample
		    float omegaMagnitude = (float) Math.sqrt(axisX*axisX + axisY*axisY + axisZ*axisZ);

		    // Normalize the rotation vector if it's big enough to get the axis
		    // (that is, EPSILON should represent your maximum allowable margin of error)
		    if (omegaMagnitude > EPSILON) {
		      axisX /= omegaMagnitude;
		      axisY /= omegaMagnitude;
		      axisZ /= omegaMagnitude;
		    }

		    // Integrate around this axis with the angular speed by the timestep
		    // in order to get a delta rotation from this sample over the timestep
		    // We will convert this axis-angle representation of the delta rotation
		    // into a quaternion before turning it into the rotation matrix.
		    float thetaOverTwo = omegaMagnitude * dT / 2.0f;
		    float sinThetaOverTwo = (float) Math.sin(thetaOverTwo);
		    float cosThetaOverTwo = (float) Math.cos(thetaOverTwo);
		    deltaRotationVector[0] = sinThetaOverTwo * axisX;
		    deltaRotationVector[1] = sinThetaOverTwo * axisY;
		    deltaRotationVector[2] = sinThetaOverTwo * axisZ;
		    deltaRotationVector[3] = cosThetaOverTwo;
		  }
		  timestamp = event.timestamp;
		  float[] deltaRotationMatrix = new float[9];
		  SensorManager.getRotationMatrixFromVector(deltaRotationMatrix, deltaRotationVector);
//		  float[] rotationNext = new float[3];
//		  rotationNext[0] = deltaRotationMatrix[0] * rotationCurrent[0] +
//				  	        deltaRotationMatrix[1] * rotationCurrent[1] +
//				  	        deltaRotationMatrix[2] * rotationCurrent[2];
//          rotationNext[1] = deltaRotationMatrix[3] * rotationCurrent[0] +
//		  	                deltaRotationMatrix[4] * rotationCurrent[1] +
//		  	                deltaRotationMatrix[5] * rotationCurrent[2];
//          rotationNext[2] = deltaRotationMatrix[6] * rotationCurrent[0] +
//	                        deltaRotationMatrix[7] * rotationCurrent[1] +
//	                        deltaRotationMatrix[8] * rotationCurrent[2];
          rotationCurrent = SensorManager.getOrientation(deltaRotationMatrix, rotationCurrent);
          
          if (Math.random() < PY * Math.pow(rotationCurrent[1] - cal[1], NY)) {
        	  writeKeyEvent(rotationCurrent[1] - cal[1] < 0 ? KeyEvent.KEYCODE_DPAD_RIGHT : KeyEvent.KEYCODE_DPAD_LEFT);
          }
          if (Math.random() < PZ * Math.pow(rotationCurrent[2] - cal[2], NZ)) {
        	  writeKeyEvent(rotationCurrent[2] - cal[2] < 0 ? KeyEvent.KEYCODE_DPAD_UP : KeyEvent.KEYCODE_DPAD_DOWN);
          }
          
          outputX.setText("x:"+Float.toString(rotationCurrent[0]));
          outputY.setText("y:"+Float.toString(rotationCurrent[1]));
          outputZ.setText("z:"+Float.toString(rotationCurrent[2]));
		   }
	
	public void calibrate(View v) {
		cal = rotationCurrent;
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

}
