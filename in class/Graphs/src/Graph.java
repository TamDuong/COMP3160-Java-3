/**
 * Abstract class representing a graph.  We extend this class with
 * ListGraph and MatrixGraph classes that implement the adjacency
 * list and adjacency matrix representations.
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public abstract class Graph
{
    protected int numVertices; // number of vertices in the graph

    // Keeps track of visited vertices for depth-first search.  Declared outside the method
    //  so that recursive calls don't reset the array each time.  (An alternate way to do
    //  this would be to have a wrapper method that sets up the array and then calls a
    //  recursive helper method.)
    boolean[] visited;

    
    public Graph(int numVertices)
    {
	this.numVertices = numVertices;
	visited = new boolean[numVertices];
    }

    // Returns the number of vertices in the graph
    public int getNumVertices()
    {
	return numVertices;
    }

    // Reads edge information from a file. Each line of the file should be
    //  formatted like this:
    // [startVertex] [endVertex] [optional weight]
    public void readEdgesFromFile(String fileName)
    {
	try {
	    Scanner s = new Scanner(new File(fileName));
	    while (s.hasNext()) {
		String next = s.nextLine();
		String[] tokens = next.split("\\s+");
		if (tokens.length == 2)
		    addEdge(new Edge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])));
		else
		    addEdge(new Edge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Double.parseDouble(tokens[2])));
	    }
	    s.close();
	} catch (FileNotFoundException e) {
	    System.out.println(fileName + " was not found!");
	    System.exit(1);
	}
    }

    // Returns an iterator over the Edges incident to the given vertex
    public abstract Iterator<Edge> edgeIterator(int vertex);

    // Adds the specified Edge into the graph
    public abstract void addEdge(Edge e);


    // Performs a depth-first search on the graph from the specified start vertex.
    public void depthFirstSearch(int startVertex)
    {
	System.out.println("I am now visiting vertex " + startVertex);
	visited[startVertex] = true;

	// for each unvisited neighbor, perform a depth-first search starting from that vertex
	Iterator<Edge> it = edgeIterator(startVertex);
	while (it.hasNext()) {
	    Edge nextEdge = it.next();
	    int endVertex = nextEdge.getEndVertex();
	    if (!visited[endVertex]) {
		depthFirstSearch(endVertex);
	    }
	}
    }
    
    // Performs a breadth-first search on the graph from the specified start vertex.
    public void breadthFirstSearch(int startVertex)
    {
	boolean[] identified = new boolean[numVertices];
	boolean[] visited = new boolean[numVertices];
	Queue<Integer> q = new LinkedList<Integer>();
	
	identified[startVertex] = true;
	q.add(startVertex);
	while (!q.isEmpty()) {
	    int nextVertex = q.poll();
	    System.out.println("I am now visiting vertex " + nextVertex);
	    Iterator<Edge> it = edgeIterator(nextVertex);
	    while (it.hasNext()) {
		Edge nextEdge = it.next();
		int endVertex = nextEdge.getEndVertex();
		if (!identified[endVertex] && !visited[endVertex]) {
		    identified[endVertex] = true;
		    q.add(endVertex);
		}
	    }
	    visited[nextVertex] = true;
	}
    }
    
    
    public String toString()
    {
	String r = "Graph containing these edges:\n";
	for (int i = 0; i < numVertices; i++) {
	    Iterator<Edge> it = edgeIterator(i);
	    while (it.hasNext())
		r += it.next() + "\n";
	}
	return r;
    }
}
