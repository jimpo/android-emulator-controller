package com.androidemulatorcontroller.client;

//import android.app.Activity;
import android.os.Bundle;
import android.view.View;
/**import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;*/
import android.view.KeyEvent;


public class ClassicController extends BluetoothActivity {
    public static final String name = "Classic Controller";


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
		// Set Click Listener
	}
}
