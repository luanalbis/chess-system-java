package app;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessPiece;
import chess.ChessPosition;

public class UI {

	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j]);
			}
			System.out.println();
		}

		System.out.println("  a b c d e f g h");

	}

	public static ChessPosition readChessPosition(Scanner sc) {

		try {
			String chessPosition = sc.nextLine();
			char column = chessPosition.charAt(0);
			int row = Integer.parseInt(String.valueOf(chessPosition.charAt(1)));
			return new ChessPosition(column, row);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Error reading ChessPosition.Valid values are from a-1 to h-8");
		}

	}

	private static void printPiece(ChessPiece piece) {
		System.out.print(piece == null ? "-" : piece.getName());
		System.out.print(" ");
	}

	public static void clearScreen() {
		System.out.println("\033[H\033[2J");
		System.out.flush();
	}

}
