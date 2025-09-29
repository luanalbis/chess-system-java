
package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece {
	public Queen(Board board, Color color) {
		super(board, color);
		super.name = 'Q';
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position aux = new Position(0, 0);

		int[][] directions = possibleDirections();

		for (int[] dir : directions) {
			aux.setValues(position.getRow() + dir[0], position.getColumn() + dir[1]);

			while (canMove(aux)) {
				mat[aux.getRow()][aux.getColumn()] = true;
				aux.setValues(aux.getRow() + dir[0], aux.getColumn() + dir[1]);
			}

			if (canCapture(aux)) {
				mat[aux.getRow()][aux.getColumn()] = true;
			}
		}

		return mat;
	}

	@Override
	protected int[][] possibleDirections() {
		return new int[][] { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };
	}

}
