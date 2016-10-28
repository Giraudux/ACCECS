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

            /*if (variable instanceof VariableInteger) {
                invariants.add(variable.getName() + " : INT");
                initialisations.add(variable.getName() + " := " + ((VariableIntegerOld) variable).getValeurDefaut());
                properties.add(variable.getName()+" : "+((VariableIntegerOld) variable).getBorneMin()+".."+((VariableIntegerOld) variable).getBorneMax());
            } else if (variable instanceof VariableFloat) {
                invariants.add(variable.getName() + " : NAT");
                initialisations.add(variable.getName() + " := " + ((VariableFloatOld) variable).getValeurDefaut());
                properties.add(variable.getName()+" : "+((VariableFloatOld) variable).getBorneMin()+".."+((VariableFloatOld) variable).getBorneMax());
            } else if (variable instanceof VariableBoolean) {
                invariants.add(variable.getName() + " : BOOL");
                initialisations.add(variable.getName() + " := " + (((VariableBooleanOld) variable).isValeurDefaut() ? "TRUE" : "FALSE"));
            }*/
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
