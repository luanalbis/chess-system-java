package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
	private Board board;

	public ChessMatch() {
		this.board = new Board();
		this.initialSetup();
	}

	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.getPieceByPosition(i, j);
			}
		}
		return mat;
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePieceOnBoard(piece, new ChessPosition(column, row).toPosition());
	}

	private void initialSetup() {
		placeNewPiece('h', 8, new Rook(board, Color.WHITE));
		board.placePieceOnBoard(new King(board, Color.WHITE), new Position(2, 2));
	}

}
