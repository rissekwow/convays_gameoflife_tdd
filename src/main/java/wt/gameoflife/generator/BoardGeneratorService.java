package wt.gameoflife.generator;

import java.util.Random;

import org.springframework.stereotype.Service;

import wt.gameoflife.model.Board;
import wt.gameoflife.model.Cell.CellState;

@Service
public class BoardGeneratorService {

	private Random randomGenerator;

	public BoardGeneratorService() {
		this.randomGenerator = new Random();
	}

	public Board generateRandomBoard(int xLength, int yLength, int livesCount) {
		if (livesCount > (xLength * yLength))
			throw new RuntimeException("Not enought space in arrays for living cells");
		CellState[][] cellStateBoard = new CellState[xLength][];
		for (int x = 0; x < xLength; x++) {
			cellStateBoard[x] = new CellState[yLength];
			for (int y = 0; y < yLength; y++) {
				cellStateBoard[x][y] = CellState.DEAD;
			}
		}
		for (int i = 0; i < livesCount; i++) {
			bornRandomCell(cellStateBoard, xLength, yLength);
		}
		return new Board(cellStateBoard);
	}

	private void bornRandomCell(CellState[][] cellStateBoard, int xLength, int yLength) {
		int randomX = randomGenerator.nextInt(xLength);
		int randomY = randomGenerator.nextInt(yLength);
		if (cellStateBoard[randomX][randomY] == CellState.DEAD)
		cellStateBoard[randomX][randomY] = CellState.ALIVE;
		else
		bornRandomCell(cellStateBoard, xLength, yLength);
	}
}
