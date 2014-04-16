package com.superdrive.plugins;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

public class GlobalService extends Service {

	private final IBinder mbinder = new LocalBinder();
	
	private WindowManager mWindowManager;
	private LinearLayout touchLayout;
	private WindowManager.LayoutParams mParams;

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
		touchLayout.setBackgroundColor(Color.argb(191, 49 ,49 ,49));

		// fetch window manager object
		mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
		
		// set layout parameter of window manager
		mParams = new WindowManager.LayoutParams(LayoutParams.MATCH_PARENT, 
				LayoutParams.MATCH_PARENT,
							WindowManager.LayoutParams.TYPE_PHONE,
							WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH | 
							WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
							PixelFormat.TRANSLUCENT);
		
		mParams.gravity = Gravity.LEFT | Gravity.TOP;
		mParams.flags = WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return mbinder;
	}
	
	public class LocalBinder extends Binder {
		public GlobalService getService(){
			return GlobalService.this;
		}
	}
	
	public void LockScreen() {
		mWindowManager.addView(touchLayout, mParams);
	}
	
	public void UnlockScreen() {
		if(mWindowManager != null) {
			if(touchLayout != null) {
				mWindowManager.removeView(touchLayout);
			}
		}
	}
}

