package fr.univ.nantes.alma.accecs.controler;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.logging.Logger;


public class SaveJsonServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1029224371829063600L;
	//private static final Logger LOGGER = Logger.getLogger(SaveJsonServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String jsonContent = "";
    	 String pattern = "ddMMyyyyHHmmss";
         SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            jsonContent = request.getParameter("data");
            String machineName = request.getParameter("machine");
            if(machineName==null || machineName.isEmpty()) machineName = "machine";
            String filename = machineName + '_' + format.format(new Date()) + ".json";
            response.setContentType("application/json");
            response.setHeader("Content-Disposition", "attachment; fileName="+filename);
            ServletOutputStream out = response.getOutputStream();
            out.print(jsonContent);
            out.flush();
            out.close();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
