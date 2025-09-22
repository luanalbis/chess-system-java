package boardgame;

public class Piece {
	protected char name;
	protected Position position;
	private Board board;

	public Piece(Board board) {
		this.position = null;
		this.board = board;
		this.name = '0';
	}

	protected Board getBoard() {
		return board;
	}

	public char getName() {
		return name;
	}

}
