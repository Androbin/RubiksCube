package de.fridolin.jf.cube.representation;

import static de.fridolin.jf.cube.representation.Util.*;
import java.util.*;

/**
 * Der Würfel ist so gedreht, dass die mittleren Würfel jeder Seite folgendermaßen angeordnet sind:
 * oben: weiß
 * vorne: grün
 * rechts: rot
 * hinten: blau
 * links: orange
 * unten: gelb
 * x, y, z
 */
public final class Zauberwuerfel implements Cube {
  private final Stein[][][] wuerfel;
  private Fenster3D fenster;
  
  private static final int[][] DREHBEWEGUNG_ECKE = {
      { 0, 2 },
      { 0, 0 },
      { 2, 0 },
      { 2, 2 },
  };
  
  private static final int[][] DREHBEWEGUNG_RAND = {
      { 1, 2 },
      { 0, 1 },
      { 1, 0 },
      { 2, 1 },
  };
  
  @ Override
  public void rotate( final Color c, final int drehung ) {
    if ( c == null ) {
      return;
    }
    
    switch ( c ) {
      case white:
        this.rotateY( drehung, 1 );
        break;
      case yellow:
        this.rotateY( drehung, -1 );
        break;
      case green:
        this.rotateZ( drehung, -1 );
        break;
      case blue:
        this.rotateZ( drehung, 1 );
        break;
      case red:
        this.rotateX( drehung, -1 );
        break;
      case orange:
        this.rotateX( drehung, 1 );
        break;
      case none:
        break;
    }
  }
  
  /**
   * Rotiert eine Seite um die x-Achse
   * 
   * @param drehung
   * @param x
   */
  private void rotateX( final int drehung, final int x ) {
    for ( int i = 0; i < Math.abs( drehung ); i++ ) {
      final boolean imUhrzeigersinn = x > 0 ^ drehung > 0;
      
      if ( fenster != null ) {
        fenster.drehenX( wuerfel, x, imUhrzeigersinn );
      }
      
      for ( int e = imUhrzeigersinn ? 2 : 0; e < 3; e++ ) {
        Stein zStein1 = wuerfel[ x + 1 ][ 2 ][ 2 ];
        Stein zStein2 = null;
        // Ecksteine
        for ( int j = 0; j < 4; j++ ) {
          zStein1.rotX( false );
          zStein2 = wuerfel[ x + 1 ][ DREHBEWEGUNG_ECKE[ j ][ 0 ] ][ DREHBEWEGUNG_ECKE[ j ][ 1 ] ];
          wuerfel[ x + 1 ][ DREHBEWEGUNG_ECKE[ j ][ 0 ] ][ DREHBEWEGUNG_ECKE[ j ][ 1 ] ] = zStein1;
          zStein1 = zStein2;
        }
        zStein1 = wuerfel[ x + 1 ][ 2 ][ 1 ];
        // Randsteine
        for ( int j = 0; j < 4; j++ ) {
          zStein1.rotX( false );
          zStein2 = wuerfel[ x + 1 ][ DREHBEWEGUNG_RAND[ j ][ 0 ] ][ DREHBEWEGUNG_RAND[ j ][ 1 ] ];
          wuerfel[ x + 1 ][ DREHBEWEGUNG_RAND[ j ][ 0 ] ][ DREHBEWEGUNG_RAND[ j ][ 1 ] ] = zStein1;
          zStein1 = zStein2;
        }
      }
    }
  }
  
  /**
   * Rotiert eine Seite um die y-Achse
   * 
   * @param drehung
   * @param y
   */
  private void rotateY( final int drehung, final int y ) {
    for ( int i = 0; i < Math.abs( drehung ); i++ ) {
      final boolean imUhrzeigersinn = y > 0 ^ drehung < 0;
      
      if ( fenster != null ) {
        fenster.drehenY( wuerfel, y, imUhrzeigersinn );
      }
      
      for ( int e = imUhrzeigersinn ? 2 : 0; e < 3; e++ ) {
        Stein zStein1 = wuerfel[ 2 ][ y + 1 ][ 2 ];
        Stein zStein2 = null;
        // Ecksteine
        for ( int j = 0; j < 4; j++ ) {
          zStein1.rotY( false );
          zStein2 = wuerfel[ DREHBEWEGUNG_ECKE[ j ][ 0 ] ][ y + 1 ][ DREHBEWEGUNG_ECKE[ j ][ 1 ] ];
          wuerfel[ DREHBEWEGUNG_ECKE[ j ][ 0 ] ][ y + 1 ][ DREHBEWEGUNG_ECKE[ j ][ 1 ] ] = zStein1;
          zStein1 = zStein2;
        }
        zStein1 = wuerfel[ 2 ][ y + 1 ][ 1 ];
        // Randsteine
        for ( int j = 0; j < 4; j++ ) {
          zStein1.rotY( false );
          zStein2 = wuerfel[ DREHBEWEGUNG_RAND[ j ][ 0 ] ][ y + 1 ][ DREHBEWEGUNG_RAND[ j ][ 1 ] ];
          wuerfel[ DREHBEWEGUNG_RAND[ j ][ 0 ] ][ y + 1 ][ DREHBEWEGUNG_RAND[ j ][ 1 ] ] = zStein1;
          zStein1 = zStein2;
        }
      }
    }
  }
  
