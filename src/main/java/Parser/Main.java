package Parser;

import java.io.File;
import java.io.FileInputStream;




public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String curDir = System.getProperty("user.dir");
		File file = new File(curDir+"/src/main/java/fichiertest.txt");
		FileInputStream fis = null;
		
	   
		
		try {
			fis = new FileInputStream(file);
		 
		ParserM m = new ParserM(fis);
		m.parser();
		m.afficherTous();
		m.variableCreation();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
