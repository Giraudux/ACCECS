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
        Machine machine = new Machine("test0", new ArrayList<Event>(), new ArrayList<Property>(), new ArrayList<Variable>());
        MachineGenerator machineGenerator = new MachineGenerator();

        //TODO: wait people learn to code

        /*System.out.println("Working Directory = " +
                System.getProperty("user.dir"));*/
    }
}
