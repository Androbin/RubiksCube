package de.fridolin.jf.cube.representation;

import java.util.*;

public final class Stein {
  private final Color[] farben;
  private final List<Color> farbenOhneNull;
  
  public Stein3d stein3d;
  
  private static final int[] DREHUNGEN_GX = {
      5, 2, 4, 3,
  };
  private static final int[] DREHUNGEN_X = {
      2, 5, 3, 4,
  };
  private static final int[] DREHUNGEN_GY = {
      0, 4, 1, 5,
  };
  private static final int[] DREHUNGEN_Y = {
      4, 0, 5, 1,
  };
  private static final int[] DREHUNGEN_Z = {
      0, 2, 1, 3,
  };
  private static final int[] DREHUNGEN_GZ = {
      0, 3, 1, 2,
  };
  
  /**
   * Gibt die Farben des Steins zurück: [rechts, links, oben, unten, hinten, vorne]
   * 
   * @return
   */
  public Color[] getFarben() {
    return farben;
  }
  
  public Color[] getFarbenImUhrzeigersinn() {
    return new Color[] { farben[ 2 ], farben[ 3 ], farben[ 5 ], farben[ 0 ], farben[ 4 ],
        farben[ 1 ] };
  }
  
  public Color[] getFarbenUhrzeigersinnZuerst() {
    final Color[] farbenUhr = getFarbenImUhrzeigersinn();
    final List<Color> ergebnis = new ArrayList<>( 3 );
    ergebnis.add( farbenUhr[ 0 ] );
    ergebnis.add( farbenUhr[ 1 ] );
    
    if ( farbenUhr[ 3 ] == Color.none && farbenUhr[ 4 ] == Color.none ) {
      ergebnis.add( farbenUhr[ 5 ] );
      ergebnis.add( farbenUhr[ 2 ] );
    } else {
      for ( int i = 2; i < 6; i++ ) {
        ergebnis.add( farbenUhr[ i ] );
      }
    }
    
    for ( int i = 0; i < ergebnis.size(); i++ ) {
      if ( ergebnis.get( i ) == Color.none ) {
        ergebnis.remove( i );
        i--;
      }
    }
    
    return ergebnis.toArray( new Color[ ergebnis.size() ] );
  }
  
  /**
   * Gibt zurück, ob der Würfel genau die übergebenen Farben hat
   * 
   * @param colors
   * @return
   */
  public boolean hatDieseFarben( final Color[] colors ) {
    if ( this.farbenOhneNull.size() != colors.length ) {
      return false;
    }
    
    for ( final Color c : colors ) {
      if ( !this.farbenOhneNull.contains( c ) ) {
        return false;
      }
    }
    
    return true;
  }
  
  /**
   * Rotiert den Würfel einmal um die x-Achse
   * 
   * @param uhrzeigersinn
   */
  public void rotX( final boolean uhrzeigersinn ) {
    if ( uhrzeigersinn ) {
      this.rot( DREHUNGEN_X );
    } else {
      this.rot( DREHUNGEN_GX );
    }
  }
  
  /**
   * Rotiert den Würfel einmal um die y-Achse
   * 
   * @param uhrzeigersinn
   */
  public void rotY( final boolean uhrzeigersinn ) {
    if ( uhrzeigersinn ) {
      this.rot( DREHUNGEN_Y );
    } else {
      this.rot( DREHUNGEN_GY );
    }
  }
  
  /**
   * Rotiert den Würfel einmal um die z-Achse
   * 
   * @param uhrzeigersinn
   */
  public void rotZ( final boolean uhrzeigersinn ) {
    if ( uhrzeigersinn ) {
      this.rot( DREHUNGEN_Z );
    } else {
      this.rot( DREHUNGEN_GZ );
    }
  }
  
  /**
   * Vertauscht die Farben der Seitenflächen so, wie im übergebenen Array beschrieben
   * 
   * @param drehungen
   */
  public void rot( final int[] drehungen ) {
    int d = drehungen[ 0 ];
    final Color start = this.farben[ d ];
    
    for ( int indexe = 1; indexe < 4; indexe++ ) {
      this.farben[ d ] = this.farben[ d = drehungen[ indexe ] ];
    }
    
    this.farben[ d ] = start;
  }
  
  public Stein( final Color[] farben ) {
    this.farben = farben;
    this.farbenOhneNull = new ArrayList<>();
    
    for ( final Color f : this.farben ) {
      if ( f != Color.none ) {
        this.farbenOhneNull.add( f );
      }
    }
  }
}