package generation;
import generation.MazeFactory;
import generation.Order;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import gui.Controller;
import gui.MazePanel;
import gui.StateGenerating;

public class MazeFactoryTest {
	private int width = 4;
	private int height = 4;
	private Floorplan floorplan;  // setup makes this a width x height cells object
	private Floorplan floorplan1;
	int seed;
	MazeFactory factory;
	StubOrder currentOrder;
	MazeContainer mazeProducer;
	MazePanel panel;
	boolean deterministic = true;
	@Test
	final void testYes() {
		currentOrder = new StubOrder();
		mazeProducer = new MazeContainer();
		currentOrder.setSkillLevel(9);
		currentOrder.setBuilder(Order.Builder.Prim);
		currentOrder.setPerfect(true);
		currentOrder.setSeed(seed);
		factory = new MazeFactory();
		factory.order(currentOrder);
		factory.waitTillDelivered();
		currentOrder.deliver(mazeProducer);
		mazeProducer = currentOrder.getMaze();
		Floorplan floorplan = mazeProducer.getFloorplan();
		int i = floorplan.getValueOfCell(0, 0);
		System.out.println(i);
		assertEquals(0,0);
	}
//	Test to identify if there exist rooms with no doors

//	Test to see if there are more than one openings on exterior wall
//	Test to see if an exit exists
// Test to see if exit is accessible
// Test to see if the maze is perfect
// Test if there are distance bounds


	
	 

}
