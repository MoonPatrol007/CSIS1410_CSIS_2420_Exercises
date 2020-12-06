/**
 * @author Hector_Juarez
 * 
 * Write a mutable data type KdTreeST.java that uses a 2d-tree to implement the same API 
 * (but renaming PointST to KdTreeST). 
 * A 2d-tree is a generalization of a BST to two-dimensional keys. 
 * The idea is to build a BST with points in the nodes, using the x- and y-coordinates of 
 * the points as keys in strictly alternating sequence, starting with the x-coordinates.
 */
package a05;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

// this is to tes the code in the main method.
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

@SuppressWarnings("unused")
public class KdTreeST2 <Value> {
	private int size;
	private Node root;
	
	/**
	 * Private helper class
	 * Since we don't need to implement the rank and select operations, 
	 * there is no need to store the subtree size.
	 *
	 */
	private class Node {
		private Point2D p; // the point
		private Value value; // the symbol table maps the point to this value
		private RectHV rect; // the axis-aligned rectangle corresponding to this node
		
		// a link to the left/bottom and right/top Subtrees
		private Node lb;
		private Node rt; 
		
		public Node(Point2D p, Value value, RectHV rect) {
			this.p = p;
			this.value = value;
			this.rect = rect;
		}
	}
	
	/**
	 * Constructor 
	 * An empty symbol table of points
	 */
	public KdTreeST2() {
		size = 0;
		root = null;
	}
	
	/**
	 * is the symbol table empty?
	 * @return 
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * number of points in the symbol table
	 * @return 
	 */
	public int size() {
		return size;
	}
	
	/**
	 * HashTable
	 * A simplified version of put() which does everything except set up the RectHV for each node.
	 * Associate the value with point p
	 * 
	 * put() is best implemented by using private helper methods analogous to those found on 
	 * page 399 of the book or by looking at BST.java. 
	 * We recommend using the orientation (vertical or horizontal) as an argument to these helper methods.
	 * 
	 * Add code to put() which sets up the RectHV for each Node.
	 * @param p Point2D 
	 * @param val Value
	 */
	public void put(Point2D p, Value value) {
		if (p == null || value == null) {
			throw new NullPointerException("put method can't be null");
		}
		root = put(null, root, p, value, true);
	}
	
	/** 
	 * private helper method for put
	 * 
	 * @param parent
	 * @param node
	 * @param p
	 * @param value
	 * @param vertical
	 * @return
	 */
	private Node put(Node parent, Node node, Point2D p, Value value, boolean vertical) {
		if (node == null) {
			size++;
			return new Node(p, value, createNewRectangle(parent, p, vertical));
		}
		
		double compare = compareXOrY(node, p, vertical);
		
		if (compare < 0) {
			node.lb = put(node, node.lb, p, value, !vertical);
		} else if (compare > 0) {
			node.rt   = put(node, node.rt,   p, value, !vertical);
		} else if (node.p.equals(p)) {
			node.value = value;
		} else {
			node.rt = put(node, node.rt, p, value, !vertical);
		}
		
		return node;
	}
	
	/**
	 * HashTable
	 * This get will test that put() was implemented properly.
	 * 
	 * get() is best implemented by using private helper methods analogous to those found 
	 * on page 399 of the book or by looking at BST.java. 
	 * We recommend using the orientation (vertical or horizontal) as an argument to these helper methods.
	 * @param p Point2D 
	 * @return 
	 */
	public Value get(Point2D p) {
		if (p == null) {
			throw new NullPointerException("get method can't be null");
		}
		return get(root, p, true);
	}
	
	/**
	 * Private helper method for get hashtable
	 * @param node
	 * @param p
	 * @param vertical
	 * @return
	 */
	private Value get(Node node, Point2D p, boolean vertical) {
		if (node == null) {
			return null;
		}
		
		double compare = compareXOrY(node, p, vertical); 
		
		if (compare < 0) {
			return get(node.lb, p, !vertical);
		} else if (compare > 0) {
			return get(node.rt, p, !vertical);
		} else if (node.p.equals(p)) {
			return node.value;
		} else {
			return get(node.rt, p, !vertical);
		}
	}
	
	/**
	 * Does the symbol table contain point p?
	 * test that put() was implemented properly.
	 * @param p
	 * @return
	 */
	public boolean contains(Point2D p) {
		if (p == null) {
			throw new NullPointerException("Contains method can't be null");
		}
		return get(p) != null;
	}
	
	/**
	 * This is to check the structure of the kd-tree.
	 * @return Iterable all points in the symbol table in level order
	 */
	public Iterable<Point2D> points() {
		Queue<Point2D> queueToReturn = new Queue<>();
		
		if (size == 0) {
			return queueToReturn;
		}
		
		Queue<Node> traversalQueue = new Queue<>();
		
		// Traversal 
		traversalQueue.enqueue(root);
		
		while (!traversalQueue.isEmpty()) {
			Node temp = traversalQueue.dequeue();
			queueToReturn.enqueue(temp.p);
			
			if (temp.lb != null) {
				traversalQueue.enqueue(temp.lb);
			}
			
			if (temp.rt != null) {
				traversalQueue.enqueue(temp.rt);
			}
		}
		return queueToReturn;
	}
	
