package graphInternet;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.KruskalMST;
import java.util.Iterator;

public class InternetCE {
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		In in = new In("src/graphInternet/GraphInternet.txt");
		
		EdgeWeightedGraph graph = new EdgeWeightedGraph(in);
		
		KruskalMST kruskal = new KruskalMST(graph);
		
		System.out.print("Offices needing to be connected: ");
		
		Iterator<Edge> var5 = kruskal.edges().iterator();

		Edge e;
		while (var5.hasNext()) {
			e = (Edge) var5.next();
			if (e.either() != 8 && e.other(e.either()) != 8) {
				System.out.print(e.either() + "-" + e.other(e.either()) + " ");
			}
		}

		System.out.println(); // new empty line
		
		System.out.print("Offices needing a router: ");
		var5 = kruskal.edges().iterator();

		while (true) {
			do {
				if (!var5.hasNext()) {
					System.out.println();
					System.out.printf("Total cost: $%.2f", kruskal.weight());
					return;
				}

				e = (Edge) var5.next();
			} while (e.either() != 8 && e.other(e.either()) != 8);

			System.out.print(e.either() + " ");
		}
	}
}
