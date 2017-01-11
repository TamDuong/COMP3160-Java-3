public class Client
{
    public static void main(String[] args)
    {
	Graph g = new ListGraph(7);
	g.readEdgesFromFile("graph2.txt");
	System.out.println(g);
	g.depthFirstSearch(4);
    }
}
