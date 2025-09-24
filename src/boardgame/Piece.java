package boardgame;

public abstract class Piece {
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

	public abstract boolean[][] possibleMoves();

	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}

	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {

				if (mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	
}
