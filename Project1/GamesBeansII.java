/**
 * @author Jose Murta 55226 && Diogo Rodrigues 56153
 */

package gameBeansII;

public class GamesBeansII {
	
	private int depth;
	private int piles[];
	private int[][] jabaM;
	private int[][] pietonM;
	
	/**
	 * 
	 * @param depth - The depth of the game
	 * @param piles - The initial sequence of piles
	 */
	public GamesBeansII(int depth, int piles[]) {
		this.depth = depth;
		this.piles = piles;
		this.jabaM = new int[piles.length+1][piles.length+1];
		this.pietonM = new int[piles.length+1][piles.length+1];
		
	}
	
	/**
	 * Calculates the final score of Jaba
	 * 
	 * @param player1 - The first player to play
	 * @return The final score of Jaba
	 */
	public int computeScore(String player1) {
		for(int i=0; i<piles.length; i++) {
			jabaM[i][i+1] = piles[i];
		}
		for(int d=1; d<piles.length; d++) { 
			for(int i = 0; i<piles.length - d; i++) {
				int j = i +d+1;
				pietonM[i][j] = calculatePieton(i, j);
				jabaM[i][j] = calculateJaba(i,j);
			}
		}
		if(player1.equals("Jaba")) {
			return jabaM[0][piles.length];
		}
		else {
			return pietonM[0][piles.length];
		}
		
	}
	
	/**
	 * Calculates the score of Jaba with the sequence i to j to fill the matrix of pieton
	 * 
	 * @param i - The initial index of the sequence
	 * @param j - The final index of the sequence
	 * @return The score of Jaba
	 */
	private int calculatePieton(int i, int j) {
		int max = (int) (Double.NEGATIVE_INFINITY);
		boolean init  = true; //This boolean is true when the pile(s) were removed from the beginning, false if removed from the end 
		int auxDepth = depth;
		int kAux = 0;
		int aux = 0;
		int aux2 =0;
		
		if(j-i < depth) { 
			auxDepth = j-i;
		}
		
		for(int k= 0; k<auxDepth; k++) {
			aux += piles[i+k];
			if(aux>max) {
				max = aux;
				init = true;
				kAux = k+1;
			}
		}
		
		for(int k= 0; k<auxDepth; k++) {
			aux2 += piles[j-k-1];
			if(aux2>max) {
				max = aux2;
				init = false;
				kAux = k+1;
			}
		}
		if(init) {
			return jabaM[i+kAux][j];
		}
		else {
			return jabaM[i][j-kAux];
		}
	}
	
	/**
	 * Calculates the score of Jaba with the sequence i to j to fill her matrix
	 * 
	 * @param i - The initial index of the sequence
	 * @param j - The final index of the sequence
	 * @return The score of Jaba
	 */
	private int calculateJaba(int i, int j) {
		int max = (int) (Double.NEGATIVE_INFINITY);
		int auxDepth = depth;
		int aux = 0;
		int aux2 = 0;
		
		if(j-i < depth) { 
			auxDepth = j-i;
		}
		
		for(int k = 0; k<auxDepth; k++) {
			aux += piles[i+k];
			int temp = aux + pietonM[i+k+1][j];
			if(temp >max) {
				max = temp;
			}
			aux2 += piles[j-k-1];
			int temp2 = aux2  + pietonM[i][j-k-1];
			if(temp2 >max) {
				max = temp2;
			}
		}
		return max;
	}
	
	
	

}