import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class HelpingGraph extends VoidVisitorAdapter<Void> {

	private static final String FILE_PATH = "src/main/resources/Factorial.java";
	
	protected static String styleSheet =
            "node {" +
            "	fill-color: black;" +
            "}" +
            "node.marked {" +
            "	fill-color: red;" +
            "}";
	
	
	public static void astPrint(Node child2, int level, Graph graph)
	{
		graph.addNode(child2.toString());
		
		List<Node> child = child2.getChildNodes();
		
		for(Node n:child)
		{
			graph.addNode(n.toString());
			graph.addEdge(n.toString(), child2.toString(), n.toString(), true);
		}
		
		child.forEach( (n) -> astPrint(n, level+1, graph) );
		
	}
	
	public static void main(String args[])
	{
		CompilationUnit cu = null;
		
		try {
			cu =  StaticJavaParser.parse(new File(FILE_PATH));;
		}
		catch(Exception e)
		{
			System.out.println("Syntactic error :: " +e.getMessage() );
		}
		
		Graph graph = new MultiGraph("Testing Graph");
		
		System.setProperty("org.graphstream.ui", "swing");
		
		graph.setAttribute("ui.stylesheet", styleSheet);
		
		graph.setAutoCreate(true);
		graph.setStrict(false);
		
		graph.display();
		
		System.out.println("Hello Before");
		
		astPrint(cu, 0, graph);
		
		for (org.graphstream.graph.Node node : graph) {
            node.setAttribute("ui.label", node.getId());
        }
		
		explore(graph.getNode(cu.toString()));
	}
	
	
	public static void explore(org.graphstream.graph.Node source) {
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
	
	public HelpingGraph()
	{
		
		
		
	}
	
	
}
