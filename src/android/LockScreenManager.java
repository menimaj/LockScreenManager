/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.superdrive.plugins;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

import com.superdrive.plugins.GlobalService.LocalBinder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class LockScreenManager extends CordovaPlugin 
{
	public static final String ACTION_START_SERVICE = "startService";
	public static final String ACTION_STOP_SERVICE = "stopService";
	public static final String ACTION_ENABLE_SCREEN_LOCK = "lockScreen";
	public static final String ACTION_DISABLE_SCREEN_LOCK = "unlockScreen";

	private CordovaWebView cordovaWebView;
	private GlobalService globalService;
	private boolean isServiceStarted = false;
	
	private ServiceConnection serviceConnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			LocalBinder binder = (LocalBinder) service;
			globalService = binder.getService(cordovaWebView);
		}
	}; 
	
	CallbackContext callbackCtx;
	
	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		cordovaWebView = webView;
		super.initialize(cordova, webView);
	}
	
	@Override
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
		
		callbackCtx = callbackContext;
		
		try {
			if (ACTION_ENABLE_SCREEN_LOCK.equals(action)) {				
				if (isServiceStarted) {
					globalService.LockScreen();
				}
		    	callbackCtx.success();
				return true;
			}
			if (ACTION_DISABLE_SCREEN_LOCK.equals(action)) {
				if (isServiceStarted) {
					globalService.UnlockScreen();
				}
				callbackContext.success();
				return true;
			}
			if (ACTION_START_SERVICE.equals(action)) {
				if (!isServiceStarted) {
					Intent intent = new Intent(cordova.getActivity(), GlobalService.class);
					cordova.getActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
					cordova.getActivity().startService(intent);
					isServiceStarted = true;
				}
				
				callbackCtx.success();
				return true;
			}
			if (ACTION_STOP_SERVICE.equals(action)) {
				if (isServiceStarted) {
					Intent intent = new Intent(cordova.getActivity(), GlobalService.class);
					cordova.getActivity().unbindService(serviceConnection);
					cordova.getActivity().stopService(intent);
					isServiceStarted = false;
				}
				
				callbackCtx.success();
				return true;
			}
			callbackContext.error("Invalid action");
			return false;
		} catch (Exception e) {
			callbackContext.error(e.getMessage());
			return false;
		}
	}
}


