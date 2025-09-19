import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.List;

public abstract class ChessPiece {
  Color color;
  ChessSquare loc;
  List<Polygon> display;

  // Abstract method to get possible moves for the piece
  public abstract List<ChessSquare> getPossibleMoves(ChessBoard grid, boolean whiteTurn);
  
  public void paint(Graphics g) {
    // Draw each polygon that makes up the actor
    for(Polygon p: display) {
      // Fill the polygon with the actor's color
      g.setColor(color);
      g.fillPolygon(p);
      // Draw the outline
      g.setColor(Color.BLACK);
      g.drawPolygon(p);
    }
  }
}
