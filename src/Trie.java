import java.util.ArrayList;
import java.util.List;

public class Trie
{
   private TrieNode root;
   
   /**
    * Constructor
    */
   public Trie()
   {
      root = new TrieNode();
   }
   
   /**
    * Adds a word to the Trie
    * @param word
    */
   public void addWord(String word)
   {
	  if (word.isEmpty() == true)
	  {
		  return;
	  }
      root.addWord(word.toLowerCase());
   }
   
   
   public boolean isPrefix(String prefix)
   {
      TrieNode lastNode = root;
      for (int i=0; i<prefix.length(); i++)
      {
    	  lastNode = lastNode.getNode(prefix.charAt(i));
    	  if (lastNode == null)
    	  {
    		  return false;
    	  }
      }
      return true;
   }
   
   public boolean isWord(String prefix)
   {
      TrieNode lastNode = root;
      for (int i=0; i<prefix.length(); i++)
      {
    	  lastNode = lastNode.getNode(prefix.charAt(i));
    	  if (lastNode == null)
    	  {
    		  return false;
    	  }
      }
      return lastNode.isWord();
   }
}