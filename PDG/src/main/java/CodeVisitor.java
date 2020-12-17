import java.util.List;
import java.util.Stack;
import java.util.ArrayList;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class CodeVisitor extends VoidVisitorAdapter<Void>{
		
		void astPrint(Node child2, int level)
		{
			List<Node> child = child2.getChildNodes();
			System.out.println(level + " Node info :: \n " + child);
			
			child.forEach( (n) -> astPrint(n, level+1) );
			
		}
		
}
