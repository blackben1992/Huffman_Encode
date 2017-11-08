import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class HuffmanTree 
{
	
	private class Node 
	{
		private Node left;
		private char data;
		private Node right;

		private Node( Node L, char d, Node R ) 
		{
			left = L;
			data = d;
			right = R;
		}

	}

	private Node root;
	private Node current; //this value is changed by the move methods
	char ch = 128;

	private String postOrderReturnAux( Node r, String s )
	{
	
		if( r.left == null && r.right == null )
		{
			return s + Character.toString( r.data );
		}
		
		if( r.left != null )
			s = postOrderReturnAux( r.left, s );
		if( r.right != null )
			s = postOrderReturnAux( r.right, s ) + "€";
		
		return s;
	}
	
	public HuffmanTree() 
	{
		root = null;
		current = null;
	}

	public HuffmanTree( char d ) 
	{
	//makes a single node tree
		Node n = new Node( null, d, null );
		root = n;
		current = n;
	}

	public HuffmanTree( String t, char nonLeaf ) 
	{
	//Assumes t represents a post order representation of the tree as discussed in class
	//nonLeaf is the char value of the data in the non-leaf nodes
	//converts a post-fix expression into a tree, where the passed in string is the post-fix expression
	//rebuilds Huffman tree
		Stack< Node > operands = new Stack<>();
		Node x;
		Node y;
		for( int i = 0; i != t.length(); ++i )
		{
			if( t.charAt( i ) == nonLeaf )
			{
				x = operands.pop();
				y = operands.pop();
				operands.push( new Node( y, t.charAt( i ), x ) );
			}
			if( t.charAt( i ) > 0 && t.charAt( i ) < 128 )
			{
				operands.push( new Node( null, t.charAt( i), null ) );
			}	
		}
		root = operands.pop();
		current = root;
	}

	public HuffmanTree( HuffmanTree b1, HuffmanTree b2, char d ) 
	{
	//makes a new tree where b1 is the left subtree and b2 is the right subtree
	//d is the data in the root
		Node n = new Node( b1.root, d, b2.root );
		root = n;
		current = n;
	}

	//use the methods moveRoot through current to help with the decoding process
	//the move methods change the value of current

	public void moveRoot() 
	{
		current = root;
	}

	public void moveLeft() 
	{
		current = current.left;
	}

	public void moveRight() 
	{
		current = current.right;
	}

	public boolean atLeaf() 
	{
	//returns true if current references a leaf other wise returns false
		if( current.right == null && current.left == null )
			return true;
		else
			return false;
	}

	public char current() 
	{
	//returns the data value in the node referenced by current
		return current.data;
	}
	

	//use this in the encoding process
	//the iterator returns the path (a series of 0s and 1s) to each leaf
	//it will be easier to construct all the paths when the iterator is created
	//add private methods and variables as needed
	public class PathIterator implements Iterator<String>
	{
		private ArrayList< String > pathToLeaf = new ArrayList< String >();
		int count = 0;
		int hasNextCounter = 0;

		public PathIterator() 
		{
			//call recursive method
			getPath( root, "" );
		}

		public boolean hasNext() 
		{
			//used with the path of 1's and 0's built
			if( hasNextCounter < pathToLeaf.size() )
			{
				hasNextCounter++;
				return true;
			}
				
			
//			if( hasNextCounter == pathToLeaf.size() )
//				return false;
			return false;
		}

		public String next() 
		{
			String s = pathToLeaf.get( count );
			count++;
			return s;
		}

		public void remove() 
		{
		//optional method not implemented
		}
		
		public void getPath( Node r, String s )
		{
			if( r.left == null && r.right == null )
			{
				pathToLeaf.add( r.data + s );
				return;
			}
			
			if( r.left != null )
				getPath( r.left, s + "0" );
			if( r.right != null )
				getPath( r.right, s + "1" );
		}
	}

	public Iterator<String> iterator() 
	{
	//return a new iterator object
		PathIterator iterator = new PathIterator();
		return iterator;
	}

	public String toString() 
	{
	//returns a string representation of the tree using the format discussed in class
		return null;
	}
	
	public String postOrderReturn()
	{
		String s = postOrderReturnAux( root, "" );
		//System.out.println( s );
		return s;
	}

}

