import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class GameBoard {

	int numRows = 13;
	int numCols = 10;
	GameBoardNode [][] gameBoard;
	WordList wordList;
	
	Set<String> words;
	ArrayList<ResultObject> results;
	char [][] puzzle = new char [numRows][numCols];
	
	public char[][] GetPuzzle()
	{
		return puzzle;
	}
	
	public GameBoardNode GetNode(int row, int col)
	{
		return gameBoard[row][col];
	}
	
	public GameBoard()
	{
		wordList = new WordList();
		results = new ArrayList<ResultObject>();
		
		puzzle = new char [numRows][numCols];
		String path = System.getProperty("user.dir");
		String fullPath = path + "\\gameboard.txt";
		
		try(BufferedReader br = new BufferedReader(new FileReader(fullPath))) {
			for (int i = 0; i < numRows;++i)
			{
				String line = br.readLine();
				
				//System.out.println(line.length());  
				
				if (line == null || line.length() != numCols)
				{
					// Error Message here
					break;
				}
				
				for (int j = 0; j < line.length(); j++ )
				{
					puzzle[i][j]= line.charAt(j);
				}
			}
	    }
		catch (Exception e)
		{
			
		}
		
		gameBoard = new GameBoardNode[numRows][numCols];
		for (int i = 0; i < numRows; i++)
		{
			for (int j = 0; j < numCols; j++)
			{
				gameBoard[i][j] = new GameBoardNode();
			}
		}
		
		for (int i = 0; i < numRows; i++)
		{
			for (int j = 0; j < numCols; j++)
			{
				gameBoard[i][j].SetCharacter(puzzle[i][j]);
				gameBoard[i][j].SetRow(i);
				gameBoard[i][j].SetCol(j);
				
				if (i > 0 && j > 0)
				{
					gameBoard[i][j].AddLink(gameBoard[i-1][j-1]);
				}
				if (j > 0)
				{
					gameBoard[i][j].AddLink(gameBoard[i][j-1]);
				}
				if (i < numRows-1 && j > 0)
				{
					gameBoard[i][j].AddLink(gameBoard[i+1][j-1]);
				}
				
				if (i > 0 )
				{
					gameBoard[i][j].AddLink(gameBoard[i-1][j]);
				}
				if (i < numRows-1)
				{
					gameBoard[i][j].AddLink(gameBoard[i+1][j]);
				}
				
				if (i > 0 && j < numCols -1)
				{
					gameBoard[i][j].AddLink(gameBoard[i-1][j+1]);
				}
				if ( j < numCols -1)
				{
					gameBoard[i][j].AddLink(gameBoard[i][j+1]);
				}
				if (i < numRows-1  && j < numCols -1)
				{
					gameBoard[i][j].AddLink(gameBoard[i+1][j+1]);
				}
				
			}
		}
		
		System.out.println("Done");
		
	}
	
	void SearchNode(GameBoardNode node, String currentString, boolean [][] searchHistory)
	{
		String newString = currentString + node.GetCharacter();
		if (currentString.length() > 1)
		{
			if (wordList.isWord(newString))
			{
				//System.out.println(newString);
				words.add(newString);
				ResultObject ro = new ResultObject(newString, node.GetRow());
				results.add(ro);
			}
			
			if (wordList.isPrefix(newString) == false)
			{
				return;
			}		
		}
		
		searchHistory[node.GetRow()][node.GetCol()] = true;
		
		
		ArrayList<GameBoardNode> links = node.GetLinks();
		Iterator<GameBoardNode> linksIt = links.iterator();
		while(linksIt.hasNext())
		{
			GameBoardNode nodeNext = linksIt.next();
			if (searchHistory[nodeNext.GetRow()][nodeNext.GetCol()] == false)
			{
				SearchNode(nodeNext, newString, searchHistory);
			}
			//System.out.println(nodeNext.GetCharacter());
		}
		
		searchHistory[node.GetRow()][node.GetCol()] = false;
	}
	
	public void GetWords(int row, int col)
	{
		boolean [][] searchHistory = new boolean [numRows][numCols];
		for (int i = 0; i < numRows; i++)
		{
			for (int j = 0; j < numCols; j++)
			{
				searchHistory[i][j] = false;
			}
		}
		
		words = new HashSet<String>();
		GameBoardNode node = GetNode(row, col);
		SearchNode(node, "", searchHistory);
		//String[] wordList = words.toArray(new String[words.size()]);
		//for (int i = 0; i < wordList.length; i++)
		//{
			//System.out.println(wordList[i]);
		//}
	}
	
	public void SortResults()
	{
		Collections.sort(results, new ResultsComparatorLow());
		for (int i = 0; i < results.size();i++)
		{
			System.out.println(results.get(i).word + " " + results.get(i).row);
		}
	}
	
	public void PrintWordsToConsole(int row, int col, boolean top)
	{
		if (row < 0 || row >= numRows)
		{
			return;
		}
		
		if (col < 0 || col >= numCols)
		{
			return;
		}
		
		results.clear();
		boolean [][] searchHistory = new boolean [numRows][numCols];	
		words = new HashSet<String>();
		GameBoardNode node = GetNode(row, col);
		SearchNode(node, "", searchHistory);
		if (top)
		{
			Collections.sort(results, new ResultsComparatorHigh());
		}
		else
		{
			Collections.sort(results, new ResultsComparatorLow());
		}
		for (int i = 0; i < results.size();i++)
		{
			System.out.println(results.get(i).word + " " + results.get(i).row);
		}
		System.out.println("--------------------------------------------");
	}
	
	public static void main(String [] args)
	{
		GameBoard gb = new GameBoard();
		
		
		for (int i = 0; i < 10; i++)
		{
			//gb.GetWords(12, i);
		}

		for (int i = 0; i < 10; i++)
		{
			//gb.GetWords(7, i);
		}
	
		
		gb.GetWords(6,3);//N
		gb.GetWords(7,3);//N
		//gb.GetWords(7,3);//T
		//gb.GetWords(6,4);//S
		//gb.GetWords(4,4);//P
		//gb.GetWords(4,3);//O
		//gb.GetWords(5,4);//L
		//gb.GetWords(5,3);//U
		//gb.GetWords(5,0);//R
		//gb.GetWords(6,5);//E
		
		
//		gb.GetWords(9, 5);//T
//		gb.GetWords(10, 1);//E
//		gb.GetWords(10, 2);//D
//		gb.GetWords(10, 4);//A
//		gb.GetWords(11, 1);
//		gb.GetWords(11, 2);
//		gb.GetWords(11, 3);
//		gb.GetWords(11, 4);
		gb.SortResults();

	}
}
