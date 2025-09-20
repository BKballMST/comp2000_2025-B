import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece implements ChessMove {
    private boolean isWhite;

    public Queen(ChessSquare inLoc, boolean isWhite) {
        loc = inLoc;
        color = isWhite ? Color.WHITE : Color.BLACK;
        this.isWhite = isWhite;
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
        Polygon crown = new Polygon();
        crown.addPoint(loc.x + 35, loc.y + 30);
        crown.addPoint(loc.x + 55, loc.y + 30);
        crown.addPoint(loc.x + 55, loc.y + 10);
        crown.addPoint(loc.x + 35, loc.y + 10);
        crown.addPoint(loc.x + 35, loc.y + 30);
        display.add(base);
        display.add(body);
        display.add(crown);
    }

    public boolean isWhite() {
        return isWhite;
    }

    @Override
    public void setLocation(ChessSquare newLoc) {
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
        Polygon crown = new Polygon();
        crown.addPoint(loc.x + 35, loc.y + 30);
        crown.addPoint(loc.x + 55, loc.y + 30);
        crown.addPoint(loc.x + 55, loc.y + 10);
        crown.addPoint(loc.x + 35, loc.y + 10);
        crown.addPoint(loc.x + 35, loc.y + 30);
        display.add(base);
        display.add(body);
        display.add(crown);
    }

    @Override
    public List<ChessSquare> getPossibleMoves(ChessBoard board, boolean whiteTurn, List<ChessPiece> allPieces) {
        List<ChessSquare> moves = new ArrayList<>();
        if (isWhite() == whiteTurn) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    char newCol = (char)(loc.col);
                    int newRow = loc.row;
                    while (true) {
                        newCol += i;
                        newRow += j;
                        if (newCol < 'A' || newCol > 'H' || newRow < 0 || newRow >= 8) break; // Out of bounds
                        ChessSquare square = board.squareAtColRow(newCol, newRow).orElse(null);
                        if (square != null) {
                            moves.add(square);
                            // If there's a piece in the way, stop in that direction
                            boolean occupied = false;
                            for (ChessPiece piece : allPieces) {
                                if (piece.loc == square) {
                                    occupied = true;
                                    break;
                                }
                            }
                            if (occupied) break;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return moves;
    }
}
