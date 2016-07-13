package de.androbin.jf.cube.algorithm;

import static de.androbin.jf.cube.algorithm.Algorithmus.*;
import de.fridolin.jf.cube.representation.*;

public final class Layer3EdgeSwap
{
	private Layer3EdgeSwap()
	{
	}
	
	public static void layer3edgeSwap( final Cube cube )
	{
		final Color[] brick_b = cube.getBrickAtBorder( DOWN, SIDES[ 1 ] );
		final Color[] brick_h = cube.getBrickAtBorder( DOWN, SIDES[ 3 ] );
		
		/**/ if ( SIDES[ 2 ] == brick_b[ 0 ] || SIDES[ 2 ] == brick_b[ 1 ] )
		{
			layer3edgeSwap1( cube, brick_h );
		}
		else if ( SIDES[ 2 ] == brick_h[ 0 ] || SIDES[ 2 ] == brick_h[ 1 ] )
		{
			layer3edgeSwap2( cube, brick_b );
		}
		else if ( SIDES[ 1 ] != brick_b[ 0 ] && SIDES[ 1 ] != brick_b[ 1 ] )
		{
			layer3edgeSwap3( cube );
		}
	}
	
	public static void layer3edgeSwap( final Cube cube, final Color side1, final Color side2 )
	{
		cube.rotate( DOWN, 1 );
		cube.rotate( side1, 1 );
		cube.rotate( side2, 1 );
		cube.rotate( DOWN, 1 );
		cube.rotate( side2, -1 );
		cube.rotate( DOWN, -1 );
		cube.rotate( side1, -1 );
	}
	
	private static void layer3edgeSwap1( final Cube cube, final Color[] brick_h )
	{
		if ( SIDES[ 3 ] != brick_h[ 0 ] && SIDES[ 3 ] != brick_h[ 1 ] )
		{
			layer3edgeSwap( cube, SIDES[ 2 ], SIDES[ 1 ] );
		}
		
		layer3edgeSwap( cube, SIDES[ 1 ], SIDES[ 0 ] );
	}
	
	private static void layer3edgeSwap2( final Cube cube, final Color[] brick_b )
	{
		if ( SIDES[ 1 ] != brick_b[ 0 ] && SIDES[ 1 ] != brick_b[ 1 ] )
		{
			layer3edgeSwap( cube, SIDES[ 1 ], SIDES[ 0 ] );
		}
		
		layer3edgeSwap( cube, SIDES[ 2 ], SIDES[ 1 ] );
	}
	
	private static void layer3edgeSwap3( final Cube cube )
	{
		layer3edgeSwap( cube, SIDES[ 1 ], SIDES[ 0 ] );
		layer3edgeSwap( cube, SIDES[ 2 ], SIDES[ 1 ] );
		layer3edgeSwap( cube, SIDES[ 1 ], SIDES[ 0 ] );
	}
}