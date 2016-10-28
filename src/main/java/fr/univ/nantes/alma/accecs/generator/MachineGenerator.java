package fr.univ.nantes.alma.accecs.generator;

import fr.univ.nantes.alma.accecs.model.Machine;
import fr.univ.nantes.alma.accecs.model.Property;
import fr.univ.nantes.alma.accecs.model.Variable;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

/**
 *
 */
public class MachineGenerator implements IMachineGenerator {
    private static final Logger LOGGER = Logger.getLogger(MachineGenerator.class.getName());

    public void generate(Machine machine, File templateFile, OutputStream outputStream) {
        JtwigTemplate template = JtwigTemplate.fileTemplate(templateFile);
        JtwigModel model = JtwigModel.newModel();
        Collection<String> variables = new ArrayList<String>();
        Collection<String> invariants = new ArrayList<String>();
        Collection<String> initialisations = new ArrayList<String>();
        Collection<String> properties = new ArrayList<String>();

        for (Variable variable : machine.getVariables()) {
            variables.add(variable.getName());
            invariants.add(variable.getName()+" : "+variable.getType());
            if (variable.hasBounds()) {
                invariants.add(variable.getName()+" : "+variable.getLowerBound()+".."+variable.getUpperBound());
            }
            initialisations.add(variable.getName()+" := "+variable.getDefaultValue());
        }

        for (Property property : machine.getProperties()) {
            properties.add(property.getExpression());
        }

        LOGGER.info("machine = " + machine.toString());
        LOGGER.info("variables = " + variables.toString());
        LOGGER.info("invariants = " + invariants.toString());
        LOGGER.info("initialisation = " + initialisations.toString());

        model.with("name", machine.getName());
        model.with("variables", variables);
        model.with("invariants", invariants);
        model.with("initialisations", initialisations);
        model.with("properties", properties);

        template.render(model, outputStream);
    }
}
