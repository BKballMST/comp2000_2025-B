import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;

public class Main extends JFrame {
    public static void main(String[] args) throws Exception {
      Main window = new Main();
      window.run();
    }

    class Canvas extends JPanel {
      GamePlay game = new GamePlay();
      public Canvas() {
        setPreferredSize(new Dimension(1024, 740));
        // Handle mouse clicks for selecting and moving pieces
        addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            // Get the square that was clicked
            Optional<ChessSquare> squareOpt = game.board.squareAtPoint(e.getPoint());
            if(squareOpt.isPresent()) {
              ChessSquare clickedSquare = squareOpt.get();
              if(game.selectedPiece == null) {
                // No piece selected yet, try to select one
                game.selectPieceAt(clickedSquare);
              } else {
                // Deselect if clicking the selected piece
                if (game.selectedPiece.loc == clickedSquare) {
                  game.selectedPiece = null;
                  game.possibleMoves.clear();
                } else {
                  // Try to move the selected piece to the clicked cell
                  game.moveSelectedPieceTo(clickedSquare);
                }
              }
            }
            // Repaint to show selection/move changes
            repaint();
          }
        });
      }

      @Override
      public void paint(Graphics g) {
        // Delegate painting to the game
        game.paint(g, getMousePosition());
      }
    }

    private Main() {
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Canvas canvas = new Canvas();
      this.setContentPane(canvas);
      this.pack();
      this.setVisible(true);
    }

    public void run() {
      while(true) {
        repaint();
      }
    }
}
