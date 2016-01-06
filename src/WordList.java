import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;


public class WordList {

	Trie myPrefixTrie = new Trie();
	Trie mySuffixTrie = new Trie();
	public WordList()
	{
		System.out.println(System.getProperty("user.dir"));
		String path = System.getProperty("user.dir");
		String fullPath = path + "\\sowpods.txt";
		//String triePath = path + "\\trie";
		myPrefixTrie = new Trie();
		mySuffixTrie = new Trie();
		try(BufferedReader br = new BufferedReader(new FileReader(fullPath))) {
			String word = br.readLine();

	        while (word != null) {
	            //System.out.println(word);
	            myPrefixTrie.addWord(word);
	            word = br.readLine();
	        }
	    }
		catch (Exception e)
		{
			
		}
	}
	
   public boolean isPrefix(String prefix)
   {
	   return myPrefixTrie.isPrefix(prefix);
   }
   
   public boolean isWord(String prefix)
   {
	   return myPrefixTrie.isWord(prefix);
   }
	public static void main(String [] args)
	{
		WordList wl = new WordList();
	}
}

