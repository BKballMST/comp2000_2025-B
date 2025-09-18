import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;

public class Pawn extends Actor {
  public Pawn(Cell inLoc) {
    loc = inLoc;
    color = Color.WHITE;
    display = new ArrayList<Polygon>();
    Polygon base = new Polygon();
    base.addPoint(loc.x + 10, loc.y + 70);
    base.addPoint(loc.x + 80, loc.y + 70);
    base.addPoint(loc.x + 80, loc.y + 60);
    base.addPoint(loc.x + 10, loc.y + 60);
    Polygon body = new Polygon();
    body.addPoint(loc.x + 30, loc.y + 60);
    body.addPoint(loc.x + 60, loc.y + 60);
    body.addPoint(loc.x + 70, loc.y + 30);
    body.addPoint(loc.x + 20, loc.y + 30);
    body.addPoint(loc.x + 30, loc.y + 60);
    Polygon head = new Polygon();
    head.addPoint(loc.x + 35, loc.y + 30);
    head.addPoint(loc.x + 55, loc.y + 30);
    head.addPoint(loc.x + 55, loc.y + 10);
    head.addPoint(loc.x + 35, loc.y + 10);
    head.addPoint(loc.x + 35, loc.y + 30);
    display.add(base);
    display.add(body);
    display.add(head);
  }
  public void setLocation(Cell newLoc) {
    loc = newLoc;
    display.clear();
    Polygon base = new Polygon();
    base.addPoint(loc.x + 10, loc.y + 70);
    base.addPoint(loc.x + 80, loc.y + 70);
    base.addPoint(loc.x + 80, loc.y + 60);
    base.addPoint(loc.x + 10, loc.y + 60);
    Polygon body = new Polygon();
    body.addPoint(loc.x + 30, loc.y + 60);
    body.addPoint(loc.x + 60, loc.y + 60);
    body.addPoint(loc.x + 70, loc.y + 30);
    body.addPoint(loc.x + 20, loc.y + 30);
    body.addPoint(loc.x + 30, loc.y + 60);
    Polygon head = new Polygon();
    head.addPoint(loc.x + 35, loc.y + 30);
    head.addPoint(loc.x + 55, loc.y + 30);
    head.addPoint(loc.x + 55, loc.y + 10);
    head.addPoint(loc.x + 35, loc.y + 10);
    head.addPoint(loc.x + 35, loc.y + 30);
    display.add(base);
    display.add(body);
    display.add(head);
  }
}
