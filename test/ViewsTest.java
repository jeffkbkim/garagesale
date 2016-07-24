import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import play.mvc.Controller;
import play.twirl.api.Content;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by yudawinata on 7/24/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ViewsTest extends BaseTest {

    private final String TEXT_HTML = "text/html";

    @Before
    public void setUp() {
//        Controller.session("test", "test");
    }

    @Test
    public void testIndex () {
//        Content html = views.html.index.render();
//        assertEquals(TEXT_HTML, html.contentType());
//        assertTrue(html.body().contains("Home"));
//        assertTrue(html.body().contains("My Sales"));
    }
}
