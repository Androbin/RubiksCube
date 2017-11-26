package de.fridolin.jf.cube.representation;

import com.sun.j3d.utils.geometry.*;
import java.util.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public final class Stein3d extends TransformGroup {
  private static final Map<Color, Appearance> FARBEN = new HashMap<>();
  public static final int ANIMATION_DURATION = 140;
  private final TransformGroup transformGroup = new TransformGroup();
  private Transform3D lastTransform;
  private final Alpha a = new Alpha( 1, Alpha.INCREASING_ENABLE, 0, 0, ANIMATION_DURATION, 0, 0, 0,
      0, 0 );
  private final RotationInterpolator pX;
  
  static {
    // Speichert die zu den W�rfelfarben passende Appeareances ab, um die Seitenfl�chen in der
    // richtigen Farbe darzustellen
    final Color3f black = new Color3f( 0f, 0f, 0f );
    final Material maBlack = new Material( black, black, black, black, 0 );
    final Appearance appBlack = new Appearance();
    appBlack.setMaterial( maBlack );
    FARBEN.put( Color.none, appBlack );
    
    final Color3f orange = new Color3f( 1f, 0.5f, 0.0f );
    final Material maOrange = new Material( orange, orange, orange, orange, 0 );
    final Appearance appOrange = new Appearance();
    appOrange.setMaterial( maOrange );
    FARBEN.put( Color.orange, appOrange );
    
    final Color3f red = new Color3f( 0.9f, 0f, 0f );
    final Material maRed = new Material( red, red, red, red, 0 );
    final Appearance appRed = new Appearance();
    appRed.setMaterial( maRed );
    FARBEN.put( Color.red, appRed );
    
    final Color3f green = new Color3f( 0f, 0.6f, 0.2f );
    final Material maGreen = new Material( green, green, green, green, 0 );
    final Appearance appGreen = new Appearance();
    appGreen.setMaterial( maGreen );
    FARBEN.put( Color.green, appGreen );
    
    final Color3f blue = new Color3f( 0f, 0f, 0.9f );
    final Material maBlue = new Material( blue, blue, blue, blue, 0 );
    final Appearance appBlue = new Appearance();
    appBlue.setMaterial( maBlue );
    FARBEN.put( Color.blue, appBlue );
    
    final Color3f white = new Color3f( 1f, 1f, 1f );
    final Material maWhite = new Material( white, white, white, white, 0 );
    final Appearance appWhite = new Appearance();
    appWhite.setMaterial( maWhite );
    FARBEN.put( Color.white, appWhite );
    
    final Color3f yellow = new Color3f( 1f, 1f, 0f );
    final Material maYellow = new Material( yellow, yellow, yellow, yellow, 0 );
    final Appearance appYellow = new Appearance();
    appYellow.setMaterial( maYellow );
    FARBEN.put( Color.yellow, appYellow );
  }
  
  /**
   * Fügt eine neue Seitenfläche hinzu
   * 
   * @param x
   * @param y
   * @param z
   * @param breite
   * @param höhe
   * @param länge
   * @param c
   */
  private void addBox( final float x, final float y, final float z, final float breite,
      final float hoehe, final float laenge, final Color c ) {
    final TransformGroup group = new TransformGroup();
    final Transform3D t = new Transform3D();
    t.setTranslation( new Vector3f( x, y, z ) );
    group.setTransform( t );
    group.addChild( new Box( breite, hoehe, laenge, Stein3d.FARBEN.get( c ) ) );
    transformGroup.addChild( group );
  }
  
  /**
   * Startet eine Rotation mit den übergebenen Werten
   * 
   * @param axis
   * @param maxAngle
   * @param s
   */
  @ SuppressWarnings( "deprecation" )
  private void startRotation( final Transform3D axis, final float maxAngle ) {
    this.pX.setAxisOfRotation( axis );
    this.pX.setMaximumAngle( maxAngle );
    this.a.setStartTime( System.currentTimeMillis() );
  }
  
  /**
   * Fügt eine Transformation hinten an die Reihe bisher ausgeführter Transformationen an
   * 
   * @param t
   */
  private void addTransform( final Transform3D t ) {
    final Transform3D bisJetzt = new Transform3D();
    transformGroup.getTransform( bisJetzt );
    bisJetzt.mul( t, bisJetzt );
    transformGroup.setTransform( bisJetzt );
  }
  
  public Stein3d( final Stein stein, final float x, final float y, final float z ) {
    this.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE );
    this.a.setStartTime( Long.MAX_VALUE );
    stein.stein3d = this;
    // F�gt die Seitenfl�chen hinzu
    this.addBox( x + 1f, y, z, 0.01f, 1, 1, stein.getFarben()[ 0 ] );
    this.addBox( x - 1f, y, z, 0.01f, 1, 1, stein.getFarben()[ 1 ] );
    this.addBox( x, y + 1f, z, 1, 0.01f, 1, stein.getFarben()[ 2 ] );
    this.addBox( x, y - 1f, z, 1, 0.01f, 1, stein.getFarben()[ 3 ] );
    this.addBox( x, y, z - 1f, 1, 1, 0.01f, stein.getFarben()[ 4 ] );
    this.addBox( x, y, z + 1f, 1, 1, 0.01f, stein.getFarben()[ 5 ] );
    
    transformGroup.setCapability( TransformGroup.ALLOW_TRANSFORM_WRITE );
    transformGroup.setCapability( TransformGroup.ALLOW_TRANSFORM_READ );
    this.addChild( transformGroup );
    // Initialisiert den RotationInterpolator
    final BoundingSphere bounds = new BoundingSphere( new Point3d(), Double.MAX_VALUE );
    this.pX = new RotationInterpolator( this.a, this );
    this.pX.setSchedulingBounds( bounds );
    this.addChild( this.pX );
  }
  
  /**
   * Fügt die Transformation, die der Interpolator zuletzt als Animation abgespielt hat,
   * zu den Transformationen hinzu
   */
  public void addLastTransform() {
    if ( lastTransform != null ) {
      this.addTransform( lastTransform );
      lastTransform = null;
    }
  }
  
  /**
   * Startet eine Rotation und speichert diese ab
   * 
   * @param d
   * @param s
   */
  public void doNextStep( final Drehung d ) {
    this.startRotation( d.bekommeAxis(), d.bekommeMaxAngle() );
    lastTransform = d.bekommeTransform();
  }
}