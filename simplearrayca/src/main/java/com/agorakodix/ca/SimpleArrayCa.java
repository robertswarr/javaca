package com.agorakodix.ca;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.agorakodix.ca.rules.CaRules;


/**
 * Set the character encoding in the run configuraton 
 * to UTF-8 to display the ALIVE_ICON in the Eclipse console. 
 * @author Robert Swarr
 *
 */
public class SimpleArrayCa {
	
	private static final int MAX_GENERATIONS = 75;
	private static final int GRID_WIDTH = 155;
	private static final char ALIVE_ICON = (char) '\u2588';  // UTF-8 FULL Block
	//private static final char ALIVE_ICON = (char) '\u25A0';
	private static final char DEAD_ICON = (char) '\u2591'; // UTF-8 LiGHT SHADE
	
	private int generation;
	private int[] cells = new int[GRID_WIDTH];
	private int[] newCells = new int[GRID_WIDTH];

	
	public SimpleArrayCa() {
		super();
		Arrays.fill(cells, 0);
		Arrays.fill(newCells, 0);
		cells[cells.length / 2 ] = 1;
	}
	
	
	/**
	 * The displayBoardOnConsole method writes the 
	 * board to the console for the generation.  The board
	 * is written to the console as one String.
	 */
	private void displayCellsOnConsole(int[] cells) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < GRID_WIDTH; i++ ) {
			//sb.append(cells[i] == 1 ? " " + ALIVE_ICON : " ");
			sb.append(cells[i] == 1 ? ALIVE_ICON :  DEAD_ICON);
		}
		sb.append("|");
		System.out.println(sb.toString() + " Generation:" + generation);
	}
	
	private void nextGeneration() { 
		// skip the edge case
		for (int i = 1; i < GRID_WIDTH - 1; i++) {
			newCells[i] = CaRules.getState(cells[i - 1], cells[i], cells[i + 1]);
		}
		System.arraycopy(newCells, 0, cells, 0, newCells.length);
		generation++;
	}

	public static void main(String[] args) {
		SimpleArrayCa ca = new SimpleArrayCa();
		long startTime = System.nanoTime();
		ca.displayCellsOnConsole(ca.cells);
		for (int i = 0; i < MAX_GENERATIONS; i++) { 
			ca.nextGeneration();
        	ca.displayCellsOnConsole(ca.cells);
		}
		long stopTime = System.nanoTime();
		long durationNs = stopTime - startTime;
		long durationMs = TimeUnit.MILLISECONDS.convert(durationNs, TimeUnit.NANOSECONDS);
	
		System.out.println("Excution time in MS: " + durationMs);
	}
}
