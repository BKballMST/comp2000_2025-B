import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Stage {
  Grid grid;
  List<Actor> actors;
  Actor selectedPawn = null;
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
  }
  
  public void selectPawnAt(Cell cell) {
    for (Actor a : actors) {
      if (a.loc == cell && a instanceof Pawn) {
        Pawn pawn = (Pawn)a;
        if (pawn.isWhite () == whiteTurn) {
          selectedPawn = a;
          possibleMoves.clear();
          int nextRow = pawn.isWhite() ? cell.row - 1 : cell.row + 1;
          possibleMoves.add(grid.cellAtColRow(cell.col, nextRow).orElse(null));
          return;
        }
      }
    }
    selectedPawn = null;
    possibleMoves.clear();
  }

  public boolean isCellOccupied(Cell cell) {
    for (Actor a : actors) {
      if (a.loc == cell) {
        return true;          
      }
    }
    return false;
  }
  public void moveSelectedPawnTo(Cell cell) {
    if (selectedPawn != null && possibleMoves.contains(cell) && !isCellOccupied(cell)) {
      if (selectedPawn instanceof PawnMove) {
        ((PawnMove)selectedPawn).setPawnLocation(cell);
      } else {
        selectedPawn.loc = cell;
      }
      selectedPawn = null;
      possibleMoves.clear();
      whiteTurn = !whiteTurn;
    }
  }

  public void paint(Graphics g, Point mouseLoc) {
    grid.paint(g, mouseLoc);

    g.setColor(new Color(0, 255, 0, 128));
    for(Cell move: possibleMoves) {
      if(move != null) {
        g.fillRect(move.x, move.y, Cell.size, Cell.size);
      }
    }
    if (selectedPawn != null) {
      g.setColor(Color.YELLOW);
      g.fillRect(selectedPawn.loc.x, selectedPawn.loc.y, Cell.size, Cell.size);
    }
    Optional<Cell> underMouse = grid.cellAtPoint(mouseLoc);
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
    for(Actor a: actors) {
      a.paint(g);
    }
  }
}
