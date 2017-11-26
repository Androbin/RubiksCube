package de.fridolin.jf.cube.representation;

import static java.awt.event.KeyEvent.*;

/**
 * Speichert die Farbe einer Würfelseite bzw. Seite eines einzelnen Steins
 * 
 * @author Simon
 */
public enum Color {
  white,
  yellow,
  green,
  red,
  blue,
  orange,
  none;
  
  public static Color get( final int keycode ) {
    switch ( keycode ) {
      case VK_B:
        return blue;
      case VK_G:
        return green;
      case VK_O:
        return orange;
      case VK_R:
        return red;
      case VK_Y:
        return yellow;
      case VK_W:
        return white;
    }
    
    return null;
  }
}