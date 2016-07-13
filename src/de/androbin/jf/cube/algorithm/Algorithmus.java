package de.androbin.jf.cube.algorithm;

import static de.fridolin.jf.cube.representation.Util.*;
import de.fridolin.jf.cube.representation.*;
import java.util.*;
import java.util.concurrent.*;

public final class Algorithmus
{
	public static final Color	UP		= Color.white;
	public static final Color	DOWN	= Color.yellow;
										
	public static final Color[]	SIDES	= new Color[]
											{ Color.green, Color.red, Color.blue, Color.orange };
											
	private Algorithmus()
	{
	}
	
	public static boolean isEquals( final Color[] c1, final Color[] c2 )
	{
		if ( c1.length != c2.length )
		{
			return false;
		}
		
		for ( int i = 0; i < c1.length; i++ )
		{
			if ( c1[ i ] != c2[ i ] )
			{
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean isSameBrick( final Color[] c1, final Color[] c2 )
	{
		return isEquals( c1, c2 ) || isEquals( c1, new Color[]
		{ c2[ 1 ], c2[ 2 ], c2[ 0 ] } ) || isEquals( c1, new Color[]
		{ c2[ 2 ], c2[ 0 ], c2[ 1 ] } );
	}
	
	public static void main( final String[] args ) throws InterruptedException
	{
		final Zauberwuerfel cube = getStandardCube();
		final Random random = ThreadLocalRandom.current();
		
		shuffle( cube, random );
		cube.generateAnimationWindow();
		solve( cube );
	}
	
	public static void solve( final Cube cube )
	{
		Layer1.layer1( cube );
		Layer2.layer2( cube );
		Layer3.layer3( cube );
	}
}
