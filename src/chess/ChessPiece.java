package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
	private Color color;
	private int moveCount;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
		moveCount = 0;

	}

	public Color getColor() {
		return color;
	}

	public int getMoveCount() {
		return moveCount;
	}

	public void incrementMoveCount() {
		moveCount++;
	}

	public void decrementMoveCount() {
		moveCount--;
	}

	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}

	protected boolean isThereOponentPieces(Position position) {
		ChessPiece p = (ChessPiece) getBoard().getPieceByPosition(position);
		return p != null && p.getColor() != this.color;
	}

}
