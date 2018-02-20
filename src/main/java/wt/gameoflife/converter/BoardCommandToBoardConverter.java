package wt.gameoflife.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import wt.gameoflife.command.BoardCommand;
import wt.gameoflife.model.Board;
import wt.gameoflife.model.Cell.CellState;

@Component
public class BoardCommandToBoardConverter implements Converter<BoardCommand, Board> {

	@Override
	public Board convert(BoardCommand source) {
		
		if (source == null) {
			return null;
		}
		
		

		int[][] boardState = source.getBoardStates();
		
		CellState[][] cellStates = new CellState[boardState.length][];
		for (int x=0;x<boardState.length; x++)
		{
			cellStates[x] = new CellState[boardState[x].length];
			for (int y=0; y<boardState[x].length; y++)
			{
				cellStates[x][y] = boardState[x][y] == 1 ? CellState.ALIVE : CellState.DEAD;
			}
		}
		
		return new Board(cellStates);
	}

}
