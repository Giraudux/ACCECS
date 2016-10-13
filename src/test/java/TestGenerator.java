import fr.univ.nantes.alma.accecs.generator.MachineGenerator;

import fr.univ.nantes.alma.accecs.model.Event;
import fr.univ.nantes.alma.accecs.model.Machine;
import fr.univ.nantes.alma.accecs.model.Property;
import fr.univ.nantes.alma.accecs.model.Variable;
import org.junit.Test;

import java.util.ArrayList;

/**
 *
 */
public class TestGenerator {

    @Test
    public void test0() {
        Machine machine = new Machine("test0", new ArrayList<Variable>(), new ArrayList<Property>(), new ArrayList<Event>());
        MachineGenerator machineGenerator = new MachineGenerator();
    }
}
