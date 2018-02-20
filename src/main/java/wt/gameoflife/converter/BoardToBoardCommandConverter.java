package wt.gameoflife.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import wt.gameoflife.command.BoardCommand;
import wt.gameoflife.model.Board;
import wt.gameoflife.model.Cell.CellState;

@Component
public class BoardToBoardCommandConverter implements Converter<Board, BoardCommand> {

	@Override
	public BoardCommand convert(Board source) {
		
		if (source == null) {
			return null;
		}
		
		CellState[][] cellStates = source.convertToCellStateArray();

		int[][] boardState = new int[cellStates.length][];
		for (int x=0;x<cellStates.length; x++)
		{
			boardState[x] = new int[cellStates[x].length];
			for (int y=0; y<boardState[x].length; y++)
			{
				boardState[x][y] = cellStates[x][y] == CellState.ALIVE ? 1 : 0;
			}
		}
		
		return new BoardCommand(boardState);
	}

}
