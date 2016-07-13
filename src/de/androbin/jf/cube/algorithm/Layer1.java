package de.androbin.jf.cube.algorithm;

import static de.androbin.jf.cube.algorithm.Algorithmus.*;
import de.fridolin.jf.cube.representation.*;

public final class Layer1
{
	private Layer1()
	{
	}
	
	public static void layer1( final Cube cube )
	{
		layer1edge( cube );
		layer1corner( cube );
	}
	
	private static void layer1edge( final Cube cube )
	{
		for ( final Color c : SIDES )
		{
			final Color[] brick = new Color[]
			{ UP, c };
			
			if ( !isEquals( cube.getBrickAtBorder( UP, c ), brick ) )
			{
				Color[] pos = cube.searchBrick( brick );
				
				/**/ if ( pos[ 0 ] == UP )
				{
					cube.rotate( pos[ 1 ], 2 );
				}
				else if ( pos[ 0 ] != DOWN )
				{
					cube.rotate( pos[ 0 ], 1 );
					cube.rotate( DOWN, 1 );
					cube.rotate( pos[ 0 ], -1 );
				}
				
				pos = cube.searchBrick( brick );
				
				while ( pos[ 1 ] != c )
				{
					cube.rotate( DOWN, 1 );
					pos = cube.searchBrick( brick );
				}
				
				if ( pos[ 1 ] == cube.getBrickAtBorder( DOWN, c )[ 1 ] )
				{
					cube.rotate( pos[ 1 ], 2 );
				}
				else
				{
					cube.rotate( DOWN, 1 );
					pos = cube.searchBrick( brick );
					cube.rotate( pos[ 1 ], 1 );
					cube.rotate( c, -1 );
					cube.rotate( pos[ 1 ], -1 );
				}
			}
		}
	}
	
	private static void layer1corner( final Cube cube )
	{
		for ( int s = 0; s < SIDES.length; s++ )
		{
			final Color c = SIDES[ s ];
			final Color d = SIDES[ ( s + 1 ) % SIDES.length ];
			
			final Color[] brick = new Color[]
			{ UP, c, d };
			
			final Color[] edge = cube.getBrickAtEdge( UP, c, d );
			
			if ( isSameBrick( brick, edge ) )
			{
				layer1corner1( cube, c, d, edge );
			}
			else
			{
				layer1corner2( cube, c, d, brick );
			}
		}
	}
	
	private static void layer1corner1( final Cube cube, final Color c, final Color d, final Color[] edge )
	{
		/**/ if ( edge[ 1 ] == UP )
		{
			cube.rotate( c, 1 );
			cube.rotate( DOWN, 1 );
			cube.rotate( c, -1 );
			cube.rotate( DOWN, 2 );
			cube.rotate( d, -1 );
			cube.rotate( DOWN, 1 );
			cube.rotate( d, 1 );
		}
		else if ( edge[ 2 ] == UP )
		{
			cube.rotate( d, -1 );
			cube.rotate( DOWN, -1 );
			cube.rotate( d, 1 );
			cube.rotate( DOWN, 2 );
			cube.rotate( c, 1 );
			cube.rotate( DOWN, -1 );
			cube.rotate( c, -1 );
		}
	}
	
	private static void layer1corner2( final Cube cube, final Color c, final Color d, final Color[] brick )
	{
		Color[] pos = cube.searchBrick( brick );
		
		if ( pos[ 0 ] == UP )
		{
			cube.rotate( pos[ 1 ], 1 );
			cube.rotate( DOWN, 1 );
			cube.rotate( pos[ 1 ], -1 );
		}
		
		pos = cube.searchBrick( brick );
		
		while ( pos[ 1 ] != c )
		{
			cube.rotate( DOWN, 1 );
			pos = cube.searchBrick( brick );
		}
		
		final Color[] edge = cube.getBrickAtEdge( pos[ 0 ], pos[ 1 ], pos[ 2 ] );
		
		if ( edge[ 0 ] == UP )
		{
			cube.rotate( c, 1 );
			cube.rotate( DOWN, 2 );
			cube.rotate( c, -1 );
			cube.rotate( DOWN, 2 );
			cube.rotate( d, -1 );
			cube.rotate( DOWN, 1 );
			cube.rotate( d, 1 );
		}
		else if ( edge[ 1 ] == UP )
		{
			cube.rotate( DOWN, -1 );
			cube.rotate( d, -1 );
			cube.rotate( DOWN, 1 );
			cube.rotate( d, 1 );
		}
		else if ( edge[ 2 ] == UP )
		{
			cube.rotate( DOWN, 1 );
			cube.rotate( c, 1 );
			cube.rotate( DOWN, -1 );
			cube.rotate( c, -1 );
		}
	}
}