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

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().getPieceByPosition(position);
		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position aux = new Position(0, 0);

		int direction = (getColor() == Color.WHITE) ? -1 : 1;

		aux.setValues(position.getRow() + direction, position.getColumn());
		if (getBoard().positionExists(aux) && canMove(aux)) {
			mat[aux.getRow()][aux.getColumn()] = true;
		}

		return mat;
	}
}
