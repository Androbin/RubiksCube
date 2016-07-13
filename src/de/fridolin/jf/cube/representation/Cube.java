package de.fridolin.jf.cube.representation;

/**
 * oben: wei�
 * vorne: gr�n
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
	 * dreht die w�rfelseite mit der farbe c (der mittlere stein) um 90� * drehung im Uhrzeigersinn
	 * 
	 * @param ausgabe
	 *        gibt an, ob sich ein Fenster mit der neuen Situation �ffnen soll
	 * @param c
	 * @param drehung
	 */
	void rotate( final Color c, final int drehung );
	
	/**
	 * Gibt den Stein zur�ck, der die Fl�chen mit den Farben farbe1, farbe2 und farbe3 an einer Ecke verbindet
	 * Falls der Brick oben oder unten ist, wird die Farbe dieser Seite zuerst
	 * ausgegeben, ansonsten einfach im Uhrzeigersinn: gr�n, rot, blau, orange
	 * Bsp: [weiss, rot, blau]
	 *
	 * @param farbe1
	 * @param farbe2
	 * @param farbe3
	 * @return
	 */
	Color[] getBrickAtEdge( final Color farbe1, final Color farbe2, final Color farbe3 );
	
	/**
	 * Gibt den Stein zur�ck, der die Fl�chen mit den Farben farbe1 und farbe2 an einer Ecke verbindet
	 * Falls der Brick oben oder unten ist, wird die Farbe dieser Seite zuerst
	 * ausgegeben, ansonsten einfach im Uhrzeigersinn: gr�n, rot, blau, orange
	 * Bsp: [weiss, rot, blau]
	 * 
	 * @param farbe1
	 * @param farbe2
	 * @return
	 */
	Color[] getBrickAtBorder( final Color farbe1, final Color farbe2 );
	
	/**
	 * Gibt die Farben der Fl�chen zur�ck, die durch den Stein mit den Farben farben verbunden werden:
	 * Falls farben drei Elemente enth�lt, ist der Stein ein Eckstein und der R�ckgabewert hat ebenfalls drei Werte
	 * Falls farben zwei Elemente enth�lt, ist der Stein ein Kantenstein und der R�ckgabewert hat ebenfalls zwei Werte
	 * Die Farben werden in dieser Reihenfolge zur�ckgegeben: wei�, gelb, gr�n, rot, blau, orange
	 * 
	 * @param farben
	 * @return
	 */
	Color[] searchBrick( final Color[] farben );
}