	/**
	 * all points that are inside the rectangle to 
	 * test implementation RangeSeachVisualizer.java
	 * 
	 * Instead of checking whether the query rectangle intersects the rectangle corresponding to a node, 
	 * it suffices to check only whether the query rectangle intersects the splitting line segment: 
	 * if it does, then recursively search both subtrees; otherwise, recursively search the one subtree 
	 * where points intersecting the query rectangle could be.
	 * 
	 * @param rect rectangle to search inside the tree
	 * @return
	 */
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) {
			throw new NullPointerException("range mehtod can't be null");
		}
		
		// Generic 
		Queue<Point2D> pointsInsideRectangle = new Queue<>();
		range(rect, pointsInsideRectangle, root);
		
		return pointsInsideRectangle;
	}
	
	/**
	 * 
	 * @param rect
	 * @param pointsInsideRectangle
	 * @param node
	 */
	private void range(RectHV rect, Queue<Point2D> pointsInsideRectangle, Node node) {
		if (node == null) return;
		if (!rect.intersects(node.rect)) return;
		if (rect.contains(node.p)) pointsInsideRectangle.enqueue(node.p);
		range(rect, pointsInsideRectangle, node.lb);
		range(rect, pointsInsideRectangle, node.rt);
	}
	
	/**
	 * A nearest neighbor to point p; 
	 * null if the symbol table is empty
	 * 
	 * test implementation NearestNeighborVisualizer.java
	 * 
	 * @param p neighbor point to check against
	 * @return point closest to p
	 */
	public Point2D nearest(Point2D p) {
		if (p == null) {
			throw new NullPointerException("Nearest method can't be null");
		}
		return nearest(p, root, root.p);
	}
	
	/**
	 * 
	 * @param p
	 * @param node
	 * @param winningPoint
	 * @return
	 */
	private Point2D nearest(Point2D p, Node node, Point2D winningPoint) {
		if (node == null) {
			return winningPoint;
		}
		
		/**
		 * Whenever you need to compare two Euclidean distances, it is often more efficient to compare the 
		 * squares of the two distances to avoid the expensive operation of taking square roots.
		 *  
		 * distanceSquaredTo() is faster and doesn't use Math.sqrt()
		 */
		if (node.rect.distanceSquaredTo(p) > winningPoint.distanceSquaredTo(p)) {
			return winningPoint;
		}
		if (p.distanceSquaredTo(node.p) < p.distanceSquaredTo(winningPoint)) {
			winningPoint = node.p;
		
		}
		
		if (node.lb != null && node.lb.rect.contains(p)) { 
			// Be moving towards the point
			winningPoint = nearest(p, node.lb, winningPoint);
			winningPoint = nearest(p, node.rt, winningPoint);
		} else {
			winningPoint = nearest(p, node.rt, winningPoint);
			winningPoint = nearest(p, node.lb, winningPoint);
		}
		return winningPoint;
	}
	
	/**
	 * Compare either x or y depending on if it's vertical or horizontal
	 * @param node
	 * @param p
	 * @param Vertical
	 * @return
	 */
	private double compareXOrY(Node node, Point2D p, boolean Vertical) {
		if (Vertical) {
			return p.x() - node.p.x(); 
		} else {
			return p.y() - node.p.y();
		}
	}
	
	/**
	 * private helper method to calculate the rectangle for put hashtable
	 * Where the parent's y-min, y-max, x-min, and x-max will copy the 
	 * rest of the rectangle. 
	 * @param parent
	 * @param p
	 * @param Vertical
	 * @return
	 */
	private RectHV createNewRectangle(Node parent, Point2D p, boolean Vertical) {
		if (parent == null) {
			return new RectHV(-Double.MAX_VALUE, -Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
		}
		
		double compare = compareXOrY(parent, p, !Vertical);
		
		if (Vertical  && compare >= 0) {
			return new RectHV(parent.rect.xmin(), parent.p.y(), parent.rect.xmax(), parent.rect.ymax());
		}
		if (Vertical  && compare <  0) {
			return new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.rect.xmax(), parent.p.y());
		}
		if (!Vertical && compare >= 0) {
			return new RectHV(parent.p.x(), parent.rect.ymin(), parent.rect.xmax(), parent.rect.ymax());
		}
		if (!Vertical && compare <  0) {
			return new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.p.x(), parent.rect.ymax());
		
		}
		return null;
	}
	
	public static void main(String[] args) {
		// TODO to test code
	}
}

