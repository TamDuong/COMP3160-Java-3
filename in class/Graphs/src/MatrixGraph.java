/**
 * Implementation of a graph using an adjacency matrix.  The graph data is stored
 * as a 2-D array of doubles.  Element [i][j] (row i, column j) in this matrix
 * stores the weight of the edge connecting vertex i to vertex j.  A value of 0
 * indicates no edge between those two vertices.
 * 
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixGraph extends Graph
{
    // Nested class that implements an iterator over the edges incident to a given
    //  start vertex.
    private class EdgeIterator implements Iterator<Edge>
    {
	// startVertex = the vertex from which we want the iterator to get incident edges (i.e., which row in the data array)
	// currentVertex = tracks how far down that row the iterator has already advanced
	private int startVertex, currentVertex;
	
	public EdgeIterator(int startVertex)
	{
	    this.startVertex = startVertex;
	    
	    // (remember currentVertex has a default value of 0)
	}
	
	public boolean hasNext()
	{
	    double[] thisRow = data[startVertex];
	    
	    // check the current row to see if there are any more nonzero elements
	    for (int i = currentVertex; i < thisRow.length; i++) {
		if (thisRow[i] != 0)
		    return true;
	    }
	    return false;
	}
	
	public Edge next()
	{
	    if (hasNext()) {
		double[] thisRow = data[startVertex];
		while (thisRow[currentVertex] == 0)	// look for the next nonzero element in this row
		    currentVertex++;
		currentVertex++;			// needed so subsequent calls to next() don't get stuck at this position
		
		return new Edge(startVertex, currentVertex-1, thisRow[currentVertex-1]);
	    } else
		throw new NoSuchElementException();
	}
	
	public void remove()
	{
	    throw new UnsupportedOperationException();
	}
    }
    
    private double[][] data;
   
    public MatrixGraph(int numVertices)
    {
	super(numVertices);
	data = new double[numVertices][numVertices];
    }
    
    @Override
    public Iterator<Edge> edgeIterator(int vertex)
    {
	return new EdgeIterator(vertex);
    }

    @Override
    public void addEdge(Edge e)
    {
	int 	startVertex = e.getStartVertex(),	// extract the relevant info from the Edge object
		endVertex = e.getEndVertex();
	double	weight = e.getWeight();

	if (data[startVertex][endVertex] == 0)	// check to make sure an edge doesn't already exist at that location
	    data[startVertex][endVertex] = weight;
    }
}
