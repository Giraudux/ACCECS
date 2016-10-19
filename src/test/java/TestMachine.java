
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;


import fr.univ.nantes.alma.accecs.model.Event;
import fr.univ.nantes.alma.accecs.model.Event.RoleEvent;
import fr.univ.nantes.alma.accecs.model.Machine;
import fr.univ.nantes.alma.accecs.model.Property;
import fr.univ.nantes.alma.accecs.model.Property.RoleProperty;
import fr.univ.nantes.alma.accecs.model.Variable;
import fr.univ.nantes.alma.accecs.model.Variable.RoleVariable;
import fr.univ.nantes.alma.accecs.model.VariableInteger;


public class TestMachine {
	@Test
    public void test0() {
		
	        Machine machine = new Machine("test0", new ArrayList<Variable>(Arrays.asList(new VariableInteger("a",RoleVariable.input,10,20))),
	        		new ArrayList<Property>(Arrays.asList(new Property("a<0",RoleProperty.SAFETY))), 
	        		new ArrayList<Event>(Arrays.asList(new Event("abc",RoleEvent.CONTROL))));
	       System.out.println(machine.getName());
	       System.out.println("");
	       System.out.println("Events:");
	       for (Event each : machine.getEvents()){System.out.println(each.getExpression());}
	       System.out.println("");
	       System.out.println("Variables:");
	       for (Variable each : machine.getVariables()){System.out.println(each.getName());}
	       System.out.println("");
	       System.out.println("Properties");
	       for (Property each : machine.getProperties()){System.out.println(each.getExpression());}
       
	}
}
