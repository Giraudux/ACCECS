package Parser;

import java.io.File;
import java.io.FileInputStream;




public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file = new File("/comptes/E129211X/M2/CAPSTON/ConstructionCECS/Parser/test.txt");
		FileInputStream fis = null;

		
		try {
			fis = new FileInputStream(file);
		 
		ParserM m = new ParserM(fis);
		m.parser();
		m.afficherTous();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
