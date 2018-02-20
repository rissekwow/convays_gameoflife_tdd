package wt.gameoflife.command;

import java.util.Arrays;

public class BoardCommand {
	
	private int[][] boardStates;

	public BoardCommand() {
	}

	public BoardCommand(int[][] boardStates) {
		this.boardStates = boardStates;
	}

	public int[][] getBoardStates() {
		return boardStates;
	}

	public void setBoardStates(int[][] boardStates) {
		this.boardStates = boardStates;
	}

	@Override
	public String toString() {
		return "BoardCommand [boardStates=" + Arrays.toString(boardStates) + "]";
	}
	
	
	
}
