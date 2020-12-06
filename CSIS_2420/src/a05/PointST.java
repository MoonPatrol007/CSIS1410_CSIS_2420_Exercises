
/**
 * @author Hector_Juarez
 * Write a mutable data type PointST.java that is symbol table with Point2D. 
 * Implement the following API by using a red-black BST 
 * (using either RedBlackBST from algs4.jar or java.util.TreeMap); 
 * do not implement your own red-black BST.
 */
package a05;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;

public class PointST<Value> {
	
	private RedBlackBST<Point2D, Value> bst;
	
	/**
	 * constructor 
	 * for an empty symbol table of points
	 */
	public PointST() {
		this.bst = new RedBlackBST<Point2D, Value>();
	}
	
	/**
	 * is the symbol table empty?
	 * @return
	 */
	public boolean isEmpty() {
		return this.bst.isEmpty();
	}
	
	/**
	 * Size for the number of points
	 * @return
	 */
	public int size() {
		return this.bst.size();
	}
	
	/**
	 * associate the value val with point p
	 * @param p
	 * @param val
	 */
	public void put(Point2D p, Value val) {
		if(p == null) {
			throw new NullPointerException();
		}
		this.bst.put(p, val);
	}
	
	/**
	 * value associated with point
	 * @param p
	 * @return
	 */
	public Value get(Point2D p) { 
		if(p == null) {
			throw new NullPointerException(); 
		}
		return this.bst.get(p);
	}
	
	/**
	 * does the symbol table contain point p?
	 * @param p
	 * @return
	 */
	public boolean contains(Point2D p) {
		if(p == null) {
			throw new NullPointerException("Contains mehtod can't be null");
		}
		return this.bst.contains(p);	 
	}
	
	/**
	 * all points in the symbol table
	 * @return
	 */
	public Iterable<Point2D> points(){ 
		return this.bst.keys();
	}
	
	/**
	 * all points that are inside the rectangle
	 * @param rect
	 * @return
	 */
	public Iterable<Point2D> range(RectHV rect){
		double xMin = rect.xmin();
		double xMax = rect.xmax();
		double yMin = rect.ymin();
		double yMax = rect.ymax();
		
		Queue<Point2D> allVals = (Queue<Point2D>) this.bst.keys();
		Queue<Point2D> rangeVals = new Queue<Point2D>();
		
		for(Point2D vals : allVals) {
			if(vals.x() >= xMin && vals.x() <= xMax) {
				if(vals.y() >= yMin && vals.y() <= yMax) {
					rangeVals.enqueue(vals);
				}
			}
		}
		return rangeVals;
	}
	
	/**
	 * a nearest neighbor to point p
	 * null if the symbol table is empty
	 * @param p
	 * @return
	 */
	public Point2D nearest(Point2D p) { 
		if(p == null) {
			throw new NullPointerException("Nearest method can't be null");
		}
		Point2D nearest = this.bst.max();
		for(Point2D nearPoint : this.bst.keys()) {
			if(p.distanceSquaredTo(nearPoint) < p.distanceSquaredTo(nearest)) {
				nearest = nearPoint;
			}
		}
		return nearest;
	}
	
	/**
	 * unit testing of the methods (not graded)
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO
		
	}

}
