package wt.gameoflife.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import wt.gameoflife.generator.BoardGeneratorService;
import wt.gameoflife.model.Board;
import wt.gameoflife.model.Cell.CellState;

@Controller
public class IndexController {

	private BoardGeneratorService boardGeneratorService;
	private CellState X = CellState.ALIVE;
	private CellState O = CellState.DEAD;
	
	public IndexController(BoardGeneratorService boardGeneratorService) {
		this.boardGeneratorService = boardGeneratorService;
	}

	@RequestMapping("")
	public String getIndexPage(Model model) {
		CellState[][] initial = {
				{O, O, O, O, O},
				{O, O, O, O, O},
				{O, X, X, X, O},
				{O, O, O, O, O},
				{O, O, O, O, O},
				{O, O, O, O, O}
		};
		//model.addAttribute("board", boardGeneratorService.generateRandomBoard(4, 4, 5));
		model.addAttribute("board", new Board(initial));
		return "index";
	}
}
