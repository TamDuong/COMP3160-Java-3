/**
 * This class represents one edge in a graph. The class stores the starting and
 * ending vertices (as integers) and the weight of the edge.
 *
 */
public class Edge
{
    private int startVertex, endVertex;
    private double weight = 1.0;

    // Accessors
    public int getStartVertex()
    {
	return startVertex;
    }

    public int getEndVertex()
    {
	return endVertex;
    }

    public double getWeight()
    {
	return weight;
    }

    public Edge(int startVertex, int endVertex)
    {
	this.startVertex = startVertex;
	this.endVertex = endVertex;
    }

    public Edge(int startVertex, int endVertex, double weight)
    {
	this(startVertex, endVertex);
	this.weight = weight;
    }

    public boolean equals(Object o)
    {
	if (o instanceof Edge) {
	    Edge e = (Edge) o;
	    return	this.getStartVertex() == e.getStartVertex()
		    	&& this.getEndVertex() == e.getEndVertex();
	}
	return false;
    }

    public String toString()
    {
	return startVertex + "-" + endVertex + " (weight = " + weight + ")";
    }
}
