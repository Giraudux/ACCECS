package fr.univ.nantes.alma.accecs.generator.controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.univ.nantes.alma.accecs.generator.model.*;
import fr.univ.nantes.alma.accecs.generator.model.Variable.RoleVariable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class RecupVar extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Variable> variables = new ArrayList<Variable>();
	

	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
	}
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		Variable variable = null;
		int varNumber = Integer.parseInt(request.getParameter("cptVar"));
		
		
		for(int i=0; i< varNumber; i++){
			String variableName = request.getParameter( "variableName" );
			String variableType = request.getParameter( "variableType" );
			String variableRole = request.getParameter( "variableRole" );
			RoleVariable role = null;
			
			
			switch(variableRole){
			case "input":
				role = RoleVariable.input;
				break;
			case "output":
				role = RoleVariable.output;
				break;
			case "control":
				role = RoleVariable.control;
				break;
			case "internal":
				role = RoleVariable.internal;
				break;
				
			}
			
	
			if(variableType.equals("Integer")){
				int lowerBound = Integer.parseInt(request.getParameter("variableMin"));
				int upperBound = Integer.parseInt(request.getParameter( "variableMax" ));
				int variableInit = Integer.parseInt(request.getParameter( "variableInit" ));
				
				variable = new VariableInteger(variableName, role, upperBound, lowerBound);
			}
			else if(variableType.equals("Float")){
				float lowerBound = Float.parseFloat(request.getParameter("variableMin"));
				float upperBound = Float.parseFloat(request.getParameter( "variableMax" ));
				float variableInit = Float.parseFloat(request.getParameter( "variableInit" ));
				
				variable = new VariableFloat(variableName, role, upperBound, lowerBound);
			} 
			else if(variableType.equals("Boolean")){
				boolean variableInit = Boolean.parseBoolean(request.getParameter( "variableInit" ));
				
				variable = new VariableBoolean(variableName, role);
			}
			
			
			variables.add(variable);
		}
		
		Machine machine = new Machine();
		machine.setName("bb");
		machine.setVariables(variables);
		

		for(Variable v : machine.getVariables()){
			System.out.println(v.getName()+v.getRole());
		}
	   // request.setAttribute( "test", message );
	    //this.getServletContext().getRequestDispatcher( "/WEB-INF/test.jsp" ).forward( request, response );
	    
	}

}
