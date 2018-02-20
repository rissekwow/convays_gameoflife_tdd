package wt.gameoflife.generator;

import static org.junit.Assert.*;

import org.junit.Test;

import wt.gameoflife.model.Board;
import wt.gameoflife.model.Cell.CellState;

public class BoardGeneratorServiceTest {


	@Test
	public void isBoardGeneratorWorkingTest() {
		BoardGeneratorService boardGeneratorService = new BoardGeneratorService();
		int xLength = 4;
		int yLength = 4;
		int aliveCount = 5;
		Board board = boardGeneratorService.generateRandomBoard(xLength, yLength, aliveCount);
		CellState[][] cellStates = board.convertToCellStateArray();
		int counter = 0;
		for (int x=0; x<cellStates.length; x++) {
			for (int y=0; y<cellStates[x].length; y++) {
				counter += cellStates[x][y] == CellState.ALIVE ? 1 : 0;
			}
		}
		assertTrue(counter == aliveCount);
	}

}
