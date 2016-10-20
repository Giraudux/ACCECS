package fr.univ.nantes.alma.accecs.generator;

import fr.univ.nantes.alma.accecs.model.*;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 */
public class MachineGenerator implements IMachineGenerator {

    public void generate(Machine machine, File templateFile, OutputStream outputStream) {
        JtwigTemplate template = JtwigTemplate.fileTemplate(templateFile);
        JtwigModel model = JtwigModel.newModel();
        Collection<String> variables = new ArrayList<String>();
        Collection<String> invariants = new ArrayList<String>();
        Collection<String> initialisations = new ArrayList<String>();

        for (Variable variable : machine.getVariables()) {
            variables.add(variable.getName());

            if (variable instanceof VariableInteger) {
                invariants.add(variable.getName() + " : INT");
                initialisations.add(variable.getName() + " := " + ((VariableInteger) variable).getValeurDefaut());
                invariants.add(variable.getName()+" : "+((VariableInteger) variable).getBorneMin()+".."+((VariableInteger) variable).getBorneMax());
            } else if (variable instanceof VariableFloat) {
                invariants.add(variable.getName() + " : NAT");
                initialisations.add(variable.getName() + " := " + ((VariableFloat) variable).getValeurDefaut());
                invariants.add(variable.getName()+" : "+((VariableFloat) variable).getBorneMin()+".."+((VariableFloat) variable).getBorneMax());
            } else if (variable instanceof VariableBoolean) {
                invariants.add(variable.getName() + " : BOOL");
                initialisations.add(variable.getName() + " := " + (((VariableBoolean) variable).isValeurDefaut() ? "TRUE" : "FALSE"));
            }
        }

        for (Property property : machine.getProperties()) {
            invariants.add(property.getExpression());
        }


        model.with("name", machine.getName());
        model.with("variables", variables);
        model.with("invariants", invariants);
        model.with("initialisations", initialisations);

        template.render(model, outputStream);
    }
}
