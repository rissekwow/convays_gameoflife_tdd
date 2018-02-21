package wt.gameoflife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import wt.gameoflife.command.BoardCommand;
import wt.gameoflife.command.BoardParamsCommand;
import wt.gameoflife.converter.BoardCommandToBoardConverter;
import wt.gameoflife.converter.BoardToBoardCommandConverter;
import wt.gameoflife.generator.BoardGeneratorService;
import wt.gameoflife.model.Board;

@Controller
@RequestMapping("/api")
public class GameOfLifeController {

	private BoardGeneratorService boardGeneratorService;
	private BoardCommandToBoardConverter boardCommandToBoardConverter;
	private BoardToBoardCommandConverter boardToBoardCommandConverter;
	
	@Autowired
	public GameOfLifeController(BoardGeneratorService boardGeneratorService, BoardCommandToBoardConverter boardCommandToBoardConverter,
			BoardToBoardCommandConverter boardToBoardCommandConverter) {
		this.boardGeneratorService = boardGeneratorService;
		this.boardToBoardCommandConverter = boardToBoardCommandConverter;
		this.boardCommandToBoardConverter = boardCommandToBoardConverter;
	}
	
	@RequestMapping(value = "/randomBoard", method=RequestMethod.POST)
	@ResponseBody
	public BoardCommand generateRandomBoard(@RequestBody BoardParamsCommand boardParamsCommand) {
		Board board = boardGeneratorService.generateRandomBoard(boardParamsCommand.getxLength(), boardParamsCommand.getyLength(), boardParamsCommand.getAliveCount());
		return boardToBoardCommandConverter.convert(board);
	}
	
	@RequestMapping(value = "/updateBoard", method=RequestMethod.POST)
	@ResponseBody
	public BoardCommand generateRandomBoard(@RequestBody BoardCommand boardCommand) {
		Board board = boardCommandToBoardConverter.convert(boardCommand);
		board.generateNewPopulation();
		return boardToBoardCommandConverter.convert(board);
	}

	@RequestMapping("")
	public String getIndexPage(Model model) {
		return "index";
	}
	
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleEmptyNecessarilyFieldException(RuntimeException ree) {
		return new ResponseEntity<String>(ree.getMessage(), HttpStatus.NOT_ACCEPTABLE);
	}
}
