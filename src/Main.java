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
        addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            Optional<Cell> cellOpt = stage.grid.cellAtPoint(e.getPoint());
            if(cellOpt.isPresent()) {
              Cell clickedCell = cellOpt.get();
              if(stage.selectedPawn == null) {
                stage.selectPawnAt(clickedCell);
              } else {
                // Deselect if clicking the selected pawn
                if (stage.selectedPawn.loc == clickedCell) {
                  stage.selectedPawn = null;
                  stage.possibleMoves.clear();
                } else {
                  stage.moveSelectedPawnTo(clickedCell);
                }
              }
            }
            repaint();
          }
        });
      }

      @Override
      public void paint(Graphics g) {
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
