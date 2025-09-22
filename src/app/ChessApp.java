package app;

import boardgame.Board;

public class ChessApp {

	public static void main(String[] args) {
		Board board = new Board(10,8);
		
		System.out.println(board.getRows());

	}

}
