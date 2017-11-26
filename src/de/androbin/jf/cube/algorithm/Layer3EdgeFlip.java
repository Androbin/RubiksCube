package de.androbin.jf.cube.algorithm;

import static de.androbin.jf.cube.algorithm.Algorithmus.*;
import static de.androbin.jf.cube.algorithm.Layer3EdgeSwap.*;
import de.fridolin.jf.cube.representation.*;

public final class Layer3EdgeFlip {
  private Layer3EdgeFlip() {
  }
  
  public static void layer3edgeFlip( final Cube cube ) {
    final Color border_c = cube.getBrickAtBorder( DOWN, SIDES[ 0 ] )[ 1 ];
    final Color border_d = cube.getBrickAtBorder( DOWN, SIDES[ 2 ] )[ 1 ];
    final Color border_e = cube.getBrickAtBorder( DOWN, SIDES[ 1 ] )[ 1 ];
    final Color border_f = cube.getBrickAtBorder( DOWN, SIDES[ 3 ] )[ 1 ];
    
    if ( border_c == SIDES[ 0 ] ) {
      layer3edgeFlip1( cube, border_d, border_e, border_f );
    } else {
      layer3edgeFlip2( cube, border_d, border_e, border_f );
    }
  }
  
  private static void layer3edgeFlip1( final Cube cube, final Color border_d, final Color border_e,
      final Color border_f ) {
    if ( border_d != SIDES[ 2 ] ) {
      if ( border_e != SIDES[ 1 ] ) {
        for ( int i = 0; i < 2; i++ ) {
          layer3edgeSwap( cube, SIDES[ 1 ], SIDES[ 0 ] );
        }
      } else if ( border_f != SIDES[ 3 ] ) {
        for ( int i = 0; i < 2; i++ ) {
          layer3edgeSwap( cube, SIDES[ 2 ], SIDES[ 1 ] );
        }
      }
    } else if ( border_e != SIDES[ 1 ] ) {
      for ( int i = 0; i < 2; i++ ) {
        layer3edgeSwap( cube, SIDES[ 1 ], SIDES[ 0 ] );
      }
      
      for ( int i = 0; i < 2; i++ ) {
        layer3edgeSwap( cube, SIDES[ 2 ], SIDES[ 1 ] );
      }
    }
  }
  
  private static void layer3edgeFlip2( final Cube cube, final Color border_d, final Color border_e,
      final Color border_f ) {
    if ( border_e != SIDES[ 1 ] ) {
      layer3edgeFlip21( cube, border_d );
    } else if ( border_f != SIDES[ 3 ] ) {
      layer3edgeFlip22( cube );
    } else if ( border_d != SIDES[ 2 ] ) {
      layer3edgeFlip23( cube );
    }
  }
  
  private static void layer3edgeFlip21( final Cube cube, final Color border_d ) {
    for ( int i = 0; i < 2; i++ ) {
      layer3edgeSwap( cube, SIDES[ 0 ], SIDES[ 3 ] );
    }
    
    if ( border_d != SIDES[ 2 ] ) {
      for ( int i = 0; i < 2; i++ ) {
        layer3edgeSwap( cube, SIDES[ 2 ], SIDES[ 1 ] );
      }
    }
  }
  
  private static void layer3edgeFlip22( final Cube cube ) {
    for ( int i = 0; i < 2; i++ ) {
      layer3edgeSwap( cube, SIDES[ 3 ], SIDES[ 2 ] );
    }
  }
  
  private static void layer3edgeFlip23( final Cube cube ) {
    for ( int i = 0; i < 2; i++ ) {
      layer3edgeSwap( cube, SIDES[ 0 ], SIDES[ 3 ] );
    }
    
    for ( int i = 0; i < 2; i++ ) {
      layer3edgeSwap( cube, SIDES[ 1 ], SIDES[ 0 ] );
    }
  }
}