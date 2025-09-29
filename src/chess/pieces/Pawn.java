package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);
		super.name = 'P';

	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position aux = new Position(0, 0);

		int step = (getColor() == Color.WHITE) ? -1 : 1;

		// move
		aux.setValues(position.getRow() + step, position.getColumn());
		if (canMove(aux)) {
			mat[aux.getRow()][aux.getColumn()] = true;

			if (getMoveCount() == 0) {
				aux.setValues(position.getRow() + (step * 2), position.getColumn());
				if (canMove(aux)) {
					mat[aux.getRow()][aux.getColumn()] = true;
				}
			}
		}

		// fight
		aux.setValues(position.getRow() + step, position.getColumn() - 1);
		if (canCapture(aux)) {
			mat[aux.getRow()][aux.getColumn()] = true;
		}

		aux.setValues(position.getRow() + step, position.getColumn() + 1);
		if (canCapture(aux)) {
			mat[aux.getRow()][aux.getColumn()] = true;
		}

		return mat;
	}

	@Override
	protected int[][] possibleDirections() {
		throw new UnsupportedOperationException("Pawn does not use possibleDirections()");
	}
}
