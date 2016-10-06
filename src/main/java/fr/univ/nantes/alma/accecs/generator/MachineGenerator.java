package fr.univ.nantes.alma.accecs.generator;

import fr.univ.nantes.alma.accecs.model.Machine;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.OutputStream;

/**
 *
 */
public class MachineGenerator implements IMachineGenerator {

    public void generate(Machine machine, String templateLocation, OutputStream outputStream) {
        //TODO: fix template location path
        JtwigTemplate template = JtwigTemplate.classpathTemplate(templateLocation);
        JtwigModel model = JtwigModel.newModel();
        model.with("name", machine.getName());
        template.render(model, outputStream);
    }
}
