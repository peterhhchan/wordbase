import java.util.ArrayList;


public class GameBoardNode {

	private char character;
	private ArrayList<GameBoardNode> links; 
	private int row;
	private int col;
	
	public int GetRow() 
	{
		return row;
	}
	
	public void SetRow(int row) 
	{ 
		this.row = row;
	}
	
	public int GetCol() 
	{
		return col;
	}
	
	public void SetCol(int col) 
	{ 
		this.col = col;
	}
	
	
	public char GetCharacter() 
	{ 
		return character;
	}
	
	public void SetCharacter(char myCharacter) 
	{
		character = myCharacter;
	};
	
	public ArrayList<GameBoardNode> GetLinks() 
	{
		return links;
	}
	

	
	public GameBoardNode()
	{
		character = ' ';
		links = new ArrayList<GameBoardNode>();
	}
	
	public void AddLink(GameBoardNode node)
	{
		if (node != null)
		{
			links.add(node);
		}
	}
	
	
}
