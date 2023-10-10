import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Legio {

	private int nNodes;
	private int nSicks;
	private int[] restri;

	private List<Integer>[] adja;

	public Legio(int nNodes, int nSicks, int[] edges, int[] restri) {
		createDataStructure(nNodes);
		this.nNodes = nNodes;
		this.nSicks = nSicks;
		this.restri = restri;
		this.fill(edges);
	}

	public List<Integer> calculate() {
		int[] results = new int[nNodes];
		for (int i = 0; i < nSicks * 2; i += 2) {
			boolean[] found = new boolean[nNodes];
			int home = restri[i] - 1;
			int distance = restri[i + 1];

			Queue<Integer> waiting = new ArrayDeque<Integer>(nNodes);
			Queue<Integer> waitingNext = new ArrayDeque<Integer>(nNodes);
			waiting.add(home);
			found[home] = true;
			do {
				int node = waiting.remove();
				results[node]++;
				for (int j = 0; j < adja[node].size(); j++) {
					int ad = adja[node].get(j);
					if (!found[ad]) {
						waitingNext.add(ad);
						found[ad] = true;
					}
				}
				if (waiting.isEmpty()) {
					Queue<Integer> aux = waiting;
					waiting = waitingNext;
					waitingNext = aux;
					distance--;
				}
				if (distance <0) {
					break;
				}
			} while (!waiting.isEmpty());
		}
		
		List<Integer> list = new LinkedList<Integer>();
		for (int a = 0; a < nNodes; a++) {
			if (results[a] == nSicks) {
				list.add(a+1);
			}
		}
		return list;
	}

	private void fill(int[] edges) {
		for (int i = 0; i < edges.length; i += 2) {
			adja[edges[i] - 1].add(edges[i + 1] - 1);
			adja[edges[i + 1] - 1].add(edges[i] - 1);
		}
	}
	

	@SuppressWarnings("unchecked")
	private void createDataStructure(int vecLength) {
		adja = new List[vecLength];
		for (int i = 0; i < adja.length; i++) {
			adja[i] = new LinkedList<>();
		}
	}
}
