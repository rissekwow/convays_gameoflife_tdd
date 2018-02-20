package wt.gameoflife.model;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import wt.gameoflife.model.Cell.CellState;

public class BoardTest {

	private CellState X = CellState.ALIVE;
	private CellState O = CellState.DEAD;
	
	
	@Test
	public void boardConverterTest() {
		CellState[][] initialCellStates = {
				{O, O, O, O, O},
				{O, O, O, O, O},
				{O, O, O, O, O},
				{O, O, O, O, O},
				{O, O, O, O, O},
				{O, O, O, O, O}
		};
		Board board = new Board(initialCellStates);
		CellState[][] convertedCellStates = board.convertToCellStateArray();
		assertTrue(Arrays.deepEquals(initialCellStates, convertedCellStates));
	}
	
	@Test
	public void blinkerGameOfLifeObjectBlockTest() {
		CellState[][] initial = {
				{O, O, O, O, O},
				{O, O, O, O, O},
				{O, X, X, X, O},
				{O, O, O, O, O},
				{O, O, O, O, O},
				{O, O, O, O, O}
		};
		Board board = new Board(initial);
		board.generateNewPopulation();
		CellState[][] convertedCellStatesAfterGeneration = board.convertToCellStateArray();
		CellState[][] expected = {
				{O, O, O, O, O},
				{O, O, X, O, O},
				{O, O, X, O, O},
				{O, O, X, O, O},
				{O, O, O, O, O},
				{O, O, O, O, O}
		};
		assertTrue(Arrays.deepEquals(expected, convertedCellStatesAfterGeneration));
	}
	
	@Test
	public void frogGameOfLifeObjectBlockTest() {
		CellState[][] initial = {
				{O, O, O, O, O, O},
				{O, O, X, X, O, O},
				{O, X, O, O, O, O},
				{O, O, O, O, X, O},
				{O, O, X, X, O, O},
				{O, O, O, O, O, O}
		};
		Board board = new Board(initial);
		board.generateNewPopulation();
		CellState[][] convertedCellStatesAfterGeneration = board.convertToCellStateArray();
		CellState[][] expected = {
				{O, O, O, O, O, O},
				{O, O, X, O, O, O},
				{O, O, X, X, O, O},
				{O, O, X, X, O, O},
				{O, O, O, X, O, O},
				{O, O, O, O, O, O}
		};
		assertTrue(Arrays.deepEquals(expected, convertedCellStatesAfterGeneration));
	}

}
