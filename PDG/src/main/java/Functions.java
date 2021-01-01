		

/* ----- First we need all the functions present in the file
		 since we are working on method level granularity ------- */


/*	----- Problem stil need to be solved 
 		How we differ function in case function overloading ??
 		since we are getting function name only
 */

import com.github.javaparser.Position;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * 		What do we need to check whether the two function are same
		1.) return_type == Matters
		2.) function_name == Doesn't matter
		3.) parameters and its type == Matters
*/

class Function_Details
{
	Node Node_Address;
	String func_name = ""; //Name of function
	int start = -1; //For range or size of function in real code ==> start range
	int end = -11; // end range
	String return_Type = ""; //return type of function ==> last second node of root node or further splited function main node
	List<Parameter> func_Parameters; //List for parameters of function
}

public class Functions extends VoidVisitorAdapter<Void> {
	
	ArrayList<Function_Details> functions = new ArrayList<Function_Details>();
	
	Functions(CompilationUnit cu, Void arg)
	{
		visit(cu, arg);
	}
	
	void print_Function_Details()
	{
		for(Function_Details i: functions)
		{
			System.out.println("Function name :: " + i.func_name + " , Function Start Line :: " +i.start + " , Function End Line :: " +i.end);
		}
	}
	
	ArrayList<Function_Details> get_Function_Details()
	{
		return functions;
	}
	
	@Override
	public void visit(MethodDeclaration md, Void arg)
	{
		super.visit(md, arg);
		Function_Details temp = new Function_Details();
		temp.Node_Address = md;
		temp.func_name = md.getNameAsString();
		
		//start.line , start.column
		Optional<Position> start = md.getBegin();
		Position posStart = start.get();
		temp.start = posStart.line;
		
		Optional<Position> end = md.getEnd();
		Position posEnd = end.get();
		temp.end = posEnd.line;
		
		//return type of function
		temp.func_Parameters = md.getParameters();
		
		for(Node i: temp.func_Parameters)
		{
			System.out.println(i);
		}
		temp.return_Type = md.getTypeAsString();
		
		functions.add(temp);
		
	}

}
