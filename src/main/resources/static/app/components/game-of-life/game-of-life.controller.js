'use strict';

webApp.controller('gameOfLifeController', gameOfLifeController);

gameOfLifeController.$inject = ['gameOfLifeService'];

function gameOfLifeController(gameOfLifeService) {
	var ctrl = this;
	
	var canvas = document.getElementById('gameOfLife');
	var context = canvas.getContext('2d');
	canvas.width = 800;
	canvas.height = 600;
	console.log(context);
	gameOfLifeService.postRequestForRandomGameOfLifeBoard(80,60,2000).then(function(response) {
		var initialBoardStates = response.data;
		paintGameOfLifeBoard(initialBoardStates.boardStates, context);
		setInterval(function(){
			gameOfLifeService.postRequestForUpdateGameOfLifeBoard(initialBoardStates).then(function(updated) {
				initialBoardStates = updated.data;
				paintGameOfLifeBoard(initialBoardStates.boardStates, context);
			}, function(optional1) { // optional
				console.log('error');
			});
		}, 50);
		
		
	}, function(optional) { // optional
		console.log('error');
	});
	
	function paintGameOfLifeBoard(board, context) {
		for(var x=0; x<board.length; x++) {
			for (var y=0; y<board[x].length; y++) {
				context.fillStyle=board[x][y] == 0 ? '#FFFFFF' : '#000000';
				context.fillRect(x*10,y*10,10,10);
			}
		}
		context.stroke();
	}
};

