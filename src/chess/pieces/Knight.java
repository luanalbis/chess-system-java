package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

	public Knight(Board board, Color color) {
		super(board, color);
		super.name = 'N';
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		int[][] directions = possibleDirections();

		for (int[] dir : directions) {
			Position aux = new Position(position.getRow() + dir[0], position.getColumn() + dir[1]);

			if (canMove(aux) || canCapture(aux)) {
				mat[aux.getRow()][aux.getColumn()] = true;
			}
		}

		return mat;
	}

	@Override
	protected int[][] possibleDirections() {
		return new int[][] { { -2, -1 }, { -2, 1 }, { -1, -2 }, { -1, 2 }, { 1, -2 }, { 1, 2 }, { 2, -1 }, { 2, 1 } };
	}

}
