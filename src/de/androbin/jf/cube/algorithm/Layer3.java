package de.androbin.jf.cube.algorithm;

import static de.androbin.jf.cube.algorithm.Algorithmus.*;
import de.fridolin.jf.cube.representation.*;

public final class Layer3
{
	private Layer3()
	{
	}
	
	public static void layer3( final Cube cube )
	{
		layer3edgePrepare( cube );
		Layer3EdgeSwap.layer3edgeSwap( cube );
		Layer3EdgeFlip.layer3edgeFlip( cube );
		layer3cornerSwap( cube );
		layer3cornerFlip( cube );
	}
	
	private static void layer3edgePrepare( final Cube cube )
	{
		final Color[] brick_c = new Color[]
		{ DOWN, SIDES[ 0 ] };
		
		Color[] pos_c = cube.searchBrick( brick_c );
		
		while ( pos_c[ 1 ] != SIDES[ 0 ] )
		{
			cube.rotate( DOWN, 1 );
			pos_c = cube.searchBrick( brick_c );
		}
	}
	
	public static void cornerSwap( final Cube cube, final Color side1, final Color side2 )
	{
		cube.rotate( side1, 1 );
		cube.rotate( DOWN, -1 );
		cube.rotate( side2, -1 );
		cube.rotate( DOWN, 1 );
		cube.rotate( side1, -1 );
		cube.rotate( DOWN, -1 );
		cube.rotate( side2, 1 );
		cube.rotate( DOWN, 1 );
	}
	
	private static void layer3cornerSwap( final Cube cube )
	{
		if ( !isSameBrick( new Color[]
		{ DOWN, SIDES[ 0 ], SIDES[ 1 ] }, cube.getBrickAtEdge( DOWN, SIDES[ 0 ], SIDES[ 1 ] ) ) )
		{
			/**/ if ( isSameBrick( new Color[]
			{ DOWN, SIDES[ 0 ], SIDES[ 1 ] }, cube.getBrickAtEdge( DOWN, SIDES[ 1 ], SIDES[ 2 ] ) ) )
			{
				cornerSwap( cube, SIDES[ 1 ], SIDES[ 3 ] );
			}
			else if ( isSameBrick( new Color[]
			{ DOWN, SIDES[ 0 ], SIDES[ 1 ] }, cube.getBrickAtEdge( DOWN, SIDES[ 2 ], SIDES[ 3 ] ) ) )
			{
				cornerSwap( cube, SIDES[ 0 ], SIDES[ 2 ] );
			}
			else if ( isSameBrick( new Color[]
			{ DOWN, SIDES[ 0 ], SIDES[ 1 ] }, cube.getBrickAtEdge( DOWN, SIDES[ 3 ], SIDES[ 0 ] ) ) )
			{
				cube.rotate( DOWN, -1 );
				cube.rotate( SIDES[ 3 ], -1 );
				cube.rotate( DOWN, 1 );
				cube.rotate( SIDES[ 1 ], 1 );
				cube.rotate( DOWN, -1 );
				cube.rotate( SIDES[ 3 ], 1 );
				cube.rotate( DOWN, 1 );
				cube.rotate( SIDES[ 1 ], -1 );
			}
		}
		
		for ( int i = 0; i < 2; i++ )
		{
			if ( isSameBrick( new Color[]
			{ DOWN, SIDES[ 1 ], SIDES[ 2 ] }, cube.getBrickAtEdge( DOWN, SIDES[ 1 ], SIDES[ 2 ] ) ) )
			{
				break;
			}
			else
			{
				cornerSwap( cube, SIDES[ 3 ], SIDES[ 1 ] );
			}
		}
	}
	
	private static void layer3cornerFlip( final Cube cube )
	{
		for ( int i = 0; i < SIDES.length; i++ )
		{
			final Color c = SIDES[ 0 ];
			final Color d = SIDES[ 1 ];
			final Color[] edge = cube.getBrickAtEdge( DOWN, c, d );
			int r = 0;
			
			/**/ if ( edge[ 1 ] == DOWN )
			{
				r = 4;
			}
			else if ( edge[ 2 ] == DOWN )
			{
				r = 2;
			}
			
			for ( int j = 0; j < r; j++ )
			{
				cube.rotate( c, 1 );
				cube.rotate( d, -1 );
				cube.rotate( c, -1 );
				cube.rotate( d, 1 );
			}
			
			cube.rotate( DOWN, -1 );
		}
	}
}