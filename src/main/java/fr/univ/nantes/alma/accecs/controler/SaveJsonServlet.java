package fr.univ.nantes.alma.accecs.controler;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;


public class SaveJsonServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(SaveJsonServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String jsonContent = "";
        try {
            jsonContent = request.getParameter("data");
        } catch (Exception e) {
            throw new ServletException(e);
        }
      response.setContentType("application/json");
      response.setHeader("Content-Disposition", "attachment; fileName=data.json");
      ServletOutputStream out = response.getOutputStream();
      out.print(jsonContent);
      out.flush();
      out.close();
    }
}
