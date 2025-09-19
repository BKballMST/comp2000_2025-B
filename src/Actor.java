import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.List;

public abstract class Actor {
  Color color;
  Cell loc;
  List<Polygon> display;
  
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
