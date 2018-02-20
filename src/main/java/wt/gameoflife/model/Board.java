package wt.gameoflife.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import wt.gameoflife.model.Cell.CellState;

@JsonSerialize
public class Board {

	private Cell[][] cellBoard;
	private int maxBoardX;
	private int maxBoardY;

	public Board(CellState[][] cellStates) {
		cellBoard = new Cell[cellStates.length][];
		maxBoardX = cellStates.length;
		for (int x = 0; x < cellStates.length; x++) {
			cellBoard[x] = new Cell[cellStates[x].length];
			maxBoardY = cellStates[x].length;
			for (int y = 0; y < cellStates[x].length; y++) {
				cellBoard[x][y] = new Cell(cellStates[x][y]);
			}
		}
	}

	public void generateNewPopulation() {
		Cell[][] newPopulationCellBoard = new Cell[cellBoard.length][];
		for (int x = 0; x < cellBoard.length; x++) {
			newPopulationCellBoard[x] = new Cell[cellBoard[x].length];
			for (int y = 0; y < cellBoard[x].length; y++) {
				newPopulationCellBoard[x][y] = new Cell(cellBoard[x][y].generateNextCellState(calculateCellNeighbours(x, y)));
			}
		}
		cellBoard = newPopulationCellBoard;
	}

	private int calculateCellNeighbours(int x, int y) {
		int neighbourCounter = 0;
		if (isOnTheBorder(x, y)) {
			if (x - 1 >= 0 && y - 1 >= 0)
				neighbourCounter += cellBoard[x - 1][y - 1].getCurrentState() == CellState.ALIVE ? 1 : 0;
			if (x - 1 >= 0)
				neighbourCounter += cellBoard[x - 1][y].getCurrentState() == CellState.ALIVE ? 1 : 0;
			if (x - 1 >= 0 && y + 1 <= maxBoardY - 1)
				neighbourCounter += cellBoard[x - 1][y + 1].getCurrentState() == CellState.ALIVE ? 1 : 0;
			if (y - 1 >= 0)
				neighbourCounter += cellBoard[x][y - 1].getCurrentState() == CellState.ALIVE ? 1 : 0;
			if (y + 1 <= maxBoardY - 1)
				neighbourCounter += cellBoard[x][y + 1].getCurrentState() == CellState.ALIVE ? 1 : 0;
			if (x + 1 <= maxBoardX - 1 && y - 1 >= 0)
				neighbourCounter += cellBoard[x + 1][y - 1].getCurrentState() == CellState.ALIVE ? 1 : 0;
			if (x + 1 <= maxBoardX - 1)
				neighbourCounter += cellBoard[x + 1][y].getCurrentState() == CellState.ALIVE ? 1 : 0;
			if (x + 1 <= maxBoardX - 1 && y + 1 <= maxBoardY - 1)
				neighbourCounter += cellBoard[x + 1][y + 1].getCurrentState() == CellState.ALIVE ? 1 : 0;
		} else {
			neighbourCounter += cellBoard[x - 1][y - 1].getCurrentState() == CellState.ALIVE ? 1 : 0;
			neighbourCounter += cellBoard[x - 1][y].getCurrentState() == CellState.ALIVE ? 1 : 0;
			neighbourCounter += cellBoard[x - 1][y + 1].getCurrentState() == CellState.ALIVE ? 1 : 0;
			neighbourCounter += cellBoard[x][y - 1].getCurrentState() == CellState.ALIVE ? 1 : 0;
			neighbourCounter += cellBoard[x][y + 1].getCurrentState() == CellState.ALIVE ? 1 : 0;
			neighbourCounter += cellBoard[x + 1][y - 1].getCurrentState() == CellState.ALIVE ? 1 : 0;
			neighbourCounter += cellBoard[x + 1][y].getCurrentState() == CellState.ALIVE ? 1 : 0;
			neighbourCounter += cellBoard[x + 1][y + 1].getCurrentState() == CellState.ALIVE ? 1 : 0;
		}
		return neighbourCounter;
	}

	private boolean isOnTheBorder(int x, int y) {
		return (x == 0 || x == (maxBoardX - 1)) || (y == 0 || y == (maxBoardY - 1));
	}
	
	public CellState[][] convertToCellStateArray()
	{
		CellState[][] cellStateBoard = new CellState[cellBoard.length][];
		for (int x = 0; x < cellBoard.length; x++) {
			cellStateBoard[x] = new CellState[cellBoard[x].length];
			for (int y = 0; y < cellBoard[x].length; y++) {
				cellStateBoard[x][y] = cellBoard[x][y].getCurrentState();
			}
		}
		return cellStateBoard;
	}

}
