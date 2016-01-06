import java.util.Comparator;

class ResultsComparatorHigh implements Comparator<ResultObject>
{
	public int compare(ResultObject r1, ResultObject r2)
	{
		if (r1.row < r2.row)
		{
			return 1;
		}
		if (r1.row > r2.row)
		{
			return -1;
		}
		return r1.word.length() - r2.word.length();
	}
}

class ResultsComparatorLow implements Comparator<ResultObject>
{
	public int compare(ResultObject r1, ResultObject r2)
	{
		if (r1.row > r2.row)
		{
			return 1;
		}
		if (r1.row < r2.row)
		{
			return -1;
		}
		return r1.word.length() - r2.word.length();
	}
}

public class ResultObject {

	String word;
	int row;
	
	public ResultObject(String word, int row)
	{
		this.word = word;
		this.row = row;
	}
}

