package boardgame;

import exception.BoardException;

public class Board {
	private int rows;
	private int columns;
	private Piece[][] pieces;

	public Board() {
		this.rows = 8;
		this.columns = 8;
		this.pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public Piece getPieceByPosition(int row, int column) {
		if (!positionExists(row, column))
			throw new BoardException("Position not on the board");

		return this.pieces[row][column];
	}

	public Piece getPieceByPosition(Position position) {
		if (!positionExists(position))
			throw new BoardException("Position not on the board");

		return this.pieces[position.getRow()][position.getColumn()];
	}

	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiece(position))
			throw new BoardException("There is already a piece on position");

		this.pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}

	public Piece removePiece(Position position) {
		if (!this.positionExists(position))
			throw new BoardException("Position not on the board");
		
		Piece removed = this.getPieceByPosition(position);
		this.pieces[position.getRow()][position.getColumn()] = null;
		return removed;
	}

	public boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;

	}

	public boolean positionExists(Position position) {
		return this.positionExists(position.getRow(), position.getColumn());
	}

	public boolean thereIsAPiece(Position position) {
		if (!positionExists(position))
			throw new BoardException("Position not on the board");
		return this.getPieceByPosition(position) != null;
	}

}