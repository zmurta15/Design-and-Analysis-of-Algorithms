/**
 * @author Jose Murta 55226 && Diogo Rodrigues 56153
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Lost {

	private int nCols, numNodes, finalNode;
	private char[][] map;
	private int[] wheels, pos;
	private List<Integer> listJohn, listKate;
	
	/**
	 * 
	 * @param nRows - number of rows of the island
	 * @param nCols - number of columns of the island
	 * @param map - matrix with the island's map
	 * @param wheels - position and time traveled of the wheels 
	 * @param pos - John and Kate's initial position
	 * @param finalNode - the island's exit
	 */
	public Lost(int nRows, int nCols, char [][] map, int[] wheels, int[] pos, int finalNode) {
		this.nCols = nCols;
		this.map = map;
		this.wheels = wheels;
		this.pos = pos;
		this.numNodes = nRows * nCols;
		this.listJohn = createEdgesJohn();
		this.listKate = createEdgesKate();
		this.finalNode = finalNode;
	}

	/**
	 * Calculates the time traveled from the start cell to the exit cell
	 * 
	 * @param isJohn - true if the player is John, false if the player is Kate
	 * @return the time traveled from the start to the exit cell
	 */
	public int calculate(boolean isJohn) {
		int[] le = new int[numNodes];
		List<Integer> list;

		for (int i = 0; i < numNodes; i++) {
			le[i] = Integer.MAX_VALUE;
		}
		
		int initial = -1;
		if(isJohn) {
			initial = pos[0] * nCols + pos[1];
			list = listJohn;
		}
		else {
			initial = pos[2] * nCols + pos[3];
			list = listKate;
		}
		
		le[initial] = 0;
		

		boolean changes = false;
		for (int j = 1; j < numNodes; j++) {
			changes = updateLengths(le, list);
			if (!changes) {
				break;
			}
		}
		
		if (changes && updateLengths(le, listJohn) && isJohn) {
			le[finalNode] = Integer.MIN_VALUE;
		}

		int a = le[finalNode];
		return a;
	}
	
	/**
	 * 
	 * @param le - array containing the time needed to travel to each node
	 * @param edges - edge's graph
	 * @return - true, if there were changes on the length array; false, otherwise
	 */
	private boolean updateLengths(int[] le, List<Integer> edges) {
		boolean changes = false;
		
		Iterator<Integer> it = edges.iterator();
		while(it.hasNext()) {
			int tail = it.next();
			int head = it.next();
			int label = it.next();
			if(le[tail] < Integer.MAX_VALUE) {
				int newLen = le[tail] + label;
				if( newLen < le[head]) {
					le[head] = newLen;
					changes = true;
				}
			}
		}
		return changes;
	}
	

	/**
	 * Creates edge's graph from John 
	 * 
	 * @return the list representing John's graph
	 */
	private List<Integer> createEdgesJohn() {
		List<Integer> list = new LinkedList<Integer>();
		int[] heads = new int[4];
		for (int i = nCols; i < numNodes-nCols; i++) {
			boolean found = false;

			heads[0] = i + 1;
			heads[1] = i - 1;
			heads[2] = i + nCols;
			heads[3] = i - nCols;
			
			
			for (int j = 0; j <4 ; j++) {
				if (heads[j] >= 0 && heads[j] < numNodes) {
					if (getType(i) != 'W' && getType(i) != 'O' && getType(i) != 'X') {
						if (getType(heads[j]) !='W' && getType(heads[j]) != 'O') {
							list.add(i);
							list.add(heads[j]);
							list.add(1);
						}
						if (getType(i) != 'G' && !found) {
							found = true;
							int aux = Character.getNumericValue(getType(i)) - 1;
							list.add(i);
							int destination = wheels[aux * 3] * nCols + wheels[aux * 3 + 1];
							list.add(destination);
							list.add(wheels[aux * 3 + 2]);
						}
					}
				}
			}
		}

		return list;
	}
	
	/**
	 * Creates edge's graph from Kate 
	 * 
	 * @return the list representing Kate's graph
	 */
	private List<Integer> createEdgesKate() {
		List<Integer> list = new LinkedList<Integer>();
		int[] heads = new int[4];
		for (int i = 0; i < numNodes; i++) {
			heads[0] = i + 1;
			heads[1] = i - 1;
			heads[2] = i + nCols;
			heads[3] = i - nCols;
			
			if (i % nCols == 0) {
				heads[1] = -1;
			}
			if ((i+1) % nCols == 0) {
				heads[0] = -1;
			}

			for (int j = 0; j < 4; j++) {
				if (heads[j] >= 0 && heads[j] < numNodes) {
					if (getType(i) != 'O'  && getType(i) != 'X') {
						if (getType(heads[j]) != 'O')  {
							list.add(i);
							list.add(heads[j]);
							if(getType(i) == 'W') {
								list.add(2);
							}
							else {
								list.add(1);
							}
						}
					}
				}
			}
		}

		return list;
	}
	
	/**
	 * Gets the type of the cell/node 
	 * 
	 * @param node - the node's integer
	 * @return the type of the node
	 */
	private char getType(int node) {
		int line = node / nCols;
		int col = node % nCols;
		return map[line][col];
	}
}