/**
 * It should allow the user to specify a departure airport. 
 * If the specified airport is not listed in routes.txt, a message 
 * should be printed that the airport could not be found. (see Expected Output) 
 * Otherwise, list the shortest paths to all the destinations that can be 
 * reached from the departure airport.
 * 
 * In this context, the shortest path means the route with the least amount 
 * of stops between departure and destination.
 * 
 */
package graphSymbol;

import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author Hector_Juarez
 *
 */
public class RouteFinder {
	private ST<String, Integer> st;  // string -> index
	private String[] keys;           // index  -> string
	private Graph graph;             // the underlying graph

	public RouteFinder(String filename, String delimiter) {
		st = new ST<String, Integer>();

		// First pass builds the index by reading strings to associate
		// distinct strings with an index
		In in = new In(filename);

		// while (in.hasNextLine()) {
		while (!in.isEmpty()) {
			String[] a = in.readLine().split(delimiter);
			for (int i = 0; i < a.length; i++) {
				if (!st.contains(a[i]))
					st.put(a[i], st.size());
			}
		}
		StdOut.println(filename);

		// inverted index to get string keys in an array
		keys = new String[st.size()];
		for (String name : st.keys()) {
			keys[st.get(name)] = name;
		}

		// second pass builds the graph by connecting first vertex on each
		// line to all others
		graph = new Graph(st.size());
		in = new In(filename);
		while (in.hasNextLine()) {
			String[] a = in.readLine().split(delimiter);
			int v = st.get(a[0]);
			for (int i = 1; i < a.length; i++) {
				int w = st.get(a[i]);
				graph.addEdge(v, w);
			}
		}
	}

	public boolean contains(String s) {
		return st.contains(s);
	}

	@Deprecated
	public int index(String s) {
		return st.get(s);
	}

	public int indexOf(String s) {
		return st.get(s);
	}

	@Deprecated
	public String name(int v) {
		validateVertex(v);
		return keys[v];
	}

	public String nameOf(int v) {
		validateVertex(v);
		return keys[v];
	}

	@Deprecated
	public Graph G() {
		return graph;
	}

	public Graph graph() {
		return graph;
	}

	private void validateVertex(int v) {
		int V = graph.V();
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String filename  = "src/graphSymbol/routes.txt";
		String delimiter = " ";

		SymbolGraph sg = new SymbolGraph(filename, delimiter);
		Graph graph = sg.graph(); 

		while (StdIn.hasNextLine()) {

			StdOut.printf("Departure: ", StdIn.readLine());
			String source = StdIn.readLine();


			if (sg.contains(source)) {

				int s = sg.index(source);
				StdOut.println("The following destinations can be reached from " + source + ":");

				BreadthFirstPaths bfs = new BreadthFirstPaths(graph, sg.indexOf(source));

				for (int v = 0; v < graph.V(); v++) {
					StdOut.print("\n " + sg.name(v) + ": ");

					for (int w : bfs.pathTo(v)) {
						StdOut.print(sg.nameOf(w) + " ");
					} 

				}

			} else {
				StdOut.println("The departure " + source + " could not be  found");
			}
		}


	}
}


