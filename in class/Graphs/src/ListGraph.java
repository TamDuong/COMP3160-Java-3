/**
 * Implementation of a graph using an adjacency list.  We maintain an array list
 * of linked lists.  Each index in this array list corresponds to one vertex in the 
 * graph.  The linked list at each index contains all of the edges that are incident
 * to that vertex.
 * 
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class ListGraph extends Graph
{
    private ArrayList<LinkedList<Edge>> data = new ArrayList<>();
    
    public ListGraph(int numVertices)
    {
	super(numVertices);
	for (int i = 0; i < numVertices; i++)	// add the correct number of empty LinkedLists to the array list
	    data.add(new LinkedList<Edge>());
    }
    
    @Override
    public Iterator<Edge> edgeIterator(int vertex)
    {
	return data.get(vertex).iterator();
    }

    @Override
    public void addEdge(Edge e)
    {
	LinkedList<Edge> currentList = data.get(e.getStartVertex());
	if (!currentList.contains(e))	// don't want to add duplicate edges
	    currentList.add(e);
    }
}
