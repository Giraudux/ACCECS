package fr.univ.nantes.alma.accecs.generator;

import fr.univ.nantes.alma.accecs.generator.model.Machine;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 */
public class MachineGenerator implements IMachineGenerator {

    public void generate(Machine machine, String templateLocation, OutputStream outputStream) {
        JtwigTemplate template = JtwigTemplate.classpathTemplate(templateLocation);
        JtwigModel model = JtwigModel.newModel();
        Collection<String> variables = new ArrayList<String>();
        Collection<String> invariants = new ArrayList<String>();
        Collection<String> initialisations = new ArrayList<String>();

        model.with("name", machine.getName());
        model.with("variables", variables);
        model.with("invariants", invariants);
        model.with("initialisations", initialisations);

        template.render(model, outputStream);
    }
}
