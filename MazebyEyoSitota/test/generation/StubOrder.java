package generation;
import generation.Factory;
import generation.Floorplan;
import generation.Maze;
import generation.MazeFactory;
import generation.Order;
import gui.Constants.UserInput;
import generation.Order.Builder;
import gui.Controller;
import gui.MazeFileReader;
import gui.MazePanel;
import gui.SimpleScreens;

public class StubOrder implements Order {
//Code Copied From StateGenerating
	SimpleScreens view;
    MazePanel panel;
    Controller control;
    // Filename if maze is loaded from file, can be null
    private String filename;
    MazeContainer container;
    // about the maze and its generation
    private int seed; // the seed value used for the random number generator
    private int skillLevel; // user selected skill level, i.e. size of maze
    private Builder builder; // selected maze generation algorithm
    private boolean perfect; // selected type of maze, i.e. 
    // perfect == true: no loops, i.e. no rooms
    // perfect == false: maze can support rooms
   
    // The factory is used to calculate a new maze configuration
    // The maze is computed in a separate thread which makes 
    // communication with the factory slightly more complicated.
    // Check the factory interface for details.
    protected Factory factory;
    // The maze configuration produced by the factory
    //private MazeConfiguration mazeConfig; 

    private int percentdone;        // describes progress during generation phase

    boolean started;
    
    public StubOrder() {
    	filename = null;
        factory = new MazeFactory() ;
        skillLevel = 0; // default size for maze
        builder = Order.Builder.DFS; // default algorithm
        perfect = false; // default: maze can have rooms
        percentdone = 0;
        started = false;
        seed = 13;
    }
	@Override
	public int getSkillLevel() {
		// TODO Auto-generated method stub
		return skillLevel;
	}

	@Override
	public Builder getBuilder() {
		// TODO Auto-generated method stub
		return builder;
	}

	@Override
	public boolean isPerfect() {
		// TODO Auto-generated method stub
		return perfect;
	}

	@Override
	public int getSeed() {
		return seed;
	}

	@Override
	public void deliver(Maze mazeConfig) {
		if (Floorplan.deepdebugWall)
        {   // for debugging: dump the sequence of all deleted walls to a log file
            // This reveals how the maze was generated
            mazeConfig.getFloorplan().saveLogFile(Floorplan.deepedebugWallFileName);
        }
//        control.switchFromGeneratingToPlaying(mazeConfig);
	}
	public void deliver(MazeContainer mazeConfig) {
		 if (Floorplan.deepdebugWall)
	        {   // for debugging: dump the sequence of all deleted walls to a log file
	            // This reveals how the maze was generated
	            mazeConfig.getFloorplan().saveLogFile(Floorplan.deepedebugWallFileName);
	        }
//	        control.switchFromGeneratingToPlaying(mazeConfig);
	        container = mazeConfig;
	}

	@Override
	public void updateProgress(int percentage) {
		if (this.percentdone < percentage && percentage <= 100) {
            this.percentdone = percentage;
        }
	}
	public void setSkillLevel(int skillLevel) {
		this.skillLevel = skillLevel; 
	}
	public void setSeed(int seed) {
		this.seed = seed; 
	}
	public Factory getFactory() {
		return factory;
	}
	public void setBuilder(Builder builder) {
		this.builder = builder;
	}
	public void start(Controller controller, MazePanel panel) {
        started = true;
        // keep the reference to the controller to be able to call method to switch the state
        control = controller;
        // keep the reference to the panel for drawing
        this.panel = panel;
        // init mazeview, controller is needed for generating screen to update progress bar
        view = new SimpleScreens();
        // reset percentage for progress
        percentdone = 0;
        // if given a filename, load maze from file
        // otherwise, show view and order maze from factory
        
            // common case: generate maze with some algorithm
            assert null != factory : "Controller.init: factory must be present";
           
            // make maze factory produce a maze 
            // operates with background thread
            // method returns immediately, 
            // maze will be delivered later by calling this.deliver method
            // this object implements Order, so it carries the spec for the maze
            // to be generated
            factory.order(this) ;
        
    }
	public void setPerfect(boolean b) {
		perfect = b;
		
	}
	public MazeContainer getMaze() {
		// TODO Auto-generated method stub
		return container;
	}

}
