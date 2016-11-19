

import org.junit.Test;

import fr.univ.nantes.alma.accecs.model.CategoryVariable;
import fr.univ.nantes.alma.accecs.model.INPUT;


public class TestCategory {
	@Test
    public void test0() {
        CategoryVariable c = new INPUT();
        System.out.println(c.getName());
    }
}
