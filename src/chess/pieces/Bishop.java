
package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {
	public Bishop(Board board, Color color) {
		super(board, color);
		super.name = 'B';
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position aux = new Position(0, 0);

		// nw
		aux.setValues(position.getRow() - 1, position.getColumn() - 1);
		while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
			mat[aux.getRow()][aux.getColumn()] = true;
			aux.setValues(aux.getRow() - 1, aux.getColumn() - 1);
		}

		if (getBoard().positionExists(aux) && isThereOponentPieces(aux)) {
			mat[aux.getRow()][aux.getColumn()] = true;
		}

		// ne
		aux.setValues(position.getRow() - 1, position.getColumn() + 1);
		while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
			mat[aux.getRow()][aux.getColumn()] = true;
			aux.setValues(aux.getRow() - 1, aux.getColumn() + 1);
		}

		if (getBoard().positionExists(aux) && isThereOponentPieces(aux)) {
			mat[aux.getRow()][aux.getColumn()] = true;
		}

		// sw
		aux.setValues(position.getRow() + 1, position.getColumn() - 1);
		while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
			mat[aux.getRow()][aux.getColumn()] = true;
			aux.setValues(aux.getRow() + 1, aux.getColumn() - 1);
		}

		if (getBoard().positionExists(aux) && isThereOponentPieces(aux)) {
			mat[aux.getRow()][aux.getColumn()] = true;
		}

		// se
		aux.setValues(position.getRow() + 1, position.getColumn() + 1);
		while (getBoard().positionExists(aux) && !getBoard().thereIsAPiece(aux)) {
			mat[aux.getRow()][aux.getColumn()] = true;
			aux.setValues(aux.getRow() + 1, aux.getColumn() + 1);
		}

		if (getBoard().positionExists(aux) && isThereOponentPieces(aux)) {
			mat[aux.getRow()][aux.getColumn()] = true;
		}

		return mat;
	}

}