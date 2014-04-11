var lockScreenManager = {
		lockScreen: function(successCallback, errorCallback) {
			cordova.exec(
					successCallback,
					errorCallback,
					'LockScreenManager',
					'lockScreen',
					[{}]
			);
		},
		unlockScreen: function(successCallback, errorCallback) {			
			cordova.exec(
					successCallback,
					errorCallback,
					'LockScreenManager',
					'unlockScreen',
					[{}]
			);
		},
		startService: function(successCallback, errorCallback) {			
			cordova.exec(
					successCallback,
					errorCallback,
					'LockScreenManager',
					'startService',
					[{}]
			);
		},
		stopService: function(successCallback, errorCallback) {			
			cordova.exec(
					successCallback,
					errorCallback,
					'LockScreenManager',
					'stopService',
					[{}]
			);
		} 
}
module.exports = lockScreenManager;