  /**
   * Rotiert eine Seite um die z-Achse
   * 
   * @param drehung
   * @param z
   */
  private void rotateZ( final int drehung, final int z ) {
    for ( int i = 0; i < Math.abs( drehung ); i++ ) {
      final boolean imUhrzeigersinn = z > 0 ^ drehung > 0;
      
      if ( fenster != null ) {
        fenster.drehenZ( wuerfel, z, imUhrzeigersinn );
      }
      
      for ( int e = imUhrzeigersinn ? 2 : 0; e < 3; e++ ) {
        Stein zStein1 = wuerfel[ 2 ][ 2 ][ z + 1 ];
        Stein zStein2 = null;
        // Ecksteine
        for ( int j = 0; j < 4; j++ ) {
          zStein1.rotZ( false );
          zStein2 = wuerfel[ DREHBEWEGUNG_ECKE[ j ][ 0 ] ][ DREHBEWEGUNG_ECKE[ j ][ 1 ] ][ z + 1 ];
          wuerfel[ DREHBEWEGUNG_ECKE[ j ][ 0 ] ][ DREHBEWEGUNG_ECKE[ j ][ 1 ] ][ z + 1 ] = zStein1;
          zStein1 = zStein2;
        }
        zStein1 = wuerfel[ 2 ][ 1 ][ z + 1 ];
        // Randsteine
        for ( int j = 0; j < 4; j++ ) {
          zStein1.rotZ( false );
          zStein2 = wuerfel[ DREHBEWEGUNG_RAND[ j ][ 0 ] ][ DREHBEWEGUNG_RAND[ j ][ 1 ] ][ z + 1 ];
          wuerfel[ DREHBEWEGUNG_RAND[ j ][ 0 ] ][ DREHBEWEGUNG_RAND[ j ][ 1 ] ][ z + 1 ] = zStein1;
          zStein1 = zStein2;
        }
      }
    }
  }
  
  public Fenster3D generateAnimationWindow() {
    return fenster = new Fenster3D( this, wuerfel );
  }
  
  @ Override
  public Color[] getBrickAtEdge( final Color farbe1, final Color farbe2, final Color farbe3 ) {
    return this.wuerfel[ getX( new Color[] { farbe1, farbe2, farbe3 } )
        + 1 ][ getY( new Color[] { farbe1, farbe2, farbe3 } )
            + 1 ][ getZ( new Color[] { farbe1, farbe2, farbe3 } ) + 1 ]
                .getFarbenUhrzeigersinnZuerst();
  }
  
  @ Override
  public Color[] getBrickAtBorder( final Color farbe1, final Color farbe2 ) {
    return this.wuerfel[ getX( new Color[] { farbe1, farbe2 } )
        + 1 ][ getY( new Color[] { farbe1, farbe2 } ) + 1 ][ getZ( new Color[] { farbe1, farbe2 } )
            + 1 ].getFarbenUhrzeigersinnZuerst();
  }
  
  @ Override
  public Color[] searchBrick( final Color[] farben ) {
    final int[] xyz = search( farben );
    
    final int x = xyz[ 0 ];
    final int y = xyz[ 1 ];
    final int z = xyz[ 2 ];
    
    final List<Color> ergebnis = new ArrayList<>( farben.length );
    
    if ( y != 0 ) {
      ergebnis.add( getYColor( y ) );
    }
    
    if ( x == 1 && z == -1 ) {
      ergebnis.add( Color.orange );
      ergebnis.add( Color.green );
    } else {
      if ( z == -1 ) {
        ergebnis.add( Color.green );
      }
      
      if ( x == -1 ) {
        ergebnis.add( Color.red );
      }
      
      if ( z == 1 ) {
        ergebnis.add( Color.blue );
      }
      
      if ( x == 1 ) {
        ergebnis.add( Color.orange );
      }
    }
    
    return ergebnis.toArray( new Color[ ergebnis.size() ] );
  }
  
  private int[] search( final Color[] farben ) {
    for ( int xi = -1; xi <= 1; xi++ ) {
      for ( int yi = -1; yi <= 1; yi++ ) {
        for ( int zi = -1; zi <= 1; zi++ ) {
          if ( this.wuerfel[ xi + 1 ][ yi + 1 ][ zi + 1 ].hatDieseFarben( farben ) ) {
            return new int[] { xi, yi, zi };
          }
        }
      }
    }
    
    throw new InternalError();
  }
  
  public Zauberwuerfel( final Stein[][][] wuerfel ) {
    this.wuerfel = wuerfel;
  }
}