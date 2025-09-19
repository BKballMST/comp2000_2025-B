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
    Polygon base = new Polygon();
    base.addPoint(loc.x + 10, loc.y + 70);
    base.addPoint(loc.x + 80, loc.y + 70);
    base.addPoint(loc.x + 80, loc.y + 60);
    base.addPoint(loc.x + 10, loc.y + 60);
    Polygon body = new Polygon();
    body.addPoint(loc.x + 30, loc.y + 60);
    body.addPoint(loc.x + 60, loc.y + 60);
    body.addPoint(loc.x + 70, loc.y + 30);
    body.addPoint(loc.x + 20, loc.y + 30);
    body.addPoint(loc.x + 30, loc.y + 60);
    Polygon head = new Polygon();
    head.addPoint(loc.x + 35, loc.y + 30);
    head.addPoint(loc.x + 55, loc.y + 30);
    head.addPoint(loc.x + 55, loc.y + 10);
    head.addPoint(loc.x + 35, loc.y + 10);
    head.addPoint(loc.x + 35, loc.y + 30);
    display.add(base);
    display.add(body);
    display.add(head);
  }

  public boolean isWhite() {
    return isWhite;
  }

  @Override
  public void setLocation(ChessSquare newLoc) {
    loc = newLoc;
    display.clear();
    Polygon base = new Polygon();
    base.addPoint(loc.x + 10, loc.y + 70);
    base.addPoint(loc.x + 80, loc.y + 70);
    base.addPoint(loc.x + 80, loc.y + 60);
    base.addPoint(loc.x + 10, loc.y + 60);
    Polygon body = new Polygon();
    body.addPoint(loc.x + 30, loc.y + 60);
    body.addPoint(loc.x + 60, loc.y + 60);
    body.addPoint(loc.x + 70, loc.y + 30);
    body.addPoint(loc.x + 20, loc.y + 30);
    body.addPoint(loc.x + 30, loc.y + 60);
    Polygon head = new Polygon();
    head.addPoint(loc.x + 35, loc.y + 30);
    head.addPoint(loc.x + 55, loc.y + 30);
    head.addPoint(loc.x + 55, loc.y + 10);
    head.addPoint(loc.x + 35, loc.y + 10);
    head.addPoint(loc.x + 35, loc.y + 30);
    display.add(base);
    display.add(body);
    display.add(head);
  }

  @Override
  public List<ChessSquare> getPossibleMoves(ChessBoard grid, boolean whiteTurn) {
    List<ChessSquare> moves = new ArrayList<>();
    if (isWhite() == whiteTurn) {
      int nextRow = isWhite() ? loc.row - 1 : loc.row + 1;
      grid.squareAtColRow(loc.col, nextRow).ifPresent(moves::add);
    }
    return moves;
  }
}
