import java.awt.Graphics;
import java.awt.Point;
import java.util.Optional;

public class Grid {
  // 8x8 grid of cells
  Cell[][] cells = new Cell[8][8];
  
  public Grid() {
    // Initialize the grid with cells labeled A-H and 0-7
    for(int i=0; i<cells.length; i++) {
      for(int j=0; j<cells[i].length; j++) {
        cells[i][j] = new Cell(colToLabel(i), j, 10+Cell.size*i, 10+Cell.size*j);
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
    // Paint each cell in the grid
    for(int i=0; i<cells.length; i++) {
      for(int j=0; j<cells[i].length; j++) {
        cells[i][j].paint(g, mousePos);
      }
    }
  }

  public Optional<Cell> cellAtColRow(int c, int r) {
    // Return the cell at the specified column and row if within bounds
    if(c >= 0 && c < cells.length && r >=0 && r < cells[c].length) {
      return Optional.of(cells[c][r]);
    } else {
      // Out of bounds
      return Optional.empty();
    }
  }

  public Optional<Cell> cellAtColRow(char c, int r) {
    // Overloaded method to accept column as char
    return cellAtColRow(labelToCol(c), r);
  }

  public Optional<Cell> cellAtPoint(Point p) {
    // Return the cell that contains the given point
    for(int i=0; i < cells.length; i++) {
      for(int j=0; j < cells[i].length; j++) {
        if(cells[i][j].contains(p)) {
          // Found the cell containing the point
          return Optional.of(cells[i][j]);
        }
      }
    }
    // No cell contains the point
    return Optional.empty();
  }
}
