import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import java.io.File;

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
		
		
		
		//System.out.println(cu);
		
		CodeVisitor methodNameVisitor = new CodeVisitor();
		//methodNameVisitor.visit(cu, null);
		methodNameVisitor.astPrint(cu,0);
	}
}
