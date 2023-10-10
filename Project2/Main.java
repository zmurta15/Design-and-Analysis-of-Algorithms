import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		String[] aux = input.readLine().split(" ");
		int nNodes = Integer.parseInt(aux[0]);
		int nEdges = Integer.parseInt(aux[1]);

		int[] temp = new int[nEdges * 2];
		int cont = 0;
		for (int i = 0; i < nEdges; i++) {
			String[] aux2 = input.readLine().split(" ");
			temp[cont++] = Integer.parseInt(aux2[0]);
			temp[cont++] = Integer.parseInt(aux2[1]);
		}

		int nSick = Integer.parseInt(input.readLine());
		int[] temp2 = new int[nSick * 2];
		int cont2 = 0;
		for (int j = 0; j < nSick; j++) {
			String[] aux3 = input.readLine().split(" ");
			temp2[cont2++] = Integer.parseInt(aux3[0]);
			temp2[cont2++] = Integer.parseInt(aux3[1]);
		}

		Legio l = new Legio(nNodes, nSick, temp, temp2);
		List<Integer> list = l.calculate();
		
		
		
		if (list.isEmpty()) {
			System.out.println(0);
		}
		else {
			Iterator<Integer> it = list.iterator();
			while (it.hasNext()) {
				int x = it.next();
				if (!it.hasNext()) {
					System.out.println(x);
				} else {
					System.out.print(x + " ");
				}
			}
		}
	}

}
