package com.superdrive.plugins;

import org.apache.cordova.CordovaWebView;

import com.apps.superdrive.R;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class GlobalService extends Service {

	private final IBinder mbinder = new LocalBinder();
	
	private WindowManager mWindowManager;
	private LinearLayout transparentLayout;
	private ImageButton btnUnlockScreen;
	private WindowManager.LayoutParams mParams;
	private WindowManager.LayoutParams mbtnParams;
	private CordovaWebView cordovaWebView;
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d("super drive service", "service started");
		// create linear layout
		transparentLayout = new LinearLayout(this);
		
		// set layout width 30 px and height is equal to full screen
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		transparentLayout.setLayoutParams(lp);

		// set color if you want layout visible on screen
		transparentLayout.setBackgroundColor(Color.TRANSPARENT);
		
		// create the unlock button to be on the transparent layout
		btnUnlockScreen = new ImageButton(this);
		btnUnlockScreen.setBackgroundColor(Color.RED);
		btnUnlockScreen.setImageResource(R.drawable.locker);
		btnUnlockScreen.setPadding(0, 50, 0, 50);
		btnUnlockScreen.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// unlock the screen when the overlayed button is clicked
				if (cordovaWebView != null) {
					cordovaWebView.sendJavascript("emergencyUnlockSreen()");
				}
			}
		});
		LayoutParams btnParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		btnUnlockScreen.setLayoutParams(btnParams);
		
		// fetch window manager object
		mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
		
		// set layout parameter of window manager
		mParams = new WindowManager.LayoutParams(LayoutParams.MATCH_PARENT, 
				LayoutParams.MATCH_PARENT,
							WindowManager.LayoutParams.TYPE_PHONE,
							WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
							PixelFormat.TRANSLUCENT);
		
		mParams.gravity = Gravity.LEFT | Gravity.TOP;
		mParams.flags = WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
		
		mbtnParams = new WindowManager.LayoutParams(LayoutParams.MATCH_PARENT, 
				LayoutParams.WRAP_CONTENT, 
				WindowManager.LayoutParams.TYPE_PHONE,
				WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH | 
				WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
				PixelFormat.TRANSLUCENT);
		
		mbtnParams.gravity = Gravity.BOTTOM;
		mbtnParams.y = Gravity.BOTTOM -25;
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
		public GlobalService getService(CordovaWebView webview){
			cordovaWebView = webview;
			return GlobalService.this;
		}
	}
	
	public void LockScreen() {
		mWindowManager.addView(transparentLayout, mParams);
		mWindowManager.addView(btnUnlockScreen, mbtnParams);
	}
	
	public void UnlockScreen() {
		if(mWindowManager != null) {
			if(transparentLayout != null) {
				mWindowManager.removeView(transparentLayout);
				mWindowManager.removeView(btnUnlockScreen);
			}
		}
	}
}

