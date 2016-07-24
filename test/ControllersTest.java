import controllers.routes;
import models.*;
import org.eclipse.jetty.io.ClientConnectionFactory;
import org.junit.*;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import play.Mode;
import play.db.Database;
import play.db.evolutions.Evolution;
import play.db.evolutions.Evolutions;
import play.inject.guice.GuiceApplicationBuilder;
import play.api.mvc.RequestHeader;
import play.mvc.*;
import play.test.*;
import play.Application;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static play.test.Helpers.route;


/**
 *
 * Simple (JUnit) tests that can call all parts of a play app.
 * If you are interested in mocking a whole application, see the wiki for more details.
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ControllersTest extends WithApplication {

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

    @Test(timeout = TIMEOUT)
    public void testRegister() {
        // Arrange
        Map<String, String> data = new HashMap<>();
        User testUser = new User("masteryoda", "123456");
        data.put("username", testUser.getUserName());
        data.put("password", testUser.getPassword());

        Http.RequestBuilder request = new Http.RequestBuilder()
                .method("POST")
                .bodyForm(data)
                .uri(routes.LoginController.register().url());

        // Act
        Result result = route(request);
        User expectedUser = User.fetchByUsername("masteryoda");

        // Assert
        assertEquals(play.mvc.Http.Status.OK, result.status());
        assertNotNull(expectedUser);
        assertEquals(expectedUser.getUserName(), testUser.getUserName());
        assertEquals(expectedUser.getPassword(), testUser.getPassword());
    }

    @Test(timeout = TIMEOUT)
    public void testLogin() {
         // Arrange
        Map<String, String> data = new HashMap<>();
        User testUser = new User("masteryoda", "123456");
        data.put("username", testUser.getUserName());
        data.put("password", testUser.getPassword());
        testUser.save();

        Http.RequestBuilder request = new Http.RequestBuilder()
                .method("POST")
                .bodyForm(data)
                .uri(routes.LoginController.loginAttempt().url());

        // Act
        Result result = route(request);

        // Assert
        assertEquals(Http.Status.SEE_OTHER, result.status());
    }

    @Test(timeout = TIMEOUT)
    public void testLoginUnauthorized() {
         // Arrange
        Map<String, String> data = new HashMap<>();
        User testUser = new User("masteryoda", "123456");
        data.put("username", testUser.getUserName());
        data.put("password", testUser.getPassword());

        Http.RequestBuilder request = new Http.RequestBuilder()
                .method("POST")
                .bodyForm(data)
                .uri(routes.LoginController.loginAttempt().url());

        // Act
        Result result = route(request);

        // Assert
        assertEquals(Http.Status.UNAUTHORIZED, result.status());
    }
}
