package wt.gameoflife.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import wt.gameoflife.model.Cell.CellState;

@RunWith(JUnitParamsRunner.class)
public class CellTest {

	@Parameters({
		"ALIVE,0,DEAD",
		"ALIVE,1,DEAD",
		"ALIVE,2,ALIVE",
		"ALIVE,3,ALIVE",
		"ALIVE,4,DEAD",
		"ALIVE,5,DEAD",
		"ALIVE,6,DEAD",
		"ALIVE,7,DEAD",
		"ALIVE,8,DEAD",
		"DEAD,0,DEAD",
		"DEAD,1,DEAD",
		"DEAD,2,DEAD",
		"DEAD,3,ALIVE",
		"DEAD,4,DEAD",
		"DEAD,5,DEAD",
		"DEAD,6,DEAD",
		"DEAD,7,DEAD",
		"DEAD,8,DEAD"
	})
	@Test
	public void shouldLifeOrShouldDieTestCases(String input, int neighbours, String output) {
		Cell cell = new Cell(Cell.CellState.valueOf(input));
		CellState generatedCellState = cell.generateNextCellState(neighbours);
		assertTrue(generatedCellState == Cell.CellState.valueOf(output));
	}

}
