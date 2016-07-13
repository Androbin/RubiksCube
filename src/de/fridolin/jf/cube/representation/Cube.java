package de.fridolin.jf.cube.representation;

/**
 * oben: weiß
 * vorne: grün
 * rechts: rot
 * hinten: blau
 * links: orange
 * unten: gelb
 * 
 * @author Simon
 */
public interface Cube
{
	/**
	 * dreht die würfelseite mit der farbe c (der mittlere stein) um 90° * drehung im Uhrzeigersinn
	 * 
	 * @param ausgabe
	 *        gibt an, ob sich ein Fenster mit der neuen Situation öffnen soll
	 * @param c
	 * @param drehung
	 */
	void rotate( final Color c, final int drehung );
	
	/**
	 * Gibt den Stein zurück, der die Flächen mit den Farben farbe1, farbe2 und farbe3 an einer Ecke verbindet
	 * Falls der Brick oben oder unten ist, wird die Farbe dieser Seite zuerst
	 * ausgegeben, ansonsten einfach im Uhrzeigersinn: grün, rot, blau, orange
	 * Bsp: [weiss, rot, blau]
	 *
	 * @param farbe1
	 * @param farbe2
	 * @param farbe3
	 * @return
	 */
	Color[] getBrickAtEdge( final Color farbe1, final Color farbe2, final Color farbe3 );
	
	/**
	 * Gibt den Stein zurück, der die Flächen mit den Farben farbe1 und farbe2 an einer Ecke verbindet
	 * Falls der Brick oben oder unten ist, wird die Farbe dieser Seite zuerst
	 * ausgegeben, ansonsten einfach im Uhrzeigersinn: grün, rot, blau, orange
	 * Bsp: [weiss, rot, blau]
	 * 
	 * @param farbe1
	 * @param farbe2
	 * @return
	 */
	Color[] getBrickAtBorder( final Color farbe1, final Color farbe2 );
	
	/**
	 * Gibt die Farben der Flächen zurück, die durch den Stein mit den Farben farben verbunden werden:
	 * Falls farben drei Elemente enthält, ist der Stein ein Eckstein und der Rückgabewert hat ebenfalls drei Werte
	 * Falls farben zwei Elemente enthält, ist der Stein ein Kantenstein und der Rückgabewert hat ebenfalls zwei Werte
	 * Die Farben werden in dieser Reihenfolge zurückgegeben: weiß, gelb, grün, rot, blau, orange
	 * 
	 * @param farben
	 * @return
	 */
	Color[] searchBrick( final Color[] farben );
}