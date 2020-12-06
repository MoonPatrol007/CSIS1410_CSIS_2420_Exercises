package graphCityConnections;

/**
 * CE 
 * 
 * Cannon Jensen and Hector Juarez
 */

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.PrimMST;

public class CityConnectionsCE {

	public static void main(String[] args) {
		String fileName = "src/graphCityConnections/CityConnections.txt";
		EdgeWeightedSymbolGraph g = new EdgeWeightedSymbolGraph(fileName, ",");
		EdgeWeightedGraph eg = g.edgeWeightedGraph();
		PrimMST mst = new PrimMST(eg);
		
		System.out.println("Cities to connect with a bike trail:");
		System.out.println("------------------------------------");
		
		for(Edge x: mst.edges()) {
			int v1 = x.either();
			int v2 = x.other(v1);
			System.out.print(g.nameOf(v1) + " to " + g.nameOf(v2) + " (" + x.weight() + ")\n");
		}
		
		System.out.println("\nTotal length of the bike trail: " + mst.weight());
	}

}
