import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

public class Rook extends ChessPiece implements ChessMove {
    private boolean isWhite;

    public Rook(ChessSquare inLoc, boolean isWhite) {
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
        body.addPoint(loc.x + 20, loc.y + 60);
        body.addPoint(loc.x + 70, loc.y + 60);
        body.addPoint(loc.x + 70, loc.y + 20);
        body.addPoint(loc.x + 20, loc.y + 20);
        Polygon top = new Polygon();
        top.addPoint(loc.x + 25, loc.y + 20);
        top.addPoint(loc.x + 65, loc.y + 20);
        top.addPoint(loc.x + 65, loc.y + 10);
        top.addPoint(loc.x + 25, loc.y + 10);
        top.addPoint(loc.x + 25, loc.y + 20);
        display.add(base);
        display.add(body);
        display.add(top);
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
        body.addPoint(loc.x + 20, loc.y + 60);
        body.addPoint(loc.x + 70, loc.y + 60);
        body.addPoint(loc.x + 70, loc.y + 20);
        body.addPoint(loc.x + 20, loc.y + 20);
        Polygon top = new Polygon();
        top.addPoint(loc.x + 25, loc.y + 20);
        top.addPoint(loc.x + 65, loc.y + 20);
        top.addPoint(loc.x + 65, loc.y + 10);
        top.addPoint(loc.x + 25, loc.y + 10);
        top.addPoint(loc.x + 25, loc.y + 20);
        display.add(base);
        display.add(body);
        display.add(top);
    }

    @Override
    public List<ChessSquare> getPossibleMoves(ChessBoard board, boolean whiteTurn, List<ChessPiece> allPieces) {
         List<ChessSquare> moves = new ArrayList<>();
        if (isWhite() == whiteTurn) {
            int[] directions = {-1, 0, 1};
            for (int i : directions) {
                for (int j : directions) {
                    // Only allow straight lines (vertical/horizontal), not diagonal
                    if ((i == 0 && j == 0) || (i != 0 && j != 0)) continue;
                    char newCol = loc.col;
                    int newRow = loc.row;
                    while (true) {
                        newCol += i;
                        newRow += j;
                        if (newCol < 'A' || newCol > 'H' || newRow < 0 || newRow >= 8) break; // Out of bounds
                        ChessSquare square = board.square[newCol - 'A'][newRow];
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
