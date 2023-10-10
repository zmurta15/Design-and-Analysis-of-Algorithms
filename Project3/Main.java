/**
 * @author Jose Murta 55226 && Diogo Rodrigues 56153
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input = new BufferedReader ( new InputStreamReader(System.in));
		int nTestCases = Integer.parseInt(input.readLine());
		for(int i = 0; i < nTestCases; i++) {
			String[] firstLine = input.readLine().split(" ");
			int nRows = Integer.parseInt(firstLine[0]);
			int nCols = Integer.parseInt(firstLine[1]);
			int nWheels = Integer.parseInt(firstLine[2]);
			int finalNode = -1;
			
			char [][] map = new char [nRows][nCols];
			for(int r = 0; r < nRows; r++) {
				String[] lines = input.readLine().split("");
				for(int c = 0; c< nCols; c++) {
					map[r][c] = lines[c].charAt(0);
					if(lines[c].equals("X")) {
						finalNode = r*nCols+c;
					}
				}
			}
			
			int[] wheels = new int[3*nWheels];
			for(int j= 0; j <nWheels*3; j+=3) {
				String[] aux = input.readLine().split(" ");
				wheels[j] = Integer.parseInt(aux[0]);
				wheels[j+1] = Integer.parseInt(aux[1]);
				wheels[j+2] = Integer.parseInt(aux[2]);
			}
			
			String[] lastLine =  input.readLine().split(" ");
			int[] pos= new int[4];
			for(int p = 0; p<4; p++) {
				pos[p] = Integer.parseInt(lastLine[p]);
			}
			
			Lost l = new Lost(nRows, nCols, map, wheels, pos, finalNode); 
			System.out.println("Case #"+ (i+1));
			int answerJohn = l.calculate(true);
			int answerKate = l.calculate(false);
			
			if(answerJohn == Integer.MIN_VALUE) {
				System.out.println("John Lost in Time");
			}
			else if(answerJohn == Integer.MAX_VALUE) {
				System.out.println("John Unreachable");
			}
			else {
				System.out.println("John "+answerJohn); 
			}
			
			if(answerKate == Integer.MAX_VALUE) {
				System.out.println("Kate Unreachable");
			}
			else {
				System.out.println("Kate "+answerKate); 
			}
			
		}

	}

}