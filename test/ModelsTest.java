import models.*;
import org.junit.*;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.List;
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
        populate();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createUser() {
        // Arrange
        Map<String, String> data = new HashMap<>();
        User testUser = new User("r2d2", "123456");

        // Act
        testUser.save();
        User expectedUser = User.fetchByUsername("r2d2");

        // Assert
        assertEquals(expectedUser.getUserName(), testUser.getUserName());
        assertEquals(expectedUser.getPassword(), testUser.getPassword());
    }

    @Test
    public void testFetchByUsername() {
        // Arrange
        User expectedUser = new User(USERNAME, PASSWORD);

        // Act
        User actualUser = User.fetchByUsername(USERNAME);

        // Assert
        assertNotNull(actualUser);
        assertEquals(expectedUser.getUserName(), actualUser.getUserName());
        assertEquals(expectedUser.getPassword(), actualUser.getPassword());
    }

    @Test(timeout = TIMEOUT)
    public void testFetchAll() {
        // Arrange
        User expectedUser = new User(USERNAME, PASSWORD);

        // Act
        List<User> actualUsers = User.fetchAllUsers();

        // Assert
        assertNotNull(actualUsers);
        assertTrue(actualUsers.contains(expectedUser));
    }

    @Test(timeout = TIMEOUT)
    public void testCreateItem() {
        // Arrange
        Sale sale = Sale.fetchAllSales().get(0);
        Item expectedItem = new Item("Test", "Test", 100, 89.99);


    }
}
