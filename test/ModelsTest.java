import models.*;
import org.junit.*;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;


/**
 *
 * Simple (JUnit) tests that can call all parts of a play app.
 * If you are interested in mocking a whole application, see the wiki for more details.
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ModelsTest extends BaseTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createUser() {
        // Arrange
        Map<String, String> data = new HashMap<>();
        User testUser = new User("masteryoda", "123456");

        // Act
        testUser.save();
        User expectedUser = User.fetchByUsername("masteryoda");

        // Assert
        assertEquals(expectedUser.getUserName(), testUser.getUserName());
        assertEquals(expectedUser.getPassword(), testUser.getPassword());
    }
}
