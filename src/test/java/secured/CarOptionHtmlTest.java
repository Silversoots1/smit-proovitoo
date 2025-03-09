package secured;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarOptionHtmlTest {

    @Test
    public void testBuildCarOptionsHtml() {
         CarOptionHtml carOptionHtml = new CarOptionHtml();
         
        String selectedCarIds = "1,2";
        String html = carOptionHtml.buildCarOptionsHtml(selectedCarIds);

        assertNotNull(html);
        assertTrue(html.contains("<select id=\"cars\" name=\"cars\" multiple=\"\" required=\"\">"));
        assertTrue(html.contains("<option class=\"option-model\" value=\"1\" selected>C 160</option>"));

        assertTrue(html.contains("<option class=\"option-model\" value=\"2\" selected>C 180</option>"));
        assertTrue(html.contains("<option class=\"option-group\" value=\"30\">Mercedes-Benz</option>"));
        assertTrue(html.contains("</select>"));
    }
}