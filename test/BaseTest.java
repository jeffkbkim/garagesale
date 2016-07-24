import org.junit.*;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import play.Mode;
import play.inject.guice.GuiceApplicationBuilder;
import play.api.mvc.RequestHeader;
import play.mvc.*;
import play.test.*;
import play.Application;

import java.util.Collections;
import java.util.Map;


/**
 *
 * Simple (JUnit) tests that can call all parts of a play app.
 * If you are interested in mocking a whole application, see the wiki for more details.
 *
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class BaseTest extends WithApplication {

    public final int TIMEOUT = 20000;

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder()
                .configure((Map) Helpers.inMemoryDatabase())
                .in(Mode.TEST)
                .build();
    }

    @Override
    public void startPlay()
    {
        super.startPlay();
        // mock or otherwise provide a context
        Http.Context.current.set(new Http.Context(1L,
                Mockito.mock(RequestHeader.class),
                Mockito.mock(Http.Request.class),
                Collections.<String, String>emptyMap(),
                Collections.<String, String>emptyMap(),
                Collections.<String, Object>emptyMap()));

    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

}
