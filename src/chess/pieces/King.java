
package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		super.name = 'K';
		this.chessMatch = chessMatch;
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

		// special move with rook
		if (getMoveCount() == 0 && !chessMatch.getCheck()) {
			Position posHook1 = new Position(position.getRow(), position.getColumn() + 3);

			if (testRookCastling(posHook1)) {
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				if (canMove(p1) && canMove(p2)) {
					mat[position.getRow()][position.getColumn() + 2] = true;
				}
			}

			Position posHook2 = new Position(position.getRow(), position.getColumn() - 4);
			if (testRookCastling(posHook2)) {
				Position p1 = new Position(position.getRow(), position.getColumn() - 1);
				Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p3 = new Position(position.getRow(), position.getColumn() - 3);
				if (canMove(p1) && canMove(p2) && canMove(p3)) {
					mat[position.getRow()][position.getColumn() - 2] = true;
				}
			}
		}

		return mat;
	}

	private boolean testRookCastling(Position position) {
		ChessPiece p = (ChessPiece) getBoard().getPieceByPosition(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;

	}

	@Override
	protected int[][] possibleDirections() {
		return new int[][] { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };
	}

}
