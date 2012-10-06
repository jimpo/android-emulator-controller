package com.androidemulatorcontroller.server;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyBindings {
	@SuppressWarnings("serial")
	public static final Map<Integer, Integer> bindings = new HashMap<Integer, Integer>() {{
		put(96, KeyEvent.VK_A); // KEYCODE_BUTTON_A
		put(97, KeyEvent.VK_B); // KEYCODE_BUTTON_B
		put(98, KeyEvent.VK_C); // KEYCODE_BUTTON_C
		put(99, KeyEvent.VK_D); // KEYCODE_BUTTON_X
		put(100, KeyEvent.VK_E); // KEYCODE_BUTTON_Y
		put(101, KeyEvent.VK_F); // KEYCODE_BUTTON_Z
		put(102, KeyEvent.VK_G); // KEYCODE_BUTTON_L1
		put(103, KeyEvent.VK_H); // KEYCODE_BUTTON_R1
		put(104, KeyEvent.VK_J); // KEYCODE_BUTTON_L2
		put(105, KeyEvent.VK_H); // KEYCODE_BUTTON_R2
        put(19, KeyEvent.VK_UP); // KEYCODE_DPAD_UP
        put(20, KeyEvent.VK_DOWN); // KEYCODE_DPAD_DOWN
        put(21, KeyEvent.VK_LEFT); // KEYCODE_DPAD_LEFT
        put(22, KeyEvent.VK_RIGHT); // KEYCODE_DPAD_RIGHT
	}};
}
