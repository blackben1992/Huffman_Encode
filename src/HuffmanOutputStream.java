import java.io.*;
public class HuffmanOutputStream extends BitOutputStream 
{
	static String s = new String( "Hello" );
	String write = new String("");
	int count = 0, writeByte = 0;

	public HuffmanOutputStream( String filename, String tree, int totalChars ) {
	//add additional privates variables and methods as needed
		super( filename );
		try 
		{
			d.writeUTF( tree );
			d.writeInt( totalChars );
			//System.out.println( totalChars );
		}
		catch ( IOException e ) 
		{
			e.printStackTrace();
		}
	}


	public void writeBit( int bit ) 
	{
	//PRE bit == 0 || bit == 1
	//Writes a bit to the file
	//Bits must be packed 8 to a byte before writing to the file
	//counter to check how many times method has been called
		//System.out.println( bit );
		writeByte++;
		if( bit == 0 )
		{
			count = count * 2;
			//System.out.println( count + " " + writeByte );
		}
		else
		{
			count = ( count * 2 ) + 1;
			//System.out.println( count + " " + writeByte );
		}
		if( writeByte == 8 )
		{
			//write out byte and reset
			try
			{
				d.writeByte( count );
			}
			catch ( IOException e )
			{
				e.printStackTrace();
			}
			writeByte = 0;
			count = 0;
		}
	}

	public void close() 
	{
		while( count < 8 )
		{
			writeByte = writeByte * 2;
			count++;
		}
		try
		{
			d.writeByte( writeByte );
			d.close();
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
	}
}