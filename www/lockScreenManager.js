var lockScreenManager = {
		lockScreen: function(successCallback, errorCallback) {
			cordova.exec(
					successCallback,
					errorCallback,
					'LockScreen',
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