package fr.univ.nantes.alma.accecs.generator;

import fr.univ.nantes.alma.accecs.model.*;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
//import java.util.logging.Logger;

/**
 *
 */
public class MachineGenerator implements IMachineGenerator {
    //private static final Logger LOGGER = Logger.getLogger(MachineGenerator.class.getName());

    @SuppressWarnings("rawtypes")
    public void generate(Machine machine, File templateFile, OutputStream outputStream) {
        JtwigTemplate template = JtwigTemplate.fileTemplate(templateFile);
        JtwigModel model = JtwigModel.newModel();
        Collection<String> variablesInput = new ArrayList<String>();
        Collection<String> variablesOutput = new ArrayList<String>();
        Collection<String> variablesControl = new ArrayList<String>();
        Collection<String> variablesInternal = new ArrayList<String>();
        Collection<String> invariants = new ArrayList<String>();
        Collection<String> initialisations = new ArrayList<String>();
        /*Collection<String> senseEvents = new ArrayList<String>();
        Collection<String> reactionEvents = new ArrayList<String>();*/
        Map<String,String> events = new HashMap<String, String>();
        Map<String, Collection<String>> sets = new HashMap<String, Collection<String>>();

        for (Variable variable : machine.getVariables()) {
            switch (variable.getCategory()) {
                case INPUT:
                    variablesInput.add(variable.getName());
                    /*if (variable.hasBounds()) {
                    	senseEvents.add(variable.getName()+" : "+variable.getLowerBound()+".."+variable.getUpperBound());
                    } else {
                    	senseEvents.add(variable.getName()+" : BOOL");
                    }*/
                    break;
                case OUTPUT:
                    variablesOutput.add(variable.getName());
                    /*if (variable.hasBounds()) {
                    	reactionEvents.add(variable.getName()+" : "+variable.getLowerBound()+".."+variable.getUpperBound());
                    } else {
                    	reactionEvents.add(variable.getName()+" : BOOL");
                    }*/
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
            initialisations.add(variable.getName()+" := "+variable.getDefaultValue().toString().toUpperCase());
        }

        for (Property property : machine.getProperties()) {
            invariants.add(property.getExpression());
        }

        for(Event event: machine.getEvents()) {
            events.put(event.getName(), "pre");
        }

        for(EnumerateType set : machine.getSets()) {
            sets.put(set.getName(), set.getValues());
        }

        model.with("name", machine.getName());
        model.with("variablesInput", variablesInput);
        model.with("variablesOutput", variablesOutput);
        model.with("variablesControl", variablesControl);
        model.with("variablesInternal", variablesInternal);
        model.with("invariants", invariants);
        model.with("initialisations", initialisations);
        model.with("events", events);
        model.with("sets", sets);
        /*model.with("senseEvents", senseEvents);
        model.with("reactionEvents", reactionEvents);*/

        template.render(model, outputStream);
    }
}
