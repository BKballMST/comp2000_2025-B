import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece implements ChessMove {
  private boolean isWhite;

  public Pawn(ChessSquare inLoc, boolean isWhite) {
    loc = inLoc;
    color = isWhite ? Color.WHITE : Color.BLACK;
    this.isWhite = isWhite;
    display = new ArrayList<Polygon>();
    Polygon body = new Polygon();
    body.addPoint(loc.x + 40, loc.y + 70);
    body.addPoint(loc.x + 60, loc.y + 70);
    body.addPoint(loc.x + 60, loc.y + 40);
    body.addPoint(loc.x + 50, loc.y + 30);
    body.addPoint(loc.x + 40, loc.y + 40);
    body.addPoint(loc.x + 40, loc.y + 70);
    display.add(body);
  }

  public boolean isWhite() {
    return isWhite;
  }

  @Override
  public void setLocation(ChessSquare newLoc) {
    loc = newLoc;
    display.clear();
    Polygon body = new Polygon();
    body.addPoint(loc.x + 40, loc.y + 70);
    body.addPoint(loc.x + 60, loc.y + 70);
    body.addPoint(loc.x + 60, loc.y + 40);
    body.addPoint(loc.x + 50, loc.y + 30);
    body.addPoint(loc.x + 40, loc.y + 40);
    body.addPoint(loc.x + 40, loc.y + 70);
    display.add(body);
  }

  @Override
  public List<ChessSquare> getPossibleMoves(ChessBoard grid, boolean whiteTurn, List<ChessPiece> allPieces) {
    List<ChessSquare> moves = new ArrayList<>();
    if (isWhite() == whiteTurn) {
      // Move one square forward if not occupied
      int nextRow = isWhite() ? loc.row - 1 : loc.row + 1;
      ChessSquare square = grid.squareAtColRow(loc.col, nextRow).orElse(null);
      if (square != null) {
        boolean occupied = false;
        for (ChessPiece piece : allPieces) {
          if (piece.loc == square) {
            occupied = true;
            break;
          }
        }
        if (!occupied) {
          moves.add(square);
        }
      }
    }
    return moves;
  }
}
