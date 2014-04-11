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
		} 
}
module.exports = lockScreenManager;