package controleur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import modele.*;
import modele.Variable.RoleVariable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import Parser.ParserJSON;



public class dataServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
	}
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		ParserJSON parser = new ParserJSON();
		parser.parser(request.getParameter(""));
		Machine machine = new Machine("bb", parser.getVariables(), parser.getProperties(), parser.getEvents());


		for(Variable v : machine.getVariables()){
			System.out.println(v.getName()+v.getRole());
		}
	   // request.setAttribute( "test", message );
	    //this.getServletContext().getRequestDispatcher( "/WEB-INF/test.jsp" ).forward( request, response );
	    
	}

}
