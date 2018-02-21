'use strict';

webApp.service('gameOfLifeService', gameOfLifeService);

gameOfLifeService.$inject = [ '$http' ];

function gameOfLifeService($http) {
	var ctrl = this;
	
	ctrl.postRequestForRandomGameOfLifeBoard = function(xLength, yLength, aliveCount) {
		var boardParamsCommand = new Object();
		boardParamsCommand.xLength = xLength;
		boardParamsCommand.yLength = yLength;
		boardParamsCommand.aliveCount = aliveCount;
		return $http({
			url : 'api/randomBoard',
			method : "POST",
			data : boardParamsCommand
		});
	}
	
	ctrl.postRequestForUpdateGameOfLifeBoard = function(boardCommand) {
		return $http({
			url : 'api/updateBoard',
			method : "POST",
			data : boardCommand
		});
	}
};

