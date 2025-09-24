package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;
import exception.ChessException;

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

	public ChessPiece performChessMove(ChessPosition source, ChessPosition target) {
		Position sourcePosition = source.toPosition();
		Position targetPosition = target.toPosition();
		
		
		validateSourcePosition(sourcePosition);
		
		Piece p  = board.removePieceOnBoard(sourcePosition);
		Piece captured = board.removePieceOnBoard(targetPosition);
		board.placePieceOnBoard(p, targetPosition);
		
		return (ChessPiece) captured;
	}
	
	private Piece makeMove(Position sourcePosition,Position targetPosition) {
		Piece p  = board.removePieceOnBoard(sourcePosition);
		Piece captured = board.removePieceOnBoard(targetPosition);
		board.placePieceOnBoard(p, targetPosition);
		return captured;
	}

	private void validateSourcePosition(Position sourcePosition) {
		if (!board.thereIsAPiece(sourcePosition)) {
			throw new ChessException("There is no piece on source position");
		}
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePieceOnBoard(piece, new ChessPosition(column, row).toPosition());
	}

	private void initialSetup() {
		placeNewPiece('h', 8, new Rook(board, Color.WHITE));
		board.placePieceOnBoard(new King(board, Color.WHITE), new Position(2, 2));
	}

}
