import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GamePlay {
  ChessBoard board;
  List<ChessPiece> pieces;
  ChessPiece selectedPiece = null;
  List<ChessSquare> possibleMoves = new ArrayList<>();
  boolean whiteTurn = true;

  public GamePlay() {
    board = new ChessBoard();
    pieces = new ArrayList<ChessPiece>();
    //White Pawns
    pieces.add(new Pawn(board.squareAtColRow(4, 6).get(), true));
    pieces.add(new Pawn(board.squareAtColRow(3, 6).get(), true));
    //Black Pawns
    pieces.add(new Pawn(board.squareAtColRow(4, 1).get(), false));
    pieces.add(new Pawn(board.squareAtColRow(3, 1).get(), false));
    //White King
    pieces.add(new King(board.squareAtColRow(4, 7).get(), true));
    //Black King
    pieces.add(new King(board.squareAtColRow(4, 0).get(), false));
    //White Queen
    pieces.add(new Queen(board.squareAtColRow(3, 7).get(), true));
    //Black Queen
    pieces.add(new Queen(board.squareAtColRow(3, 0).get(), false));
  }
  
  public void selectPieceAt(ChessSquare cell) {
    // Find the actor at the clicked cell
    for (ChessPiece a : pieces) {
      // Check if the actor is at the clicked cell
      if (a.loc == cell) {
        // Select the piece only if it matches the current turn
        if ((a instanceof Pawn && ((Pawn)a).isWhite() == whiteTurn) ||
            (a instanceof King && ((King)a).isWhite() == whiteTurn) ||
            (a instanceof Queen && ((Queen)a).isWhite() == whiteTurn)) {
            selectedPiece = a;
            possibleMoves = a.getPossibleMoves(board, whiteTurn, pieces);
            return;
        }
      }
    }
    // If no valid piece is found, clear selection
    selectedPiece = null;
    possibleMoves.clear();
  }
  // Check if a cell is occupied by any actor
  public boolean isSquareOccupied(ChessSquare cell) {
    for (ChessPiece a : pieces) {
      if (a.loc == cell) {
        return true;          
      }
    }
    return false;
  }
  public void moveSelectedPieceTo(ChessSquare square) {
    // Move the selected piece if the move is valid and the cell is unoccupied
    if (selectedPiece != null && possibleMoves.contains(square) && !isSquareOccupied(square)) {
      // Use the appropriate interface method to set the location
      if (selectedPiece instanceof ChessMove) {
        ((ChessMove)selectedPiece).setLocation(square);
      } else {
        // Fallback for other actors
        selectedPiece.loc = square;
      }
      // Clear selection and possible moves after moving
      selectedPiece = null;
      possibleMoves.clear();
      // Switch turns (white to black or black to white)
      whiteTurn = !whiteTurn;
    }
  }

  public void paint(Graphics g, Point mouseLoc) {
    board.paint(g, mouseLoc);
    // Highlight possible moves
    g.setColor(new Color(0, 255, 0, 128)); // Green with transparency
    for(ChessSquare move: possibleMoves) {
      if(move != null && !isSquareOccupied(move)) {
        // Highlight the square for possible move if not occupied
        g.fillRect(move.x, move.y, ChessSquare.size, ChessSquare.size);
      }
    }
    if (selectedPiece != null) {
      // Highlight the selected piece
      g.setColor(new Color( 255, 255, 0, 128)); // Yellow with transparency
      g.fillRect(selectedPiece.loc.x, selectedPiece.loc.y, ChessSquare.size, ChessSquare.size);
    }
    Optional<ChessSquare> underMouse = board.squareAtPoint(mouseLoc);
    // Highlight square under mouse and show coordinates
    if(underMouse.isPresent()) {
      ChessSquare hoverSquare = underMouse.get();
      for (ChessPiece a: pieces) {
        if(a.loc == hoverSquare) {
          g.setColor(new Color(255, 255, 0, 128));
          g.fillRect(hoverSquare.x, hoverSquare.y, ChessSquare.size, ChessSquare.size);
        }
      }
      g.setColor(Color.DARK_GRAY);
      g.drawString(String.valueOf(hoverSquare.col) + String.valueOf(8 - hoverSquare.row), 740, 30);
    }
    // Draw all actors
    for(ChessPiece a: pieces) {
      a.paint(g);
    }
  }
}
