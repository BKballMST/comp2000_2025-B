import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

public class King extends ChessPiece implements ChessMove {
    private boolean isWhite;

    public King(ChessSquare inLoc, boolean isWhite) {
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
    public void setLocation(ChessSquare newLoc) {
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

    @Override
    public List<ChessSquare> getPossibleMoves(ChessBoard board, boolean whiteTurn) {
        List<ChessSquare> moves = new ArrayList<>();
        if (isWhite() == whiteTurn) {
            //King can move one square in any direction
            for (int dCol = -1; dCol <= 1; dCol++) {
                for (int dRow = -1; dRow <= 1; dRow++) {
                    char newCol = (char)(loc.col + dCol);
                    int newRow = loc.row + dRow;
                    board.squareAtColRow(newCol, newRow).ifPresent(moves::add);
                }
            }
        }
        return moves;
    }
}