import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Stage {
  Grid grid;
  List<Actor> actors;
  Actor selectedPiece = null;
  List<Cell> possibleMoves = new ArrayList<>();
  boolean whiteTurn = true;

  public Stage() {
    grid = new Grid();
    actors = new ArrayList<Actor>();
    //White Pawns
    actors.add(new Pawn(grid.cellAtColRow(4, 6).get(), true));
    actors.add(new Pawn(grid.cellAtColRow(3, 6).get(), true));
    //Black Pawns
    actors.add(new Pawn(grid.cellAtColRow(4, 1).get(), false));
    actors.add(new Pawn(grid.cellAtColRow(3, 1).get(), false));
    //White King
    actors.add(new King(grid.cellAtColRow(4, 7).get(), true));
    //Black King
    actors.add(new King(grid.cellAtColRow(4, 0).get(), false));
  }
  
  public void selectPieceAt(Cell cell) {
    // Find the actor at the clicked cell
    for (Actor a : actors) {
      // Check if the actor is at the clicked cell
      if (a.loc == cell) {
        // If it's a pawn of the correct color, select it and calculate possible moves
        if (a instanceof Pawn) {
          Pawn pawn = (Pawn)a;
          if (pawn.isWhite () == whiteTurn) {
            selectedPiece = a;
            possibleMoves.clear();
            int nextRow = pawn.isWhite() ? cell.row - 1 : cell.row + 1;
            possibleMoves.add(grid.cellAtColRow(cell.col, nextRow).orElse(null));
            return;
          }
        } //Select King and calculate possible moves 
        else if (a instanceof King) {
          King king = (King)a;
          if (king.isWhite() == whiteTurn) {
            selectedPiece = a;
            possibleMoves.clear();
            //King can move one square in any direction
            for (int dCol = -1; dCol <= 1; dCol++) {
              for (int dRow = -1; dRow <= 1; dRow++) {
                char newCol = (char)(cell.col + dCol);
                int newRow = cell.row + dRow;
                grid.cellAtColRow(newCol, newRow).ifPresent(possibleMoves::add);
              }
            }
            return;
          }
        }
      }
    }
    // If no valid piece is found, clear selection
    selectedPiece = null;
    possibleMoves.clear();
  }
  // Check if a cell is occupied by any actor
  public boolean isCellOccupied(Cell cell) {
    for (Actor a : actors) {
      if (a.loc == cell) {
        return true;          
      }
    }
    return false;
  }
  public void moveSelectedPieceTo(Cell cell) {
    // Move the selected piece if the move is valid and the cell is unoccupied
    if (selectedPiece != null && possibleMoves.contains(cell) && !isCellOccupied(cell)) {
      // Use the appropriate interface method to set the location
      if (selectedPiece instanceof PawnMove) {
        //Pawn can only move forward
        ((PawnMove)selectedPiece).setPawnLocation(cell);
      } else if (selectedPiece instanceof KingMove) {
        //King can move in any direction
        ((KingMove)selectedPiece).setKingLocation(cell);
      } else {
        // Fallback for other actors
        selectedPiece.loc = cell;
      }
      // Clear selection and possible moves after moving
      selectedPiece = null;
      possibleMoves.clear();
      // Switch turns (white to black or black to white)
      whiteTurn = !whiteTurn;
    }
  }

  public void paint(Graphics g, Point mouseLoc) {
    grid.paint(g, mouseLoc);
    // Highlight possible moves
    g.setColor(new Color(0, 255, 0, 128));
    for(Cell move: possibleMoves) {
      if(move != null && !isCellOccupied(move)) {
        // Highlight the cell for possible move if not occupied
        g.fillRect(move.x, move.y, Cell.size, Cell.size);
      }
    }
    if (selectedPiece != null) {
      // Highlight the selected piece
      g.setColor(Color.YELLOW);
      g.fillRect(selectedPiece.loc.x, selectedPiece.loc.y, Cell.size, Cell.size);
    }
    Optional<Cell> underMouse = grid.cellAtPoint(mouseLoc);
    // Highlight cell under mouse and show coordinates
    if(underMouse.isPresent()) {
      Cell hoverCell = underMouse.get();
      for (Actor a: actors) {
        if(a.loc == hoverCell) {
          g.setColor(Color.YELLOW);
          g.fillRect(hoverCell.x, hoverCell.y, Cell.size, Cell.size);
        }
      }
      g.setColor(Color.DARK_GRAY);
      g.drawString(String.valueOf(hoverCell.col) + String.valueOf(hoverCell.row), 740, 30);
    }
    // Draw all actors
    for(Actor a: actors) {
      a.paint(g);
    }
  }
}
