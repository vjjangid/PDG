import java.io.File;
import java.io.FileNotFoundException;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.resolution.declarations.ResolvedReferenceTypeDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.logic.FunctionalInterfaceLogic;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

public class GetTypeReference {

	private static final String FILE_PATH = "src/main/resources/BinarySearch.java";
	
	
	public static void main(String[] args) throws FileNotFoundException{

		
			TypeSolver typeSolver = new CombinedTypeSolver();
			
			JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);

			
			CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));
			
			cu.findAll(MethodCallExpr.class).forEach(mce -> 
			System.out.println(mce.resolve().getQualifiedSignature())
			);
			
		
		

	}

}
