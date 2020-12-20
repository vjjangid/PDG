import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;

import java.io.File;
import java.util.ArrayList;

public class Main {

	private static final String FILE_PATH = "src/main/resources/BubbleSort.java";

	public static void main(String args[]) {

		CompilationUnit cu = null;

		try {
			
			TypeSolver typeSolver = new CombinedTypeSolver();
			JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
			ParserConfiguration parserConfiguration = new ParserConfiguration().setAttributeComments(false);
			StaticJavaParser.setConfiguration(parserConfiguration);
			StaticJavaParser.getConfiguration().setSymbolResolver(symbolSolver);
			cu = StaticJavaParser.parse(new File(FILE_PATH));
			
		} 
		catch (Exception e) 
		{
			System.out.println("Syntactic error :: " + e.getMessage());
		}

		Functions getCalls = new Functions(cu, null);
		getCalls.print_Function_Details();
		ArrayList<Function_Details> func_Details = getCalls.get_Function_Details();

		
		for(Function_Details n: func_Details)
		{	
			GenerateGraph graph = new GenerateGraph(n.Node_Address);
	   		break; 
		}
		
		
		ResolvingReferences check = new ResolvingReferences(func_Details.get(0).Node_Address);

		
		/*
		 * CodeVisitor traverse = new CodeVisitor();
		 * traverse.astPrint(func_Details.get(0).Node_Address, 0);
		 */

	}
}
