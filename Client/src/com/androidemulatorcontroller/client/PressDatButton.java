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


public class PressDatButton extends BluetoothActivity {
//	OnClickListener myClickListener = new OnClickListener() {
//		Button Bup = (Button) findViewById(R.id.button1);
//		Button BA = (Button) findViewById(R.id.button2);
//		Button Bleft = (Button) findViewById(R.id.button3);
//		Button Bright = (Button) findViewById(R.id.button5);
//		Button Bdown = (Button) findViewById(R.id.button4);
//		Button BB = (Button) findViewById(R.id.button6);

		//public void onCreate(Bundle savedInstanceState) {
		//	super.onCreate(savedInstanceState);
		//	setContentView(R.layout.main);
		//}
		
		
			
		
//	};
	
	public void ButtClick(View v) {
		if (v.getId() == R.id.buttonup) writeKeyEvent(KeyEvent.KEYCODE_DPAD_UP);
		if (v.getId()==R.id.buttonleft) writeKeyEvent(KeyEvent.KEYCODE_DPAD_LEFT);
		if (v.getId()==R.id.buttondown) writeKeyEvent(KeyEvent.KEYCODE_DPAD_DOWN);
		if (v.getId()==R.id.buttonright) writeKeyEvent(KeyEvent.KEYCODE_DPAD_RIGHT);
		if (v.getId()==R.id.buttonA) writeKeyEvent(KeyEvent.KEYCODE_BUTTON_A);
		if (v.getId()==R.id.buttonB) writeKeyEvent(KeyEvent.KEYCODE_BUTTON_B);
	}
	
//	public void calculateClickHandler(View view) {
//		if (view.getId() == R.id.button1) {
//			
//		}
//	}
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);    
		// Set Click Listener
	}
}
