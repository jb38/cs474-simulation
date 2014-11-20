package edu.hood.cs.sim;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.display.SimpleController;
import sim.engine.SimState;
import sim.portrayal.continuous.ContinuousPortrayal2D;

public class SimulationWithUI extends GUIState {

	/** The desired FPS */
	public double FRAMES_PER_SECOND = 60;

	/** The Display for the game. */
	public Display2D display;

	/** The window in which the Display resides. */
	public JFrame displayFrame;

	/** The field portrayal for the asteroids field. */
	public ContinuousPortrayal2D fieldPortrayal = new ContinuousPortrayal2D();

	/** Default main() for MASON */
	public static void main(String[] args) {
		new SimulationWithUI().createController();
	}

	/** Creates a SimpleController and starts it playing. */
	public Controller createController() {
		SimpleController c = new SimpleController(this);
		c.pressPlay();
		return c;
	}

	public SimulationWithUI() {
		this(new Simulation(System.currentTimeMillis()));
	}

	public SimulationWithUI(SimState state) {
		super(state);
	}

	/** Provides the name of the game. */
	public static String getName() {
		return "Simulation";
	}

	/** Starts the game. */
	public void start() {
		super.start();
		setupPortrayals();
	}

	/** Loads a game. This is unlikely to occur. */
	public void load(SimState state) {
		super.load(state);
		setupPortrayals();
	}

	/**
	 * Creates the portrayal and sets it up. The portrayal is set up to display
	 * toroidally to show the asteroids properly. Additionally a RateAdjuster is
	 * added to the GUIState minischedule to maintain an approximate rate of
	 * speed.
	 */
	public void setupPortrayals() {
		Simulation simulation = (Simulation) state;

		fieldPortrayal.setField(simulation.field);
		//fieldPortrayal.setDisplayingToroidally(true);
		//fieldPortrayal.set

		
		//scheduleRepeatingImmediatelyAfter(new RateAdjuster(FRAMES_PER_SECOND));

		// reschedule the displayer
		display.reset();

		// redraw the display
		display.repaint();
	}

	/**
	 * Sets up the initial game. Creates a display and sets it to be the only
	 * thing that can be shown, and sans normal decorations. Adds listeners for
	 * keystrokes.
	 */
	public void init(final Controller c) // make it final so we can use it in
											// the anonymous class
	{
		super.init(c);

		// make the displayer
		display = new Display2D(750, 750, this) {
			public void quit() // we close our controller when we die
			{
				super.quit();
				((SimpleController) c).doClose();
			}
		};

		display.setBackdrop(Color.black);

		displayFrame = display.createFrame();
		displayFrame.setTitle("Simulation");
		c.registerFrame(displayFrame); // register the frame so it appears in
										// the "Display" list
		displayFrame.setVisible(true);

		display.attach(fieldPortrayal, "Simulation");

		// some stuff to make this feel less like MASON:

		// delete the header
		display.remove(display.header);
		// delete all listeners
		display.removeListeners();
		// delete the scroll bars
		display.display
				.setVerticalScrollBarPolicy(display.display.VERTICAL_SCROLLBAR_NEVER);
		display.display
				.setHorizontalScrollBarPolicy(display.display.HORIZONTAL_SCROLLBAR_NEVER);
		// when we close the window, the application quits
		displayFrame
				.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		// can't resize
		displayFrame.setResizable(false);
		// add antialiasing and interpolation
		display.insideDisplay.setupHints(true, false, false);
		// the window won't be the right size now -- modify it.
		displayFrame.pack();

		// Now we add in the listeners we want
		addListeners(display);
	}

	/** Creates key listeners which issue requests to the simulation. */
	public void addListeners(final Display2D display) {
		final Simulation simulation = (Simulation) state;
		final SimpleController cont = (SimpleController) controller;

		// Make us able to take focus -- this is by default true usually anyway
		display.setFocusable(true);

		// Make us request focus whenever our window comes up
		displayFrame.addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				display.requestFocusInWindow();
			}
		});

		// the display frame has just been set visible so we need to request
		// focus once
		display.requestFocusInWindow();
	}

	/** Quits the game. */
	public void quit() {
		super.quit();

		if (displayFrame != null)
			displayFrame.dispose();
		displayFrame = null;
		display = null;
	}

}
