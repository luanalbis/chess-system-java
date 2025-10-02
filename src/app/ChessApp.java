package app;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import exception.ChessException;

public class ChessApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captureds = new ArrayList<>();

		while (!chessMatch.getCheckMate()) {
			try {

				UI.clearScreen();
				UI.printMatch(chessMatch, captureds);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);

				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				if (capturedPiece != null) {
					captureds.add(capturedPiece);
				}

				if (chessMatch.getPromoted() != null) {
					System.out.println("Enter piece for promotion (B, N, R, Q)");
					String typeString = sc.nextLine();
					chessMatch.replacePromotedPiece(typeString.charAt(0));
				}

			} catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}

		}

		UI.clearScreen();
		UI.printMatch(chessMatch, captureds);

	}

}
