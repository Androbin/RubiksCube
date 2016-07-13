package de.fridolin.jf.cube.representation;

import static de.fridolin.jf.cube.representation.Util.*;
import com.sun.j3d.utils.behaviors.vp.*;
import com.sun.j3d.utils.universe.*;
import de.androbin.jf.cube.algorithm.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.*;
import javax.media.j3d.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.vecmath.*;

public final class Fenster3D extends JFrame
{
	private static final long			serialVersionUID		= 1L;
	private static final Set<Fenster3D>	offeneFenster			= new HashSet<>();
	private static final boolean		schrittFuerSchritt		= false;
	// Speichert die dreidimensionale Szene
	private final BranchGroup			g;
	// Speichert den Zauberw�rfel
	private final Cube					wuerfel;
										
	private final Timer					t						= new Timer( Stein3d.animationDuration, e -> naechsteDrehung() );
																
	private final Queue<Drehung>		ausstehendeDrehungen	= new ArrayDeque<>();
																
	public Fenster3D( final Cube cube, final Stein[][][] wuerfel )
	{
		super( "Zauberw�rfel" );
		// f�gt ein neues Fenster hinzu
		offeneFenster.add( this );
		// konfiguriert das Fenster
		if ( !schrittFuerSchritt )
		{
			this.t.start();
		}
		this.addWindowListener( new WindowAdapter()
		{
			@ Override
			public void windowClosing( final WindowEvent e )
			{
				offeneFenster.remove( Fenster3D.this );
				
				if ( offeneFenster.isEmpty() )
				{
					System.exit( 0 );
				}
			}
		} );
		// Legt die dreidimensionale Szene fest
		final Canvas3D c = new Canvas3D( SimpleUniverse.getPreferredConfiguration() );
		final SimpleUniverse s = new SimpleUniverse( c );
		s.getViewingPlatform().setNominalViewingTransform();
		g = new BranchGroup();
		final PointLight light = new PointLight( new Color3f(), new Point3f( 10.0f, 0f, 0f ), new Point3f( 0.3f, 0.3f, 0.3f ) );
		g.addChild( light );
		g.setCapability( BranchGroup.ALLOW_DETACH );
		g.setCapability( BranchGroup.ALLOW_CHILDREN_WRITE );
		g.setCapability( BranchGroup.ALLOW_CHILDREN_READ );
		
		this.wuerfel = cube;
		addChilds( wuerfel );
		
		g.compile();
		s.addBranchGraph( g );
		final OrbitBehavior b = new OrbitBehavior( c );
		b.setSchedulingBounds( new BoundingSphere( new Point3d( 0.0, 0.0, 0.0 ), Double.MAX_VALUE ) );
		s.getViewingPlatform().setViewPlatformBehavior( b );
		this.add( c );
		
		s.getCanvas().addKeyListener( new KeyAdapter()
		{
			@ Override
			public void keyReleased( final KeyEvent e )
			{
				Fenster3D.this.keyReleased( e );
			}
		} );
		this.setVisible( true );
		this.requestFocusInWindow();
		this.setSize( 500, 500 );
		this.setLocationRelativeTo( null );
	}
	
	private void addChilds( final Stein[][][] wuerfel )
	{
		final float gap = 0.1f;
		final float offset = 2f + gap;
		
		for ( int x = -1; x <= 1; x++ )
		{
			for ( int y = -1; y <= 1; y++ )
			{
				for ( int z = -1; z <= 1; z++ )
				{
					final Stein stein = wuerfel[ x + 1 ][ y + 1 ][ z + 1 ];
					g.addChild( stein.stein3d = new Stein3d( stein, x * offset, y * offset, z * offset ) );
				}
			}
		}
	}
	
	public void drehen( final Drehung d )
	{
		this.ausstehendeDrehungen.offer( d );
	}
	
	public void drehenX( final Stein[][][] wuerfel, final int x, final boolean imUhrzeigersinn )
	{
		final Set<Stein> zuDrehendeSteine = new HashSet<>( 9 );
		
		for ( int yStein = -1; yStein <= 1; yStein++ )
		{
			for ( int zStein = -1; zStein <= 1; zStein++ )
			{
				zuDrehendeSteine.add( wuerfel[ x + 1 ][ yStein + 1 ][ zStein + 1 ] );
			}
		}
		
		drehen( new Drehung( true, false, false, imUhrzeigersinn, zuDrehendeSteine ) );
	}
	
	public void drehenY( final Stein[][][] wuerfel, final int y, final boolean imUhrzeigersinn )
	{
		final Set<Stein> zuDrehendeSteine = new HashSet<>( 9 );
		
		for ( int xStein = -1; xStein <= 1; xStein++ )
		{
			for ( int zStein = -1; zStein <= 1; zStein++ )
			{
				zuDrehendeSteine.add( wuerfel[ xStein + 1 ][ y + 1 ][ zStein + 1 ] );
			}
		}
		
		drehen( new Drehung( false, true, false, imUhrzeigersinn, zuDrehendeSteine ) );
	}
	
	public void drehenZ( final Stein[][][] wuerfel, final int z, final boolean imUhrzeigersinn )
	{
		final Set<Stein> zuDrehendeSteine = new HashSet<>( 9 );
		
		for ( int xStein = -1; xStein <= 1; xStein++ )
		{
			for ( int yStein = -1; yStein <= 1; yStein++ )
			{
				zuDrehendeSteine.add( wuerfel[ xStein + 1 ][ yStein + 1 ][ z + 1 ] );
			}
		}
		
		drehen( new Drehung( false, false, true, imUhrzeigersinn, zuDrehendeSteine ) );
	}
	
	public void keyReleased( final KeyEvent e )
	{
		switch ( e.getKeyCode() )
		{
			default :
				this.wuerfel.rotate( Color.get( e.getKeyCode() ), e.isShiftDown() ? -1 : 1 );
				break;
			case KeyEvent.VK_S :
				shuffle( Fenster3D.this.wuerfel, ThreadLocalRandom.current() );
				break;
			case KeyEvent.VK_L :
				Algorithmus.solve( Fenster3D.this.wuerfel );
				break;
			case KeyEvent.VK_ENTER :
				if ( schrittFuerSchritt )
				{
					Fenster3D.this.naechsteDrehung();
				}
				break;
		}
	}
	
	private void naechsteDrehung()
	{
		// Bestimmt die auszuf�hrende Drehung
		final Drehung d = this.ausstehendeDrehungen.poll();
		
		if ( d == null )
		{
			return;
		}
		
		// Dreht die n�tigen Steine
		for ( final Stein stein : d.bekommeZuDrehendeSteine() )
		{
			stein.stein3d.addLastTransform();
			stein.stein3d.doNextStep( d );
		}
	}
}