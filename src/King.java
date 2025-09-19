import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;

public class King extends Actor implements KingMove {
    private boolean isWhite;

    public King(Cell inLoc, boolean isWhite) {
        loc = inLoc;
        color = isWhite ? Color.WHITE : Color.BLACK;
        this.isWhite = isWhite;
        display = new ArrayList<Polygon>();
        Polygon base = new Polygon();
        base.addPoint(loc.x + 20, loc.y + 70);
        base.addPoint(loc.x + 70, loc.y + 70);
        base.addPoint(loc.x + 70, loc.y + 60);
        base.addPoint(loc.x + 20, loc.y + 60);
        Polygon body = new Polygon();
        body.addPoint(loc.x + 30, loc.y + 60);
        body.addPoint(loc.x + 60, loc.y + 60);
        body.addPoint(loc.x + 60, loc.y + 30);
        body.addPoint(loc.x + 30, loc.y + 30);
        Polygon crown = new Polygon();
        crown.addPoint(loc.x + 40, loc.y + 30);
        crown.addPoint(loc.x + 50, loc.y + 30);
        crown.addPoint(loc.x + 45, loc.y + 10);
        display.add(base);
        display.add(body);
        display.add(crown);
    }

    public boolean isWhite() {
        return isWhite;
    }
    public void setKingLocation(Cell newLoc) {
        loc = newLoc;
        display.clear();
        Polygon base = new Polygon();
        base.addPoint(loc.x + 20, loc.y + 70);
        base.addPoint(loc.x + 70, loc.y + 70);
        base.addPoint(loc.x + 70, loc.y + 60);
        base.addPoint(loc.x + 20, loc.y + 60);
        Polygon body = new Polygon();
        body.addPoint(loc.x + 30, loc.y + 60);
        body.addPoint(loc.x + 60, loc.y + 60);
        body.addPoint(loc.x + 60, loc.y + 30);
        body.addPoint(loc.x + 30, loc.y + 30);
        Polygon crown = new Polygon();
        crown.addPoint(loc.x + 40, loc.y + 30);
        crown.addPoint(loc.x + 50, loc.y + 30);
        crown.addPoint(loc.x + 45, loc.y + 10);
        display.add(base);
        display.add(body);
        display.add(crown);
    }
}