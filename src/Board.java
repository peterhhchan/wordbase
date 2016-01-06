import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel {

    private final int CELL_SIZE = 30;

    private int rows;
    private int cols;

    private char [][] board;
    private boolean [][] orangeTiles;
    private boolean [][] blueTiles;
    private GameBoard gb;
    
    public Board(GameBoard gb) {
    	this.gb = gb;
        setDoubleBuffered(true);

        addMouseListener(new WordBaseMouseAdapter());
        newGame();
    }

    public int GetRows()
    {
    	return rows;
    }
    
    public int GetCols()
    {
    	return cols;
    }
    
    private void newGame() {

    	char [][] board = gb.GetPuzzle();
    	if (board == null)
    	{
    		return;
    	}
    	
    	this.board = board;
    	
    	rows = board.length;
    	
    	if (rows == 0)
    	{
    		return;
    	}
    	
    	cols = board[0].length;
        
    	if (cols == 0)
    	{
    		return;
    	}
    	
    	blueTiles = new boolean[rows][cols];
    	orangeTiles = new boolean[rows][cols];
    	for (int i = 0; i < cols;i++)
    	{
    	    orangeTiles[0][i] = true;
    	    blueTiles[rows-1][i] = true;
    	}
    }


    @Override
    public void paintComponent(Graphics g) {

    	Font f = new Font("Dialog", Font.BOLD, 20);
    	g.setFont(f);
    	
        int cell = 0;
        int uncover = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
            	String text = Character.toString(board[i][j]); 
            	text = text.toUpperCase();
            	
            	if (blueTiles[i][j])
            	{
            		g.setColor(Color.BLUE);
            	}
            	else if (orangeTiles[i][j])
            	{
            		g.setColor(Color.ORANGE);
            	}
            	else
            	{
            		g.setColor(Color.BLACK);
            	}
            	
                g.drawString(text , (j * CELL_SIZE),
                    (i+1) * CELL_SIZE);
            }
        }
    }


    class WordBaseMouseAdapter extends MouseAdapter {
        
        @Override
        public void mousePressed(MouseEvent e) {

            int x = e.getX();
            int y = e.getY();

            int cCol = x / CELL_SIZE;
            int cRow = y / CELL_SIZE;

            boolean rep = false;

            if (e.getButton() == MouseEvent.BUTTON3) 
            {
            	gb.PrintWordsToConsole(cRow, cCol, false);
            }
            else
            {
            	gb.PrintWordsToConsole(cRow, cCol, true);
            }
        	//System.out.println(cRow + ", " + cCol);
            //if (!inGame) {
                //newGame();
                //repaint();
            //}
    		
        	
        	
                if (rep)
                    repaint();

            
        }
    }
}