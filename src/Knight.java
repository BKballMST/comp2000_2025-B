import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

public class Knight extends ChessPiece implements ChessMove {
    private boolean isWhite;

    public Knight(ChessSquare inLoc, boolean isWhite) {
        loc = inLoc;
        color = isWhite ? Color.WHITE : Color.BLACK;
        this.isWhite = isWhite;
        display = new ArrayList<Polygon>();
        // Simple polygon for knight
        Polygon body = new Polygon();
        body.addPoint(loc.x + 40, loc.y + 70);
        body.addPoint(loc.x + 60, loc.y + 70);
        body.addPoint(loc.x + 60, loc.y + 40);
        body.addPoint(loc.x + 50, loc.y + 30);
        body.addPoint(loc.x + 40, loc.y + 40);
        body.addPoint(loc.x + 40, loc.y + 70);
        display.add(body);
    }

    public boolean isWhite() {
        return isWhite;
    }

    @Override
    public void setLocation(ChessSquare newLoc) {
        loc = newLoc;
        display.clear();
        Polygon body = new Polygon();
        body.addPoint(loc.x + 40, loc.y + 70);
        body.addPoint(loc.x + 60, loc.y + 70);
        body.addPoint(loc.x + 60, loc.y + 40);
        body.addPoint(loc.x + 50, loc.y + 30);
        body.addPoint(loc.x + 40, loc.y + 40);
        body.addPoint(loc.x + 40, loc.y + 70);
        display.add(body);
    }

    @Override
    public List<ChessSquare> getPossibleMoves(ChessBoard board, boolean whiteTurn, List<ChessPiece> allPieces) {
        List<ChessSquare> moves = new ArrayList<>();
        if (isWhite() == whiteTurn) {
            // Can move in "L" shape: 2 in one direction and 1 perpendicular
            int[] dCol = {-2, -1, 1, 2};
            int[] dRow = {-2, -1, 1, 2};
            for (int dc : dCol) {
                for (int dr : dRow) {
                    if (Math.abs(dc) + Math.abs(dr) == 3) {
                        char newCol = (char)(loc.col + dc);
                        int newRow = loc.row + dr;
                        if (newCol >= 'A' && newCol <= 'H' && newRow >= 0 && newRow < 8) {
                            ChessSquare square = board.squareAtColRow(newCol, newRow).orElse(null);
                            if (square != null) {
                                boolean occupied = false;
                                for (ChessPiece piece : allPieces) {
                                    if (piece.loc == square) {
                                        occupied = true;
                                        break;
                                    }
                                }
                                if (!occupied) {
                                    moves.add(square);
                                }
                            }
                        }
                    }
                }
            }
        }
        return moves;
    }
}
