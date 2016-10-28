package fr.univ.nantes.alma.accecs.controler;

import fr.univ.nantes.alma.accecs.generator.MachineGenerator;
import fr.univ.nantes.alma.accecs.model.Machine;
import fr.univ.nantes.alma.accecs.parser.ParserJSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;


public class DataServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(DataServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ParserJSON parser = new ParserJSON();
        try {
            parser.parse(request.getParameter("data"));
        } catch (Exception e) {
            throw new ServletException(e);
        }

        Machine machine = new Machine(parser.getName(), parser.getVariables(), parser.getProperties(), parser.getEvents());
        MachineGenerator generator = new MachineGenerator();
        File template = new File(getServletContext().getRealPath("/M0.mch"));
        generator.generate(machine, template, response.getOutputStream());
    }
}
