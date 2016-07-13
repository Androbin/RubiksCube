package de.fridolin.jf.cube.representation;

import java.util.*;

public final class Util
{
	public static final Color[] SIDES = new Color[]
	{ Color.green, Color.red, Color.blue, Color.orange, Color.white, Color.yellow };
	
	private Util()
	{
	}
	
	/**
	 * Gibt einen "gel�sten" Zauberw�rfel zur�ck, der ein Fenster mit Animationen �ffnet
	 * 
	 * @return
	 */
	public static Zauberwuerfel getStandardCube()
	{
		final Stein[][][] wuerfel =
		{
			{
				{
					new Stein( new Color[]
						{ Color.none, Color.red, Color.none, Color.yellow, Color.green, Color.none } ),
					new Stein( new Color[]
						{ Color.none, Color.red, Color.none, Color.yellow, Color.none, Color.none } ),
					new Stein( new Color[]
						{ Color.none, Color.red, Color.none, Color.yellow, Color.none, Color.blue } ),
				},
				{
					new Stein( new Color[]
						{ Color.none, Color.red, Color.none, Color.none, Color.green, Color.none } ),
					new Stein( new Color[]
						{ Color.none, Color.red, Color.none, Color.none, Color.none, Color.none } ),
					new Stein( new Color[]
						{ Color.none, Color.red, Color.none, Color.none, Color.none, Color.blue } ),
				},
				{
					new Stein( new Color[]
						{ Color.none, Color.red, Color.white, Color.none, Color.green, Color.none } ),
					new Stein( new Color[]
						{ Color.none, Color.red, Color.white, Color.none, Color.none, Color.none } ),
					new Stein( new Color[]
						{ Color.none, Color.red, Color.white, Color.none, Color.none, Color.blue } ),
				},
			},
			{
				{
					new Stein( new Color[]
						{ Color.none, Color.none, Color.none, Color.yellow, Color.green, Color.none } ),
					new Stein( new Color[]
						{ Color.none, Color.none, Color.none, Color.yellow, Color.none, Color.none } ),
					new Stein( new Color[]
						{ Color.none, Color.none, Color.none, Color.yellow, Color.none, Color.blue } ),
				},
				{
					new Stein( new Color[]
						{ Color.none, Color.none, Color.none, Color.none, Color.green, Color.none } ),
					new Stein( new Color[]
						{ Color.none, Color.none, Color.none, Color.none, Color.none, Color.none } ),
					new Stein( new Color[]
						{ Color.none, Color.none, Color.none, Color.none, Color.none, Color.blue } ),
				},
				{
					new Stein( new Color[]
						{ Color.none, Color.none, Color.white, Color.none, Color.green, Color.none } ),
					new Stein( new Color[]
						{ Color.none, Color.none, Color.white, Color.none, Color.none, Color.none } ),
					new Stein( new Color[]
						{ Color.none, Color.none, Color.white, Color.none, Color.none, Color.blue } ),
				},
			},
			{
				{
					new Stein( new Color[]
						{ Color.orange, Color.none, Color.none, Color.yellow, Color.green, Color.none } ),
					new Stein( new Color[]
						{ Color.orange, Color.none, Color.none, Color.yellow, Color.none, Color.none } ),
					new Stein( new Color[]
						{ Color.orange, Color.none, Color.none, Color.yellow, Color.none, Color.blue } ),
				},
				{
					new Stein( new Color[]
						{ Color.orange, Color.none, Color.none, Color.none, Color.green, Color.none } ),
					new Stein( new Color[]
						{ Color.orange, Color.none, Color.none, Color.none, Color.none, Color.none } ),
					new Stein( new Color[]
						{ Color.orange, Color.none, Color.none, Color.none, Color.none, Color.blue } ),
				},
				{
					new Stein( new Color[]
						{ Color.orange, Color.none, Color.white, Color.none, Color.green, Color.none } ),
					new Stein( new Color[]
						{ Color.orange, Color.none, Color.white, Color.none, Color.none, Color.none } ),
					new Stein( new Color[]
						{ Color.orange, Color.none, Color.white, Color.none, Color.none, Color.blue } ),
				},
			},
		};
		
		return new Zauberwuerfel( wuerfel );
	}
	
	/**
	 * Gibt den x-Wert der Fl�che zur�ck, die parallel zur yz-Ebene ist und eine der Farben farben hat
	 * Das Array farben darf keine zwei Farben enthalten, die beide Fl�chen definieren, die parallel zur
	 * yz-Ebene sind
	 * 
	 * @param farben
	 * @return
	 */
	public static int getX( final Color[] farben )
	{
		for ( final Color f : farben )
		{
			if ( f == Color.red )
			{
				return -1;
			}
			else if ( f == Color.orange )
			{
				return 1;
			}
		}
		
		return 0;
	}
	
	/**
	 * Gibt den y-Wert der Fl�che zur�ck, die parallel zur xz-Ebene ist und eine der Farben farben hat
	 * Das Array farben darf keine zwei Farben enthalten, die beide Fl�chen definieren, die parallel zur
	 * xz-Ebene sind
	 * 
	 * @param farben
	 * @return
	 */
	public static int getY( final Color[] farben )
	{
		for ( final Color f : farben )
		{
			if ( f == Color.white )
			{
				return 1;
			}
			else if ( f == Color.yellow )
			{
				return -1;
			}
		}
		
		return 0;
	}
	
	/**
	 * Gibt die Farbe der Seite zur�ck, die parallel zur xz-Ebene ist und den y-Wert y hat
	 * 
	 * @param y
	 * @return
	 */
	public static Color getYColor( final int y )
	{
		switch ( y )
		{
			case -1 :
				return Color.yellow;
			case 0 :
				return Color.none;
			case 1 :
				return Color.white;
			default :
				throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Gibt den z-Wert der Fl�che zur�ck, die parallel zur xy-Ebene ist und eine der Farben farben hat
	 * Das Array farben darf keine zwei Farben enthalten, die beide Fl�chen definieren, die parallel zur
	 * xy-Ebene sind
	 * 
	 * @param farben
	 * @return
	 */
	public static int getZ( final Color[] farben )
	{
		for ( final Color f : farben )
		{
			if ( f == Color.blue )
			{
				return 1;
			}
			else if ( f == Color.green )
			{
				return -1;
			}
		}
		
		return 0;
	}
	
	public static void shuffle( final Cube cube, final Random random )
	{	
		for ( int i = 0; i < 20; i++ )
		{
			cube.rotate( SIDES[ random.nextInt( SIDES.length ) ], random.nextInt( 3 ) + 1 );
		}
	}
}