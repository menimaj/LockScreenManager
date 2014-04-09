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

public class LockScreenManager extends CordovaPlugin 
{
	public static final String ACTION_ENABLE_SCREEN_LOCK = "lockScreen";
	public static final String ACTION_DISABLE_SCREEN_LOCK = "unlockScreen";

	//Intent globalService;
	//Activity cordovaActivity = cordova.getActivity();
	
	@Override
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
		try {
			if (ACTION_ENABLE_SCREEN_LOCK.equals(action)) {
				//globalService = new Intent(cordovaActivity, GlobalTouchService.class);
				//cordovaActivity.startService(globalService);
				return true;
			}
			if (ACTION_DISABLE_SCREEN_LOCK.equals(action)) {
				//if (globalService != null) {
		        //	cordovaActivity.stopService(globalService);
		    	//}
				return true;
			}
			
			return false;
		} catch (Exception e) {
			return false;
		}
	}
}

