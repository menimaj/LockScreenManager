package com.superdrive.plugins;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

public class GlobalTouchService extends Service implements OnTouchListener{

	private WindowManager mWindowManager;
	private LinearLayout touchLayout;

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d("super drive service", "service started");
		// create linear layout
		touchLayout = new LinearLayout(this);
		
		// set layout width 30 px and height is equal to full screen
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		touchLayout.setLayoutParams(lp);

		// set color if you want layout visible on screen
		touchLayout.setBackgroundColor(Color.TRANSPARENT);
		
		// set on touch listener
		touchLayout.setOnTouchListener(this);

		// fetch window manager object
		mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
		
		// set layout parameter of window manager
		WindowManager.LayoutParams mParams = new WindowManager.LayoutParams(150, 
				150,
				WindowManager.LayoutParams.TYPE_PHONE,
				WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH | 
				WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
				PixelFormat.TRANSLUCENT);
		
		mParams.gravity = Gravity.LEFT | Gravity.TOP;
		mWindowManager.addView(touchLayout, mParams);
	}
	
	@Override
	public void onDestroy() {
		if(mWindowManager != null) {
			if(touchLayout != null) {
				mWindowManager.removeView(touchLayout);
			}
		}
		super.onDestroy();
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN) {
			Log.d("super drive service", event.getRawX() + " , " + event.getRawY());
		}
		
		return true;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}

