/**
 * @author Jose Murta 55226 && Diogo Rodrigues 56153
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import gameBeansII.GamesBeansII;


public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input = new BufferedReader ( new InputStreamReader(System.in));
		int nTestCases = Integer.parseInt(input.readLine());
		for(int i = 0; i < nTestCases; i++) {
			String[] aux = input.readLine().split(" ");
			int nPiles = Integer.parseInt(aux[0]);
			int depth = Integer.parseInt(aux[1]);
			int[] piles = new int[nPiles];
			String[] auxPiles = input.readLine().split(" ");
			for(int j= 0; j< nPiles; j++) {
				piles[j] = Integer.parseInt(auxPiles[j]);
			}
			String player1 = input.readLine();

			GamesBeansII games = new GamesBeansII(depth, piles);
			System.out.println(games.computeScore(player1));
		}
	}

}