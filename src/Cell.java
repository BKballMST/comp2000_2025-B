import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Cell extends Rectangle {
  static int size = 90;
  char col;
  int row;
  Color chessGreen = new Color(118, 150, 86);
  Color chessWhite = new Color(238, 238, 210);

  public Cell(char inCol, int inRow, int x, int y) {
    super(x, y, size, size);
    col = inCol;
    row = inRow;
  }

  public void paint(Graphics g, Point mousePos) {
    Color chessColor = ((col - 'A' + row) % 2 == 0) ? chessWhite : chessGreen;
    g.setColor(chessColor);
    g.fillRect(x, y, size, size);
    g.setColor(Color.BLACK);
    g.drawRect(10, 10, 720, 720);
  }

  public boolean contains(Point p) {
    if(p != null) {
      return super.contains(p);
    } else {
      return false;
    }
  }
}
