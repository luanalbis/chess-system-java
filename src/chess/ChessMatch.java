package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;
import exception.ChessException;

public class ChessMatch {
	private Board board;
	private int turn;
	private Color currentPlayer;

	public ChessMatch() {
		this.board = new Board();
		this.initialSetup();
		this.currentPlayer = Color.WHITE;
		this.turn = 1;

	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public int getTurn() {
		return this.turn;
	}

	public Color getCurrentPlayer() {
		return this.currentPlayer;
	}

	public void setCurrentPlayer(Color currentPlayer) {
		this.currentPlayer = currentPlayer;
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
		validateTargetPosition(sourcePosition, targetPosition);

		Piece p = board.removePieceOnBoard(sourcePosition);
		Piece captured = board.removePieceOnBoard(targetPosition);
		board.placePieceOnBoard(p, targetPosition);

		nextTurn();
		return (ChessPiece) captured;
	}

	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}

		if (!board.getPieceByPosition(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece");
		}

		if (((ChessPiece) board.getPieceByPosition(position)).getColor() != getCurrentPlayer()) {
			throw new ChessException("The chosen piece is not yours");
		}
	}

	private void validateTargetPosition(Position source, Position target) {

		if (!board.getPieceByPosition(source).possibleMove(target))
			throw new ChessException("The chosen piece cant move to target position");

	}

	private void nextTurn() {
		turn++;
		currentPlayer = currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE;
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePieceOnBoard(piece, new ChessPosition(column, row).toPosition());
	}

	private void initialSetup() {
		placeNewPiece('h', 8, new Rook(board, Color.WHITE));
		board.placePieceOnBoard(new King(board, Color.WHITE), new Position(2, 2));
	}

}
