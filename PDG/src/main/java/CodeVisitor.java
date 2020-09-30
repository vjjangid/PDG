import java.util.List;
import java.util.ArrayList;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class CodeVisitor extends VoidVisitorAdapter<Void>{
	
		CodeVisitor()
		{
			
		}
		
		@Override
		public void visit(MethodDeclaration md, Void arg)
		{
			super.visit(md, arg);
			System.out.println("Method Name Printed: " + md.getName());
			System.out.println("Body :: \n" +md.getBody());
			System.out.println("Child Nodes are :: \n" + md.getChildNodes());
			System.out.println("Get Data :: \n" + md.getData(null));
			
		}
		
		void astPrint(Node child2, int level)
		{
			List<Node> child = child2.getChildNodes();
			System.out.println(level + " Node info :: \n " + child);
			
			child.forEach( (n) -> astPrint(n, level+1) );
			
		}
		
}
