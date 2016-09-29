package Parser;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class ParserM {

	private InputStream flux;
	private char separateurFin;
	private List<String> proprietes = new ArrayList<String>();
	private List<String> vars = new ArrayList<String>();
	private List<String> evts = new ArrayList<String>();
	
	ParserM(InputStream flux){
		this.flux = flux;
		separateurFin = ';';
	}
	
	public void parser() throws IOException{
		InputStream fluxProp = new BufferedInputStream(flux);
		int content;
		StringBuffer bf = new StringBuffer(""); 
		//lie charactère par charactère et vérifie que le flux n'est pas terminé
		while ((content = fluxProp.read()) != -1) {
			//Cast en char et vérifié 
			if((char) content != separateurFin){
				bf.append((char)content);
			}
			//Sinon on ajoute la propriete
			else{							
				String res;
				res = bf.toString().trim();				
				int pos = res.indexOf("PROP");
				//System.out.println(posPROP);
				if(pos == 0){
					res = bf.toString().replaceAll("PROP", "");
					res = res.trim();
					res = res.replaceAll("\n",""); 
					proprietes.add(res);
				}else{
					pos = res.indexOf("VAR");
					if(pos == 0){
						res = bf.toString().replaceAll("VAR", "");
						res = res.trim();
						res = res.replaceAll("\n",""); 
						vars.add(res);
					}
					else{
						pos = res.indexOf("EVT");
						if(pos == 0){
							res = bf.toString().replaceAll("EVT", "");
							res = res.trim();
							res = res.replaceAll("\n",""); 
							evts.add(res);
						}
						else{
							System.out.println("Ligne non valide");
						}
					}
				}
				bf = new StringBuffer(""); 
				
			}
			
		}
		
		
		
	}
	
	public void afficherTous(){
		for (String var : vars){
			System.out.println(var+" ");
		}
		System.out.println("-----------------------------------------");
		for (String prop : proprietes){
			System.out.println(prop+" ");
		}
		System.out.println("-----------------------------------------");
		for (String evt : evts){
			System.out.println(evt+" ");
		}
		System.out.println("-----------------------------------------");
	}
	public void parserProp() throws IOException{
		InputStream fluxProp = new BufferedInputStream(flux);
		int content;
		StringBuffer bf = new StringBuffer(""); 
		while ((content = fluxProp.read()) != -1) {
			// convert to char and display it
			if((char) content != separateurFin){
				bf.append((char)content);
			}
			else{				
				
				String res = bf.toString().replaceAll("PROP", "");
				System.out.println(res);
			}
			
		}
	}
	/*
	private void parserVar(){
		
	}
	
	private void parserEvt(){
		
	}
	*/
	
}
