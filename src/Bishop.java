
import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends ChessPiece implements ChessMove {
    private boolean isWhite;

    public Bishop(ChessSquare inLoc, boolean isWhite) {
        loc = inLoc;
        color = isWhite ? Color.WHITE : Color.BLACK;
        this.isWhite = isWhite;
        display = new ArrayList<Polygon>();
        Polygon base = new Polygon();
        base.addPoint(loc.x + 25, loc.y + 70);
        base.addPoint(loc.x + 65, loc.y + 70);
        base.addPoint(loc.x + 65, loc.y + 60);
        base.addPoint(loc.x + 25, loc.y + 60);
        Polygon body = new Polygon();
        body.addPoint(loc.x + 35, loc.y + 60);
        body.addPoint(loc.x + 55, loc.y + 60);
        body.addPoint(loc.x + 55, loc.y + 30);
        body.addPoint(loc.x + 35, loc.y + 30);
        Polygon head = new Polygon();
        head.addPoint(loc.x + 45, loc.y + 30);
        head.addPoint(loc.x + 50, loc.y + 20);
        head.addPoint(loc.x + 45, loc.y + 10);
        head.addPoint(loc.x + 40, loc.y + 20);
        head.addPoint(loc.x + 45, loc.y + 30);
        display.add(base);
        display.add(body);
        display.add(head);
    }

    public boolean isWhite() {
        return isWhite;
    }

    @Override
    public void setLocation(ChessSquare newLoc) {
        loc = newLoc;
        display.clear();
        Polygon base = new Polygon();
        base.addPoint(loc.x + 25, loc.y + 70);
        base.addPoint(loc.x + 65, loc.y + 70);
        base.addPoint(loc.x + 65, loc.y + 60);
        base.addPoint(loc.x + 25, loc.y + 60);
        Polygon body = new Polygon();
        body.addPoint(loc.x + 35, loc.y + 60);
        body.addPoint(loc.x + 55, loc.y + 60);
        body.addPoint(loc.x + 55, loc.y + 30);
        body.addPoint(loc.x + 35, loc.y + 30);
        Polygon head = new Polygon();
        head.addPoint(loc.x + 45, loc.y + 30);
        head.addPoint(loc.x + 50, loc.y + 20);
        head.addPoint(loc.x + 45, loc.y + 10);
        head.addPoint(loc.x + 40, loc.y + 20);
        head.addPoint(loc.x + 45, loc.y + 30);
        display.add(base);
        display.add(body);
        display.add(head);
    }

    @Override
    public List<ChessSquare> getPossibleMoves(ChessBoard board, boolean whiteTurn, List<ChessPiece> allPieces) {
        List<ChessSquare> moves = new ArrayList<>();
        if (isWhite() == whiteTurn) {
            // Can only move diagonally any number of squares
            int[] directions = {-1, 1};
            for (int i : directions) {
                for (int j : directions) {
                    char newCol = loc.col;
                    int newRow = loc.row;
                    while (true) {
                        newCol += i;
                        newRow += j;
                        if (newCol < 'A' || newCol > 'H' || newRow < 0 || newRow >= 8) break;
                        ChessSquare square = board.squareAtColRow(newCol, newRow).orElse(null);
                        if (square != null) {
                            moves.add(square);
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
