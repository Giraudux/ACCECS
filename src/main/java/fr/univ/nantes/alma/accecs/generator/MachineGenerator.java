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
        Collection<String> variablesInput = new ArrayList<String>();
        Collection<String> variablesOutput = new ArrayList<String>();
        Collection<String> variablesControl = new ArrayList<String>();
        Collection<String> variablesInternal = new ArrayList<String>();
        Collection<String> invariants = new ArrayList<String>();
        Collection<String> initialisations = new ArrayList<String>();
        Collection<String> properties = new ArrayList<String>();

        for (Variable variable : machine.getVariables()) {
            //TODO Changer pour category variable
        	switch (variable.getCategory()) {
                case INPUT:
                    variablesInput.add(variable.getName());
                    break;
                case OUTPUT:
                    variablesOutput.add(variable.getName());
                    break;
                case CONTROL:
                    variablesControl.add(variable.getName());
                    break;
                case INTERNAL:
                    variablesInternal.add(variable.getName());
                    break;
            }
            invariants.add(variable.getName()+" : "+variable.getType());
            if (variable.hasBounds()) {
                invariants.add(variable.getName()+" : "+variable.getLowerBound()+".."+variable.getUpperBound());
            }
            initialisations.add(variable.getName()+" := "+variable.getDefaultValue());
        }

        for (Property property : machine.getProperties()) {
            properties.add(property.getExpression());
        }

        model.with("name", machine.getName());
        model.with("variablesInput", variablesInput);
        model.with("variablesOutput", variablesOutput);
        model.with("variablesControl", variablesControl);
        model.with("variablesInternal", variablesInternal);
        model.with("invariants", invariants);
        model.with("initialisations", initialisations);
        model.with("properties", properties);

        template.render(model, outputStream);
    }
}
