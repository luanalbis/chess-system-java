
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

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().getPieceByPosition(position);
		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
		Position aux = new Position(0, 0);

		// cima cida esquerda
		aux.setValues(position.getRow() - 2, position.getColumn() - 1);
		if (getBoard().positionExists(aux) && canMove(aux)) {
			mat[aux.getRow()][aux.getColumn()] = true;
		}

		// cima esquerda esquerda
		aux.setValues(position.getRow() - 1, position.getColumn() - 2);
		if (getBoard().positionExists(aux) && canMove(aux)) {
			mat[aux.getRow()][aux.getColumn()] = true;
		}

		// cima cima direita
		aux.setValues(position.getRow() - 2, position.getColumn() + 1);
		if (getBoard().positionExists(aux) && canMove(aux)) {
			mat[aux.getRow()][aux.getColumn()] = true;
		}

		// cima direita direita
		aux.setValues(position.getRow() - 1, position.getColumn() + 2);
		if (getBoard().positionExists(aux) && canMove(aux)) {
			mat[aux.getRow()][aux.getColumn()] = true;
		}

		// baixo baixo esquerda
		aux.setValues(position.getRow() + 2, position.getColumn() - 1);
		if (getBoard().positionExists(aux) && canMove(aux)) {
			mat[aux.getRow()][aux.getColumn()] = true;
		}

		// baixo esquerda esquerda
		aux.setValues(position.getRow() + 1, position.getColumn() - 2);
		if (getBoard().positionExists(aux) && canMove(aux)) {
			mat[aux.getRow()][aux.getColumn()] = true;
		}

		// baixo baixo direita
		aux.setValues(position.getRow() + 2, position.getColumn() + 1);
		if (getBoard().positionExists(aux) && canMove(aux)) {
			mat[aux.getRow()][aux.getColumn()] = true;
		}

		// baixo direita direita
		aux.setValues(position.getRow() + 1, position.getColumn() + 2);
		if (getBoard().positionExists(aux) && canMove(aux)) {
			mat[aux.getRow()][aux.getColumn()] = true;
		}

		return mat;
	}

}
