var lockScreenManager = {
		lockScreen: function(successCallback, errorCallback) {
			cordova.exec(
					successCallback,
					errorCallback,
					'LockScreen',
					[{}]
			);
		} 
}
module.exports = lockScreenManager;