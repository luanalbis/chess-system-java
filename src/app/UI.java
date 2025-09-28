package app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captureds) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturedPieces(captureds);
		System.out.println();
		System.out.println("Turn: " + chessMatch.getTurn());
		
		if (chessMatch.getCheckMate()) {
			System.out.println("CHECKMATE!");
			System.out.println("WINNER: " + chessMatch.getCurrentPlayer());
			return;
		}

		System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());
		if (chessMatch.getCheck()) {
			System.out.println("CHECK");
		}
	}

	private static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + "  ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j]);
			}
			System.out.println();
		}
		System.out.println("\n   a b c d e f g h");
	}

	private static void printPiece(ChessPiece piece) {
		System.out.print(piece == null ? "-" : piece.getName());
		System.out.print(" ");
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

	private static void printCapturedPieces(List<ChessPiece> captureds) {
		List<ChessPiece> whites = captureds.stream().filter(p -> p.getColor() == Color.WHITE).toList();
		List<ChessPiece> blacks = captureds.stream().filter(p -> p.getColor() == Color.BLACK).toList();

		System.out.println();
		System.out.println("Captured Pieces:");
		System.out.print("White: ");
		whites.forEach(p -> System.out.print(p.getName() + " "));

		System.out.println();

		System.out.print("Black: ");
		blacks.forEach(p -> System.out.print(p.getName() + " "));
		System.out.println();
	}

	public static void clearScreen() {
		System.out.println("\033[H\033[2J");
		System.out.flush();
	}

}
