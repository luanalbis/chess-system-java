package chess;

import java.util.ArrayList;
import java.util.List;

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
	private boolean check;

	private List<Piece> piecesOnBoard;
	private List<Piece> capturedPieces;

	public ChessMatch() {
		board = new Board();
		turn = 1;
		currentPlayer = Color.WHITE;
		check = false;

		piecesOnBoard = new ArrayList<>();
		capturedPieces = new ArrayList<>();
		initialSetup();
	}

	public int getTurn() {
		return this.turn;
	}

	public Color getCurrentPlayer() {
		return this.currentPlayer;
	}

	public boolean getCheck() {
		return this.check;
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

		if (captured != null) {
			piecesOnBoard.remove(captured);
			capturedPieces.add(captured);
		}

		if (isCheck(currentPlayer)) {
			undoMove(sourcePosition, targetPosition, captured);
			throw new ChessException("You can´t put yourself in check");
		}

		check = isCheck(getOpponent(currentPlayer));

		nextTurn();
		return (ChessPiece) captured;
	}

	private void undoMove(Position source, Position target, Piece captured) {
		Piece piece = board.removePieceOnBoard(target);
		board.placePieceOnBoard(piece, source);
		
		
		if (captured != null) {
			board.placePieceOnBoard(captured, target);
			capturedPieces.remove(captured);
			piecesOnBoard.add(captured);
		}
		

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

	private Color getOpponent(Color color) {
		return color == Color.WHITE ? Color.BLACK : Color.WHITE;
	}

	private ChessPiece getKing(Color color) {
		return piecesOnBoard.stream().map(p -> (ChessPiece) p).filter(p -> p instanceof King && p.getColor() == color)
				.findFirst().orElseThrow(() -> new IllegalStateException("There is no" + color + "King on the board"));
	}

	private boolean isCheck(Color color) {
		Color opponent = getOpponent(color);
		Position kingPosition = getKing(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnBoard.stream().filter(p -> ((ChessPiece) p).getColor() == opponent)
				.toList();

		for (Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePieceOnBoard(piece, new ChessPosition(column, row).toPosition());
		piecesOnBoard.add(piece);
	}

	private void initialSetup() {
		placeNewPiece('d', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 5, new King(board, Color.WHITE));
		placeNewPiece('d', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new King(board, Color.BLACK));
	}

}
