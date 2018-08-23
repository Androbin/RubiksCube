package de.fridolin.jf.cube.representation;

import static de.fridolin.jf.cube.representation.Color.*;
import java.util.*;

public final class Util {
  public static final Color[] SIDES = {
      green, red, blue, orange, white, yellow };
  public static final int[] TURNS = { -1, 1, 2 };
  
  private Util() {
  }
  
  /**
   * Gibt einen "gelösten" Zauberwürfel zurück, der ein Fenster mit Animationen öffnet
   * 
   * @return
   */
  public static Zauberwuerfel getStandardCube() {
    final Stein[][][] wuerfel = {
        {
            {
                new Stein( new Color[] { none, red, none, yellow, green, none } ),
                new Stein( new Color[] { none, red, none, yellow, none, none } ),
                new Stein( new Color[] { none, red, none, yellow, none, blue } ),
            },
            {
                new Stein( new Color[] { none, red, none, none, green, none } ),
                new Stein( new Color[] { none, red, none, none, none, none } ),
                new Stein( new Color[] { none, red, none, none, none, blue } ),
            },
            {
                new Stein( new Color[] { none, red, white, none, green, none } ),
                new Stein( new Color[] { none, red, white, none, none, none } ),
                new Stein( new Color[] { none, red, white, none, none, blue } ),
            },
        },
        {
            {
                new Stein( new Color[] { none, none, none, yellow, green, none } ),
                new Stein( new Color[] { none, none, none, yellow, none, none } ),
                new Stein( new Color[] { none, none, none, yellow, none, blue } ),
            },
            {
                new Stein( new Color[] { none, none, none, none, green, none } ),
                new Stein( new Color[] { none, none, none, none, none, none } ),
                new Stein( new Color[] { none, none, none, none, none, blue } ),
            },
            {
                new Stein( new Color[] { none, none, white, none, green, none } ),
                new Stein( new Color[] { none, none, white, none, none, none } ),
                new Stein( new Color[] { none, none, white, none, none, blue } ),
            },
        },
        {
            {
                new Stein( new Color[] { orange, none, none, yellow, green, none } ),
                new Stein( new Color[] { orange, none, none, yellow, none, none } ),
                new Stein( new Color[] { orange, none, none, yellow, none, blue } ),
            },
            {
                new Stein( new Color[] { orange, none, none, none, green, none } ),
                new Stein( new Color[] { orange, none, none, none, none, none } ),
                new Stein( new Color[] { orange, none, none, none, none, blue } ),
            },
            {
                new Stein( new Color[] { orange, none, white, none, green, none } ),
                new Stein( new Color[] { orange, none, white, none, none, none } ),
                new Stein( new Color[] { orange, none, white, none, none, blue } ),
            },
        },
    };
    
    return new Zauberwuerfel( wuerfel );
  }
  
  /**
   * Gibt den x-Wert der Fläche zurück, die parallel zur yz-Ebene ist und eine der Farben farben hat
   * Das Array farben darf keine zwei Farben enthalten, die beide Flächen definieren, die parallel
   * zur
   * yz-Ebene sind
   * 
   * @param farben
   * @return
   */
  public static int getX( final Color[] farben ) {
    for ( final Color f : farben ) {
      if ( f == red ) {
        return -1;
      } else if ( f == orange ) {
        return 1;
      }
    }
    
    return 0;
  }
  
  /**
   * Gibt den y-Wert der Fläche zurück, die parallel zur xz-Ebene ist und eine der Farben farben hat
   * Das Array farben darf keine zwei Farben enthalten, die beide Flächen definieren, die parallel
   * zur
   * xz-Ebene sind
   * 
   * @param farben
   * @return
   */
  public static int getY( final Color[] farben ) {
    for ( final Color f : farben ) {
      if ( f == white ) {
        return 1;
      } else if ( f == yellow ) {
        return -1;
      }
    }
    
    return 0;
  }
  
  /**
   * Gibt die Farbe der Seite zurück, die parallel zur xz-Ebene ist und den y-Wert y hat
   * 
   * @param y
   * @return
   */
  public static Color getYColor( final int y ) {
    switch ( y ) {
      case -1:
        return yellow;
      case 0:
        return none;
      case 1:
        return white;
      default:
        throw new IllegalArgumentException();
    }
  }
  
  /**
   * Gibt den z-Wert der Fläche zurück, die parallel zur xy-Ebene ist und eine der Farben farben hat
   * Das Array farben darf keine zwei Farben enthalten, die beide Flächen definieren, die parallel
   * zur
   * xy-Ebene sind
   * 
   * @param farben
   * @return
   */
  public static int getZ( final Color[] farben ) {
    for ( final Color f : farben ) {
      if ( f == blue ) {
        return 1;
      } else if ( f == green ) {
        return -1;
      }
    }
    
    return 0;
  }
  
  public static void shuffle( final Cube cube, final Random random ) {
    for ( int i = 0; i < 20; i++ ) {
      final Color side = SIDES[ random.nextInt( SIDES.length ) ];
      final int turn = TURNS[ random.nextInt( TURNS.length ) ];
      cube.rotate( side, turn );
    }
  }
}