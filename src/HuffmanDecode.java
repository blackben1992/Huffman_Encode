import java.io.*;
import java.util.*;

public class HuffmanDecode {

	HuffmanTree decode;
	HuffmanInputStream h;
	String s;
	PrintWriter writer;
	int Byte, writtenChars;

	public HuffmanDecode( String in, String out ) 
	{
	//implements the Huffman Decode Algorithm
	//Add private methods and instance variables as needed
		
		
		
		h = new HuffmanInputStream( in );
		char ch = '€';
		//rebuild the tree
		decode = new HuffmanTree( h.getTree(), ch );
			
		//initialize the filewriter
		try
		{
			writer = new PrintWriter( out );
		
			while( writtenChars < h.totalChars() )
			{
				Byte = h.readBit();
				
				if( Byte == 49 )
				{
					decode.moveRight();
				}
				if( Byte == 48 )
				{
					decode.moveLeft();
				}
				if( decode.atLeaf() )
				{
					writer.print( decode.current() );
					decode.moveRoot();
					writtenChars++;
				}
			}
			writer.close();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}

	public static void main( String args[] ) 
	{
	//args[0] is the name of a file created by Huffman Encode
	//args[1] is the name of the output file for the uncompressed file
		//new HuffmanDecode( args [ 0 ], args[ 1 ] );
		new HuffmanDecode( "test", "finalTest" );
	}

}