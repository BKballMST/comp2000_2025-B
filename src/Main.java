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
      Stage stage = new Stage();
      public Canvas() {
        setPreferredSize(new Dimension(1024, 740));
        // Handle mouse clicks for selecting and moving pieces
        addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            // Get the cell that was clicked
            Optional<Cell> cellOpt = stage.grid.cellAtPoint(e.getPoint());
            if(cellOpt.isPresent()) {
              Cell clickedCell = cellOpt.get();
              if(stage.selectedPiece == null) {
                // No piece selected yet, try to select one
                stage.selectPieceAt(clickedCell);
              } else {
                // Deselect if clicking the selected piece
                if (stage.selectedPiece.loc == clickedCell) {
                  stage.selectedPiece = null;
                  stage.possibleMoves.clear();
                } else {
                  // Try to move the selected piece to the clicked cell
                  stage.moveSelectedPieceTo(clickedCell);
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
        // Delegate painting to the stage
        stage.paint(g, getMousePosition());
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
