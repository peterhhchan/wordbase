
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


public class Game extends JFrame {

    private final int CELL_SIZE = 30;

    private final JLabel statusbar;
    
    public Game(GameBoard gb) {

    	Board board = new Board(gb);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int cols = board.GetCols();
        int rows = board.GetRows() + 2;
        setSize( cols * CELL_SIZE, rows * CELL_SIZE);
        setLocationRelativeTo(null);
        setTitle("WordBase");

        statusbar = new JLabel("");
        add(statusbar, BorderLayout.SOUTH);
        
        add(board);

        setResizable(false);
    }
    
    public static void main(String[] args) {
    	
	
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
        		GameBoard gb = new GameBoard();
                JFrame ex = new Game(gb);
                ex.setVisible(true);                
            }
        });
    }
}