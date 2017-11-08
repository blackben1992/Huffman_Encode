import java.io.*;
import java.util.*;

public class HuffmanEncode 
{
	int[] frequency = new int[ 128 ];
	String[] dataStore = new String[ 128 ];
	String[] values;
	H4 h;
	HuffmanTree h1, h2, combo, lastTree;
	int freq1, freq2, totalChars = 0;
	HuffmanTree.PathIterator iterator;
	HuffmanOutputStream outStream;
	String postOrderTraversal;

	public HuffmanEncode( String in, String out ) 
	{
	//Implements the huffman encoding algorithm
	//Add private methods and instance variables as needed
		try
		{
			BufferedReader readMe = new BufferedReader( new FileReader( in ) );
			int c = readMe.read();
			while( c != -1 )
			{
				frequency[ c ]++;
				c = readMe.read();
				totalChars++;
			}
			readMe.close();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		h = new H4( 128, 4 );
		for( int i = 0; i != frequency.length; ++i )
		{
			if( frequency[ i ] > 0 )
			{
				h.insert( frequency[ i ], new HuffmanTree( ( char )i ) );
			}
		}
		
		while( h.getSize() > 1 )
		{
			h1 = ( HuffmanTree )h.getMinData();
			freq1 = ( int )h.getMinKey();
			h.removeMin();
			h2 = ( HuffmanTree )h.getMinData();
			freq2 = ( int )h.getMinKey();
			h.removeMin();
			char c = 128;
			combo = new HuffmanTree( h1, h2, c );
			h.insert( freq1 + freq2, combo  );
		}
	
		if( h.getSize() == 1 )
		{
			//System.out.println( "Size is 1" );
			lastTree = ( HuffmanTree )h.getMinData();
		}
		
		Iterator< String > p = lastTree.iterator();
		postOrderTraversal = lastTree.postOrderReturn();
		String s;
		
		while( p.hasNext() )
		{
			s = p.next();
			String character = s.substring( 0, 1 );
			char c = character.charAt( 0 );
			String split = s.substring( 1 );
			dataStore[ c ] = split;
		}
		
		//Initialize outputstream object with post fix expression and totalchars
		outStream = new HuffmanOutputStream( out, postOrderTraversal, totalChars );
		try
		{
			BufferedReader b = new BufferedReader( new FileReader( in ) );
			int e = b.read();
			while( e != -1 )
			{
				if( dataStore[ e ] != null )
				{
					String chicago = dataStore[ e ];
					//System.out.println( chicago );
					char[] cArray = chicago.toCharArray();
					for( char c : cArray )
					{
						int car = Character.getNumericValue( c );
						outStream.writeBit( car );
					}
				}
				e = b.read();
			}
			b.close();
			outStream.close();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}

	public static void main( String args[] ) 
	{
	//args[0] is the name of the file whose contents should be compressed
	//args[1] is the name of the output file that will hold the compressed content if the input file
		//new HuffmanEncode( args[ 0 ], args[ 1 ] );
		new HuffmanEncode( "1399A.txt", "test" );
	}
}

		
			
		

	

