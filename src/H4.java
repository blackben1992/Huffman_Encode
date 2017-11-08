

public class H4< T extends Comparable< ? super T > >
{
	//heap rule: the parent value(the key) is less than or equal to the children

	T keys[]; //The values used to order the heap
	Object data[]; //additional data associated with the keys
					//the data associated with the key in position i is stored in position i of data
	int maxChildren;
	int size;
	int test;
	
	public H4( int s, int m )
	{
		keys = ( T[] ) new Comparable[ s ]; //will generate a warning
		data = new Object[ s ];
		maxChildren = m;
		size = 0;
	}
	
	public static void main( String[] args )
	{
		H4 h = new H4( 100, 5 );
		Object o = new Object();
	
		for( int i = 10; i < 90; ++i )
		{
			h.insert( i, i );
		}
		System.out.println( h.getSize() );

		while( h.getSize() > 0 )
		{
			System.out.println( "Min key: " + " " +h.getMinKey() + " " + "Min data: " + " " + h.getMinData() );
			h.removeMin();
		}

	}
	//done
	public T getMinKey()
	{
		//PRE: !empty()
		//returns the smallest key value in the heap
		if( empty() )
		{
			System.out.println( "No keys present" );
			return null;
		}
		return keys[ 0 ];
	}
	//done
	public Object getMinData()
	{
		//PRE: !empty()
		//returns the data associated with the smallest key value in the heap
		//since keys might not be unique the data returned should be the data that would be 
		//removed if remove was called instead of getMinData
		if( empty() )
		{
			System.out.println( "Tree is empty, aborting." );
		}
		return data[ 0 ];
	}
	
	public void removeMin()
	{
		//PRE: !empty()
		//removes smallest key and associated data from the heap
		if( empty() )
		{
			System.out.println( "Tree is empty, aborting." );
		}
		
		int child, parent;
		T end = keys[ size - 1 ];
		keys[ size ] = null;
		Object dataEnd = data[ size - 1 ];
		data[ size ] = null;
		size--;
		parent = 0;
		child = 1;
		while( child < size )
		{
			parent = ( (child - 1 )/ maxChildren );
			for( int i = 1; i != maxChildren + 1; ++i )
			{
				if( ( ( maxChildren * parent ) + i ) > size )
					break;
				if( child < size && keys[ ( maxChildren * parent ) + i ].compareTo( keys[ child ] ) < 0 )
				{
					child = ( maxChildren * parent ) + i;
				}
			}
			if( end.compareTo( keys[ child ] ) < 0 )
				break;
			else
			{
				keys[ parent ] = keys[ child ];
				data[ parent ] = data[ child ];
				child = ( maxChildren * child ) + 1;
			}
		}
		keys[ ( child - 1 ) / maxChildren ] = end;
		data[ ( child - 1 ) / maxChildren ] = dataEnd;
	}
	
	public void insert( T k, Object d )
	{
		//PRE: !full()
		//inserts a new key and associated data into the heap
		if( full() )
		{
			System.out.println( "Tree is full, aborting." );
			return;
		}
		if( size == 0 && empty() )
		{
			keys[ 0 ] = k;
			data[ 0 ] = d;
			size++;
			//System.out.println( size );
			return;
		}
		int parent, child;
	
		size++;
		//System.out.println( size );
		child = size - 1;
		parent = ( child - 1 ) / maxChildren;
		while( child > 0 && keys[ parent ].compareTo( k ) > 0 )
		{
			keys[ child ] = keys[ parent ];
			data[ child ] = data[ parent ];
			child = parent;
			parent = ( child - 1 ) / maxChildren;
		}
		keys[ child ] = k;
		data[ child ] = d;
	}
	
	public boolean empty()
	{
		//return true when the list is empty
		for( int i = 0; i != keys.length; ++i )
		{
			if( keys[ i ] != null )
				return false;
		}
		return true;
	}
	public boolean full()
	{
		//return true when the list is full
		for( int i = 0; i != keys.length; ++i )
		{
			if( keys[ i ] == null )
				return false;
		}
		return true;
	}
	
	public int getSize()
	{
		//return the number of elements in the heap
		return size;
	}
	
	private void debug()
	{
		System.out.println( "You are here." );
	}

}
