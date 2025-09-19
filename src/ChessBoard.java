import java.awt.Graphics;
import java.awt.Point;
import java.util.Optional;

public class ChessBoard {
  // 8x8 grid of ChessSquares
  ChessSquare[][] square = new ChessSquare[8][8];
  
  public ChessBoard() {
    // Initialize the board with squares labeled A-H and 0-7
    for(int i=0; i<square.length; i++) {
      for(int j=0; j<square[i].length; j++) {
        square[i][j] = new ChessSquare(colToLabel(i), j, 10+ChessSquare.size*i, 10+ChessSquare.size*j);
      }
    }
  }

  private char colToLabel(int col) {
    // Convert column index to letter label
    return (char) (col + Character.valueOf('A'));
  }

  private int labelToCol(char col) {
    // Convert letter label to column index
    return (int) (col - Character.valueOf('A'));
  }

  public void paint(Graphics g, Point mousePos) {
    // Paint each square in the grid
    for(int i=0; i<square.length; i++) {
      for(int j=0; j<square[i].length; j++) {
        square[i][j].paint(g, mousePos);
      }
    }
  }

  public Optional<ChessSquare> squareAtColRow(int c, int r) {
    // Return the square at the specified column and row if within bounds
    if(c >= 0 && c < square.length && r >=0 && r < square[c].length) {
      return Optional.of(square[c][r]);
    } else {
      // Out of bounds
      return Optional.empty();
    }
  }

  public Optional<ChessSquare> squareAtColRow(char c, int r) {
    // Overloaded method to accept column as char
    return squareAtColRow(labelToCol(c), r);
  }

  public Optional<ChessSquare> squareAtPoint(Point p) {
    // Return the square that contains the given point
    for(int i=0; i < square.length; i++) {
      for(int j=0; j < square[i].length; j++) {
        if(square[i][j].contains(p)) {
          // Found the square containing the point
          return Optional.of(square[i][j]);
        }
      }
    }
    // No square contains the point
    return Optional.empty();
  }
}
