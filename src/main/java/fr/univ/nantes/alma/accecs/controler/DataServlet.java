package fr.univ.nantes.alma.accecs.controler;

import java.io.File;
import java.io.IOException;

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
	private String resultat = "aucune erreur.";
	

	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
	}
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
			
		ParserJSON parser = new ParserJSON();
		
		try{
			parser.parse(request.getParameter("data"));
		}catch(NumberFormatException e){
			this.resultat = "Une erreur lors de l'ecriture des variables";
			request.setAttribute( "resultat", this.resultat );
			this.getServletContext().getRequestDispatcher( "/index.jsp" ).forward( request, response );
		}
		
		
		Machine machine = new Machine("Machine0", parser.getVariables(), parser.getProperties(), parser.getEvents());
	    
		for(Variable each : machine.getVariables()){
			System.out.println(each.toString());
		}
		
		MachineGenerator generator = new MachineGenerator();
		File template = new File("C:/Users/Geof/workspace_JEE/ACCECS/src/main/resources/mch/M0.mch");
		generator.generate(machine, template, System.out);
	}

}
