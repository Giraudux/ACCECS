package fr.univ.nantes.alma.accecs.controler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import fr.univ.nantes.alma.accecs.generator.MachineGenerator;
import fr.univ.nantes.alma.accecs.model.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.univ.nantes.alma.accecs.parser.ParserJSON;



public class DataServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
	}
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
			
		System.out.println(request.getParameter("data"));
		ParserJSON parser = new ParserJSON();
		parser.parser(request.getParameter("data"));
		Machine machine = new Machine("bb", parser.getVariables(), parser.getProperties(), parser.getEvents());


		for(Variable v : machine.getVariables()){
			System.out.println(v.getName()+v.getRole());
		}
	   // request.setAttribute( "test", message );
	    //this.getServletContext().getRequestDispatcher( "/WEB-INF/test.jsp" ).forward( request, response );
	    
		MachineGenerator generator = new MachineGenerator();
		generator.generate(machine, new File("src/main/resources/mch/M0.mch"), System.out);
	}

}
