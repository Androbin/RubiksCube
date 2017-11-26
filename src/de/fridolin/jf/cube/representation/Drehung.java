package de.fridolin.jf.cube.representation;

import java.util.*;
import javax.media.j3d.*;

public final class Drehung {
  private final boolean x;
  private final boolean y;
  private final boolean z;
  private final boolean uhrzeigersinn;
  private final Set<Stein> zuDrehendeSteine;
  
  public Set<Stein> bekommeZuDrehendeSteine() {
    return zuDrehendeSteine;
  }
  
  /**
   * Berechnet die Transformation, die die y-Achse auf die Drehachse abbildet.
   * Wird f�r den RotationInterpolator verwendet
   * 
   * @return
   */
  public Transform3D bekommeAxis() {
    final Transform3D ergebnis = new Transform3D();
    
    if ( this.x ) {
      ergebnis.rotZ( -(float) Math.PI * 0.5f );
    }
    
    if ( this.z ) {
      ergebnis.rotX( (float) Math.PI * 0.5f );
    }
    
    if ( this.y ) {
      ergebnis.rotX( Math.PI );
    }
    
    return ergebnis;
  }
  
  /**
   * Berechnet die geometrische Transformation, die dieser Drehung entspricht
   * 
   * @return
   */
  public Transform3D bekommeTransform() {
    final Transform3D ergebnis = new Transform3D();
    final int dir = this.uhrzeigersinn ? 1 : -1;
    
    if ( this.x ) {
      ergebnis.rotX( dir * Math.PI * 0.5f );
    }
    
    if ( this.y ) {
      ergebnis.rotY( -dir * Math.PI * 0.5f );
    }
    
    if ( this.z ) {
      ergebnis.rotZ( dir * Math.PI * 0.5f );
    }
    
    return ergebnis;
  }
  
  /**
   * Berechnet den Winkel, um den die Drehung erfolgt
   * 
   * @return
   */
  public float bekommeMaxAngle() {
    return ( this.uhrzeigersinn ? 1f : -1f ) * (float) Math.PI * 0.5f;
  }
  
  public Drehung( final boolean x, final boolean y, final boolean z, final boolean imUhrzeigersinn,
      final Set<Stein> zuDrehendeSteine ) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.uhrzeigersinn = imUhrzeigersinn;
    this.zuDrehendeSteine = zuDrehendeSteine;
  }
}