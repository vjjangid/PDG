import java.util.Iterator;
import java.util.List;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;

import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class GenerateGraph extends VoidVisitorAdapter<Void> {
	
	/*
	 * public static void main(String args[]) {
	 * 
	 * System.setProperty("org.graphstream.ui", "swing");
	 * 
	 * Graph graph = new SingleGraph("Testing 1"); graph.addNode("A");
	 * graph.addNode("B"); graph.addNode("C"); graph.addEdge("AB" , "A", "B");
	 * graph.addEdge("BC", "B", "C"); graph.addEdge("CA", "C", "A");
	 * 
	 * for(Node node: graph) { node.setAttribute("ui.label", node.getId()); }
	 * graph.display();
	 * 
	 * //"Key", "value" pair Node n = graph.getNode("A"); n.setAttribute("root",
	 * "A");
	 * 
	 * String value = (String) n.getAttribute("root"); System.out.println(value);
	 * 
	 * }
	 */
	
	protected String styleSheet =
            "node {" +
            "	fill-color: black;" +
            "}" +
            "node.marked {" +
            "	fill-color: red;" +
            "}";
	
	
	protected Graph graph = new MultiGraph("Testing Graph");
	
	public GenerateGraph(com.github.javaparser.ast.Node cu) 
	{
		System.setProperty("org.graphstream.ui", "swing");
		
		graph.setAttribute("ui.stylesheet", styleSheet);
		
		graph.setAutoCreate(true);
		graph.setStrict(false);
		
		graph.display();
		
		astPrint(cu, graph);
		
		for (org.graphstream.graph.Node node : graph) {
            node.setAttribute("ui.label", node.getId());
        }
		
		explore(graph.getNode(cu.toString()));
	}
	
	protected void astPrint(com.github.javaparser.ast.Node child2, Graph graph)
	{
		graph.addNode(child2.toString());
		
		List<com.github.javaparser.ast.Node> child = child2.getChildNodes();
		
		for(com.github.javaparser.ast.Node n:child)
		{
			graph.addNode(n.toString());
			graph.addEdge(n.toString(), child2.toString(), n.toString(), true);
		}
		
		child.forEach( (n) -> astPrint(n, graph) );
		
	}
	
	protected void explore(org.graphstream.graph.Node source) {
        Iterator<? extends org.graphstream.graph.Node> k = source.getBreadthFirstIterator();

        while (k.hasNext()) {
            org.graphstream.graph.Node next = k.next();
            next.setAttribute("ui.class", "marked");
            sleep();
        }
    }
	
	protected static void sleep() {
        try { Thread.sleep(1000); } catch (Exception e) {}
    }
	
}
