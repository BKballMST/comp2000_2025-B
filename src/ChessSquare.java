import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class ChessSquare extends Rectangle {
  static int size = 90;
  char col;
  int row;
  Color chessGreen = new Color(118, 150, 86);
  Color chessWhite = new Color(238, 238, 210);

  public ChessSquare(char inCol, int inRow, int x, int y) {
    // Initialize cell position and size
    super(x, y, size, size);
    col = inCol;
    row = inRow;
  }

  public void paint(Graphics g, Point mousePos) {
    //Create chessboard pattern
    Color chessColor = ((col - 'A' + row) % 2 == 0) ? chessWhite : chessGreen;
    g.setColor(chessColor);
    g.fillRect(x, y, size, size);
    g.setColor(Color.BLACK);
    g.drawRect(10, 10, 720, 720);
  }

  public boolean contains(Point p) {
    // Safely check if point is within cell bounds
    if(p != null) {
      return super.contains(p);
    } else {
      return false;
    }
  }
}
