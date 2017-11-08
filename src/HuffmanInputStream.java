import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class HuffmanInputStream extends BitInputStream {

	private String tree, byt;
	private ArrayList<Integer> storeArray = new ArrayList<>();
	private int totalChars, position = 0, output, b = 0;
	
	public HuffmanInputStream( String filename ) 
	{
	//add additional private variables and methods as needed
		super( filename );
		
		try 
		{
			tree = d.readUTF();
			totalChars = d.readInt();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}


	public int readBit() {
	//returns the next bit in the file 
		
		if( position == 0 )
		{
			try
			{
				byt = Integer.toBinaryString( d.readUnsignedByte() );
				//System.out.println( byt );
				while( byt.length() < 8 )
				{
					byt = '0' + byt;
				}
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}
		}
		
		b = byt.charAt( position );
		position++;
		if( position == 8 )
		{
			position = 0;
		}
		
		return b;
		
//		int read, bitCount;
//		
//		try
//		{
//			if( offOne == 0 )
//			{
//				read = d.readUnsignedByte();
//				System.out.println( read );
//				while( read != 0 )
//				{
//					bitCount = read % 2;
//					read = read / 2;
//					storeArray.add( bitCount );
//				}
//				storeArray.add( 0 );
//				Collections.reverse( storeArray );
//				offOne++;
//			}
//			else
//			{
//				count++;
//				return storeArray.get( count );
//			}
//			return storeArray.get( 0 );
//		}
//		catch ( IOException e )
//		{
//			e.printStackTrace();
//		}
//		return 0;
	}

	public String getTree() 
	{ 
		return tree;
	}

	public int totalChars() { 
		return totalChars;
	}

	public void close() 
	{
		
	}
}