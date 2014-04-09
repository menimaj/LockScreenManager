var lockScreenManager = {
		lockScreen: function(successCallback, errorCallback) {
			cordova.exec(
					successCallback,
					errorCallback,
					'LockScreenManager',
					[{}]
			);
		} 
}
module.exports = lockScreenManager;