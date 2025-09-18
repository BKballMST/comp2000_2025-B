import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Stage {
  Grid grid;
  List<Actor> actors;

  public Stage() {
    grid = new Grid();
    actors = new ArrayList<Actor>();
    actors.add(new Pawn(grid.cellAtColRow(4, 6).get()));
    actors.add(new Pawn(grid.cellAtColRow(3, 6).get()));
  }
  
  public void paint(Graphics g, Point mouseLoc) {
    grid.paint(g, mouseLoc);
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
