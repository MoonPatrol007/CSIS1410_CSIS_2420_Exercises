
/*
 * Class Excersie: Cannon Jensen and Hector Juarez
 * 
 * https://algs4.cs.princeton.edu/41graph/
 * 
 * https://algs4.cs.princeton.edu/41graph/tinyG.txt
 * the tinyG.txt needs to be converted to match the code. 
 * 
 */
		
package graphDFSvsBFS;

import java.io.File;

import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.DepthFirstPaths;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;

public class DFSvsBFS {

	public static void main(String[] args) {
		In in = new In(new File("src/graphDFSvsBFS/Resources/tinyG.txt"));
		Graph G = new Graph(in);
		
		
		//adjacency list
		System.out.println("Adjacency List:");
        System.out.println("---------------");
		for (int v = 0; v < G.V(); v++) {
        	System.out.printf("%d: ", v);
        	
        	int j = 0;
	        for (Integer i: G.adj(v)) {
	        	if (j == 0) {
	        		System.out.print(i);
	        	}
	        	else {
	        		System.out.print(" -> " + i);
	        	}
	        	j++;
	        }
	        j = 0;
	        System.out.println();
        }
		
		int source = 1;
		
		DepthFirstPaths dfs = new DepthFirstPaths(G, source);
		BreadthFirstPaths bfp = new BreadthFirstPaths(G, source);
		
		System.out.println();
		
		String[] dfs1 = new String[7];
		String[] bfp1 = new String[7];
		
		int i = 0;
		System.out.printf("%s %28s%n", "Paths DFS:", "Shortest Paths BFS:");
        System.out.printf("%s %23s%n", "---------------", "-------------------");
        for (int v = 0; v < G.V(); v++) {
            if (dfs.hasPathTo(v)) {          	
                for (Integer x : dfs.pathTo(v)) {
                	if (x == source) dfs1[i] = x.toString();
                	else dfs1[i] += ".." + x.toString();
                }
                
                for (Integer x : bfp.pathTo(v)) {
                	if (x == source) bfp1[i] = x.toString();
                	else bfp1[i] += ".." + x.toString();
                }
                
                i++;
            }
        }
        for (int j = 0; j < dfs1.length; j++) {
        	System.out.printf("%-19s %s%n", dfs1[j], bfp1[j]);
        }
	}

}
