import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import java.io.File;
import java.util.ArrayList;

public class Main {
	
	private static final String FILE_PATH = "src/main/resources/Factorial.java";
	
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
		
		
		
		Functions getCalls = new Functions(cu, null);
		getCalls.print_Function_Details();
		ArrayList<Function_Details> func_Details = getCalls.get_Function_Details();
		
		for(Function_Details n: func_Details)
		{
			GenerateGraph graph = new GenerateGraph(n.Node_Address);
		}
		
		/*
		 * CodeVisitor traverse = new CodeVisitor();
		 * traverse.astPrint(func_Details.get(0).Node_Address, 0);
		 */
		
	}
}
