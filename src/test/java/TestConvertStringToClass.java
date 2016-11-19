import org.junit.Test;

import fr.univ.nantes.alma.accecs.parser.ParserJSON;

public class TestConvertStringToClass {
	@Test
    public void test0() {
        ParserJSON j = new ParserJSON();
        System.out.println(j.convertVariableCategory("output").getName());
    }
}
