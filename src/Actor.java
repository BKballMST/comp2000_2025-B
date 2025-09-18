import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.List;

public abstract class Actor {
  Color color;
  Cell loc;
  List<Polygon> display;
  public abstract void setLocation(Cell newloc);
  
  public void paint(Graphics g) {
    for(Polygon p: display) {
      g.setColor(color);
      g.fillPolygon(p);
      g.setColor(Color.BLACK);
      g.drawPolygon(p);
    }
  }
}
