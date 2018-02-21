'use strict';

webApp.controller('gameOfLifeController', gameOfLifeController);

gameOfLifeController.$inject = ['$scope','gameOfLifeService'];

function gameOfLifeController($scope, gameOfLifeService) {

	var canvasId = 'gameOfLife';
	var canvasContext = '2d';
	var colorDead = '#FFFFFF';
	var colorAlive = '#000000';
	var runningInterval = null;
	
	$scope.startGameOfLife = function(x,y,alive,pix,intervalDelay)
	{
	if (runningInterval != null) clearInterval(runningInterval);
	
	var canvas = document.getElementById(canvasId);
	var context = canvas.getContext(canvasContext);
	canvas.width = x*pix;
	canvas.height = y*pix;
	gameOfLifeService.postRequestForRandomGameOfLifeBoard(x,y,alive).then(function(success) {
		var initialBoardStates = success.data;
		paintGameOfLifeBoard(initialBoardStates.boardStates, context, pix);
		runningInterval = setInterval(function(){
			gameOfLifeService.postRequestForUpdateGameOfLifeBoard(initialBoardStates).then(function(updated) {
				initialBoardStates = updated.data;
				paintGameOfLifeBoard(initialBoardStates.boardStates, context,pix);
			}, function(optional1) { // optional
				console.log(optional1);
			});
		}, intervalDelay);
	}, function(optional) { 
		console.log(optional);
	});
	}
	
	function paintGameOfLifeBoard(board, context,pix) {
		for(var x=0; x<board.length; x++) {
			for (var y=0; y<board[x].length; y++) {
				context.fillStyle=board[x][y] == 0 ? colorDead : colorAlive;
				context.fillRect(x*pix,y*pix,pix,pix);
			}
		}
		context.stroke();
	}
};

