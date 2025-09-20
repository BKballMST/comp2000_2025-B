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
        // Head
        Polygon head = new Polygon();
        head.addPoint(loc.x + 50, loc.y + 20);
        head.addPoint(loc.x + 60, loc.y + 30);
        head.addPoint(loc.x + 55, loc.y + 40);
        head.addPoint(loc.x + 45, loc.y + 35);
        head.addPoint(loc.x + 50, loc.y + 20);

        // Neck
        Polygon neck = new Polygon();
        neck.addPoint(loc.x + 45, loc.y + 35);
        neck.addPoint(loc.x + 55, loc.y + 40);
        neck.addPoint(loc.x + 55, loc.y + 60);
        neck.addPoint(loc.x + 45, loc.y + 60);

        // Mane
        Polygon mane = new Polygon();
        mane.addPoint(loc.x + 45, loc.y + 35);
        mane.addPoint(loc.x + 40, loc.y + 40);
        mane.addPoint(loc.x + 42, loc.y + 50);
        mane.addPoint(loc.x + 45, loc.y + 60);

        // Base
        Polygon base = new Polygon();
        base.addPoint(loc.x + 40, loc.y + 60);
        base.addPoint(loc.x + 60, loc.y + 60);
        base.addPoint(loc.x + 60, loc.y + 70);
        base.addPoint(loc.x + 40, loc.y + 70);

        display.add(head);
        display.add(neck);
        display.add(mane);
        display.add(base);
    }

    public boolean isWhite() {
        return isWhite;
    }

    @Override
    public void setLocation(ChessSquare newLoc) {
        loc = newLoc;
        display.clear();
        // Head
        Polygon head = new Polygon();
        head.addPoint(loc.x + 50, loc.y + 20);
        head.addPoint(loc.x + 60, loc.y + 30);
        head.addPoint(loc.x + 55, loc.y + 40);
        head.addPoint(loc.x + 45, loc.y + 35);
        head.addPoint(loc.x + 50, loc.y + 20);

        // Neck
        Polygon neck = new Polygon();
        neck.addPoint(loc.x + 45, loc.y + 35);
        neck.addPoint(loc.x + 55, loc.y + 40);
        neck.addPoint(loc.x + 55, loc.y + 60);
        neck.addPoint(loc.x + 45, loc.y + 60);

        // Mane
        Polygon mane = new Polygon();
        mane.addPoint(loc.x + 45, loc.y + 35);
        mane.addPoint(loc.x + 40, loc.y + 40);
        mane.addPoint(loc.x + 42, loc.y + 50);
        mane.addPoint(loc.x + 45, loc.y + 60);

        // Base
        Polygon base = new Polygon();
        base.addPoint(loc.x + 40, loc.y + 60);
        base.addPoint(loc.x + 60, loc.y + 60);
        base.addPoint(loc.x + 60, loc.y + 70);
        base.addPoint(loc.x + 40, loc.y + 70);

        display.add(head);
        display.add(neck);
        display.add(mane);
        display.add(base);
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
