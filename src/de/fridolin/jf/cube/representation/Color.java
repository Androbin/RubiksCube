package de.fridolin.jf.cube.representation;

import static java.awt.event.KeyEvent.*;
import it.unimi.dsi.fastutil.ints.*;

/**
 * Speichert die Farbe einer Würfelseite bzw. Seite eines einzelnen Steins
 * 
 * @author Simon
 */
public enum Color
{
	white,
	yellow,
	green,
	red,
	blue,
	orange,
	none;
	
	private static final Int2ObjectMap<Color> key_map = new Int2ObjectOpenHashMap<Color>();
	
	static
	{
		key_map.put( VK_B, blue );
		key_map.put( VK_G, green );
		key_map.put( VK_O, orange );
		key_map.put( VK_R, red );
		key_map.put( VK_Y, yellow );
		key_map.put( VK_W, white );
	}
	
	public static Color get( final int keycode )
	{
		return key_map.get( keycode );
	}
}