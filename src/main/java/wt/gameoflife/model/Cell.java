package wt.gameoflife.model;

public class Cell {

	public enum CellState {
		ALIVE, DEAD
	}

	private CellState currentState;

	public Cell(CellState currentState) {
		this.currentState = currentState;
	}

	public CellState generateNextCellState(int neighbours) {
		if (currentState == CellState.ALIVE) {
			return neighbours > 1 && neighbours < 4 ? CellState.ALIVE : CellState.DEAD;
		} else {
			return neighbours == 3 ? CellState.ALIVE : CellState.DEAD;
		}
	}

	public CellState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(CellState currentState) {
		this.currentState = currentState;
	}
	
	
}
