package de.androbin.jf.cube.algorithm;

import static de.androbin.jf.cube.algorithm.Algorithmus.*;
import de.fridolin.jf.cube.representation.*;

public final class Layer2
{
	private Layer2()
	{
	}
	
	public static void layer2( final Cube cube )
	{
		for ( int s = 0; s < SIDES.length; s++ )
		{
			final Color c = SIDES[ s ];
			final Color d = SIDES[ ( s + 1 ) % SIDES.length ];
			final Color[] brick = new Color[]
			{ c, d };
			
			Color[] pos = cube.searchBrick( brick );
			
			if ( !isEquals( pos, cube.getBrickAtBorder( pos[ 0 ], pos[ 1 ] ) ) )
			{
				if ( pos[ 0 ] != DOWN )
				{
					cube.rotate( pos[ 0 ], 1 );
					cube.rotate( DOWN, -1 );
					cube.rotate( pos[ 0 ], -1 );
					cube.rotate( DOWN, -1 );
					cube.rotate( pos[ 1 ], -1 );
					cube.rotate( DOWN, 1 );
					cube.rotate( pos[ 1 ], 1 );
				}
				
				pos = cube.searchBrick( brick );
				
				while ( pos[ 1 ] != cube.getBrickAtBorder( DOWN, pos[ 1 ] )[ 1 ] )
				{
					cube.rotate( DOWN, 1 );
					pos = cube.searchBrick( brick );
				}
				
				/**/ if ( cube.getBrickAtBorder( DOWN, pos[ 1 ] )[ 1 ] == c )
				{
					cube.rotate( DOWN, -1 );
					cube.rotate( d, -1 );
					cube.rotate( DOWN, 1 );
					cube.rotate( d, 1 );
					cube.rotate( DOWN, 1 );
					cube.rotate( c, 1 );
					cube.rotate( DOWN, -1 );
					cube.rotate( c, -1 );
				}
				else if ( cube.getBrickAtBorder( DOWN, pos[ 1 ] )[ 1 ] == d )
				{
					cube.rotate( DOWN, 1 );
					cube.rotate( c, 1 );
					cube.rotate( DOWN, -1 );
					cube.rotate( c, -1 );
					cube.rotate( DOWN, -1 );
					cube.rotate( d, -1 );
					cube.rotate( DOWN, 1 );
					cube.rotate( d, 1 );
				}
			}
		}
	}
}