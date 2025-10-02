package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {
	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		super.name = 'P';
		this.chessMatch = chessMatch;

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

		// specialmove en passant
		int enPassantCheckRow = getColor() == Color.WHITE ? 3 : 4;
		if (position.getRow() == enPassantCheckRow) {
			var left = new Position(position.getRow(), position.getColumn() - 1);
			var right = new Position(position.getRow(), position.getColumn() + 1);
			if (canEnPassant(left)) {
				mat[left.getRow() + step][left.getColumn()] = true;
			}

			if (canEnPassant(right)) {
				mat[right.getRow() + step][right.getColumn()] = true;
			}
		}

		return mat;
	}

	private boolean canEnPassant(Position p) {
		return canCapture(p) && getBoard().getPieceByPosition(p) == chessMatch.getEnPassantVulnerable();
	}

	@Override
	protected int[][] possibleDirections() {
		throw new UnsupportedOperationException("Pawn does not use possibleDirections()");
	}
}
