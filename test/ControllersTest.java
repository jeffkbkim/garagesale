import controllers.routes;
import models.*;
import org.junit.*;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import play.mvc.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
public class ControllersTest extends BaseTest {

    protected Http.RequestBuilder requestWithSession;

    @Before
    public void setUp() {
        populate();

        Map<String, String> sessionMap = new HashMap<>();
        sessionMap.put("connected", USERNAME);
        requestWithSession = new Http.RequestBuilder();
        requestWithSession.session(sessionMap);
    }

    @After
    public void tearDown() {
    }

    @Test(timeout = TIMEOUT)
    public void testRegister() {
        // Arrange
        Map<String, String> data = new HashMap<>();
        User testUser = new User("r2d2", "123456");
        data.put("username", testUser.getUserName());
        data.put("password", testUser.getPassword());

        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .bodyForm(data)
                .uri(routes.LoginController.register().url());

        // Act
        Result result = route(request);
        User expectedUser = User.fetchByUsername("r2d2");

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
        User testUser = new User("r2d2", "123456");
        data.put("username", testUser.getUserName());
        data.put("password", testUser.getPassword());
        testUser.save();

        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
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
        User testUser = new User(USERNAME, PASSWORD);
        data.put("username", "");
        data.put("password", "");

        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(POST)
                .bodyForm(data)
                .uri(routes.LoginController.loginAttempt().url());

        // Act
        Result result = route(request);

        // Assert
        assertEquals(Http.Status.UNAUTHORIZED, result.status());
    }

    @Test(timeout = TIMEOUT)
    public void testProfile() {
        // Arrange
        Http.RequestBuilder request = requestWithSession
                .method(GET)
                .uri(routes.ProfileController.view().url());

        // Act
        Result result = route(request);

        // Assert
        assertEquals(Http.Status.OK, result.status());
    }

    @Test(timeout = TIMEOUT)
    public void testUpdateProfile() {
        // Arrange
        Map<String, String> data = new HashMap<>();
        User testUser = new User(USERNAME, PASSWORD);
        testUser.setEmail("masteryoda@gmail.com");
        testUser.setFirstName("Master");
        testUser.setLastName("Yoda");
        testUser.setPhone("1234567890");
        data.put("firstName", testUser.getFirstName());
        data.put("lastName", testUser.getLastName());
        data.put("email", testUser.getEmail());
        data.put("phone", testUser.getPhone());
        data.put("verifypassword", testUser.getPassword());

        Http.RequestBuilder request = requestWithSession
                .method(POST)
                .bodyForm(data)
                .uri(routes.ProfileController.updateProfile().url());

        // Act
        Result result = route(request);
        User resultUser = User.fetchByUsername(testUser.getUserName());

        // Assert
        assertEquals(Http.Status.OK, result.status());
        assertEquals(testUser.getUserName(), resultUser.getUserName());
        assertEquals(testUser.getFirstName(), resultUser.getFirstName());
        assertEquals(testUser.getLastName(), resultUser.getLastName());
        assertEquals(testUser.getEmail(), resultUser.getEmail());
        assertEquals(testUser.getPhone(), resultUser.getPhone());
    }

    @Test(timeout = TIMEOUT)
    public void testRenderSaleRolesPage() {
        // Arrange
        Sale sale = Sale.fetchAllSales().get(0);

        Http.RequestBuilder request = requestWithSession
                .method(GET)
                .uri(routes.SalesController.renderSaleRolesPage(sale.getId()).url());

        // Act
        Result result = route(request);

        // Assert
        assertEquals(Http.Status.OK, result.status());
    }

    @Test(timeout = TIMEOUT)
    public void testRenderAddUserRolePage() {
        // Arrange
        Sale sale = Sale.fetchAllSales().get(0);

        Http.RequestBuilder request = requestWithSession
                .method(GET)
                .uri(routes.SalesController.renderAddUserRolePage(sale.getId()).url());

        // Act
        Result result = route(request);

        // Assert
        assertEquals(Http.Status.OK, result.status());
    }

    @Test(timeout = TIMEOUT)
    public void testRenderCreateSalePage() {
        // Arrange
        Http.RequestBuilder request = requestWithSession
                .method(GET)
                .uri(routes.SalesController.createSalePage().url());

        // Act
        Result result = route(request);

        // Assert
        assertEquals(Http.Status.OK, result.status());
    }

    @Test(timeout = TIMEOUT)
    public void testRenderGetSalesPage() {
        // Arrange
        Http.RequestBuilder request = requestWithSession
                .method(GET)
                .uri(routes.SalesController.getSales().url());

        // Act
        Result result = route(request);

        // Assert
        assertEquals(Http.Status.OK, result.status());
    }

    @Test(timeout = TIMEOUT)
    public void testAllSalesPage() {
        // Arrange
        Http.RequestBuilder request = requestWithSession
                .method(GET)
                .uri(routes.SalesController.allSales().url());

        // Act
        Result result = route(request);

        // Assert
        assertEquals(Http.Status.OK, result.status());
    }

    @Test(timeout = TIMEOUT)
    public void testCreateSale() {
        // Arrange
        Sale newSale = new Sale("Newsale", "Atlanta");
        Map<String, String> data = new HashMap<>();
        data.put("SaleID", "0");
        data.put("name", newSale.getName());
        data.put("location", newSale.getLocation());

        Http.RequestBuilder request = requestWithSession
                .method(POST)
                .bodyForm(data)
                .uri(routes.SalesController.createSale().url());

        // Act
        Result result = route(request);

        // Assert
        assertEquals(Http.Status.SEE_OTHER, result.status());
    }

    @Test(timeout = TIMEOUT)
    public void testCloseSale() {
        // Arrange
        Sale sale = Sale.fetchAllSales().get(0);

        Http.RequestBuilder request = requestWithSession
                .method(GET)
                .uri(routes.SalesController.closeSale(sale.getId()).url());

        // Act
        Result result = route(request);

        // Assert
        assertEquals(Http.Status.SEE_OTHER, result.status());
    }

    @Test(timeout = TIMEOUT)
    public void testRenderCatalogPage() {
        // Arrange
        Sale sale = Sale.fetchAllSales().get(0);

        Http.RequestBuilder request = requestWithSession
                .method(GET)
                .uri(routes.CatalogController.renderCatalogPage(sale.getId()).url());

        // Act
        Result result = route(request);

        // Assert
        assertEquals(Http.Status.OK, result.status());
    }

    @Test(timeout = TIMEOUT)
    public void testRenderCatalogReadonlyPage() {
        // Arrange
        Sale sale = Sale.fetchAllSales().get(0);

        Http.RequestBuilder request = requestWithSession
                .method(GET)
                .uri(routes.CatalogController.renderCatalogReadOnlyPage(sale.getId()).url());

        // Act
        Result result = route(request);

        // Assert
        assertEquals(Http.Status.OK, result.status());
    }

    @Test(timeout = TIMEOUT)
    public void testRenderAddItemPage() {
        // Arrange
        Sale sale = Sale.fetchAllSales().get(0);

        Http.RequestBuilder request = requestWithSession
                .method(GET)
                .uri(routes.CatalogController.renderAddItemPage(sale.getId()).url());

        // Act
        Result result = route(request);

        // Assert
        assertEquals(Http.Status.OK, result.status());
    }

    @Test(timeout = TIMEOUT)
    public void testRenderModifyItemPage() {
        // Arrange
        Sale sale = Sale.fetchAllSales().get(0);
        Item item = Item.fetchItemsBySale(sale).get(0);

        Http.RequestBuilder request = requestWithSession
                .method(GET)
                .uri(routes.CatalogController.renderModifyItemPage(item.getId(), sale.getId()).url());

        // Act
        Result result = route(request);

        // Assert
        assertEquals(Http.Status.OK, result.status());
    }

    @Test(timeout = TIMEOUT)
    public void testRenderTag() {
        // Arrange
        Sale sale = Sale.fetchAllSales().get(0);
        Item item = Item.fetchItemsBySale(sale).get(0);

        Http.RequestBuilder request = requestWithSession
                .method(GET)
                .uri(routes.CatalogController.renderTag(sale.getId(), item.getId()).url());

        // Act
        Result result = route(request);

        // Assert
        assertEquals(Http.Status.OK, result.status());
    }

    @Test(timeout = TIMEOUT)
    public void testAllTags() {
        // Arrange
        Sale sale = Sale.fetchAllSales().get(0);

        Http.RequestBuilder request = requestWithSession
                .method(GET)
                .uri(routes.CatalogController.renderAllTags(sale.getId()).url());

        // Act
        Result result = route(request);

        // Assert
        assertEquals(Http.Status.OK, result.status());
    }

    @Test(timeout = TIMEOUT)
    public void testRenderReceipt() {
        // Arrange
        Sale sale = Sale.fetchAllSales().get(0);
        Receipt receipt = Receipt.fetchReceiptsBySale(sale).get(0);

        Http.RequestBuilder request = requestWithSession
                .method(GET)
                .uri(routes.CatalogController.renderReceipt(receipt.getId()).url());

        // Act
        Result result = route(request);

        // Assert
        assertEquals(Http.Status.OK, result.status());
    }

    @Test(timeout = TIMEOUT)
    public void testRenderReport() {
        // Arrange
        Sale sale = Sale.fetchAllSales().get(0);

        Http.RequestBuilder request = requestWithSession
                .method(GET)
                .uri(routes.CatalogController.renderReport(sale.getId()).url());

        // Act
        Result result = route(request);

        // Assert
        assertEquals(Http.Status.OK, result.status());
    }

    @Test(timeout = TIMEOUT)
    public void testAddItem() {
        // Arrange
        Sale sale = Sale.fetchAllSales().get(0);
        Item item = new Item("Toyota", "Car", 100, 19.99);
        Map<String, String> data = new HashMap<>();

        data.put("name", item.getName());
        data.put("description", item.getDescription());
        data.put("price", Double.toString(item.getPrice()));
        data.put("quantity", Integer.toString(item.getQuantity()));
        data.put("saleId", Integer.toString(sale.getId()));

        Http.RequestBuilder request = requestWithSession
                .method(POST)
                .bodyForm(data)
                .uri(routes.CatalogController.addItem().url());

        // Act
        Result result = route(request);

        // Assert
        assertEquals(Http.Status.SEE_OTHER, result.status());
    }

    @Test(timeout = TIMEOUT)
    public void testModifyItem() {
        // Arrange
        Sale sale = Sale.fetchAllSales().get(0);
        Item item = Item.fetchItemsBySale(sale).get(0);
        item.setName("New Name");
        item.setPrice(99.99);
        item.setQuantity(200);
        item.setMinimumPrice(99.99);
        Map<String, String> data = new HashMap<>();

        data.put("itemId", Integer.toString(item.getId()));
        data.put("name", item.getName());
        data.put("description", item.getDescription());
        data.put("price", Double.toString(item.getPrice()));
        data.put("minimumPrice", Double.toString(item.getPrice()));
        data.put("quantity", Integer.toString(item.getQuantity()));
        data.put("saleId", Integer.toString(sale.getId()));

        Http.RequestBuilder request = requestWithSession
                .method(POST)
                .bodyForm(data)
                .uri(routes.CatalogController.modifyItem().url());

        // Act
        Result result = route(request);

        // Assert
        assertEquals(Http.Status.SEE_OTHER, result.status());
    }

    @Test(timeout = TIMEOUT)
    public void testAllReceipts() {
        // Arrange
        Sale sale = Sale.fetchAllSales().get(0);

        Http.RequestBuilder request = requestWithSession
                .method(GET)
                .uri(routes.CatalogController.allReceipts(sale.getId()).url());

        // Act
        Result result = route(request);

        // Assert
        assertEquals(Http.Status.OK, result.status());
    }

    @Test(timeout = TIMEOUT)
    public void testRenderTransactionPage() {
        // Arrange
        Sale sale = Sale.fetchAllSales().get(0);

        Http.RequestBuilder request = requestWithSession
                .method(GET)
                .uri(routes.TransactionController.renderTransactionPage(sale.getId()).url());

        // Act
        Result result = route(request);

        // Assert
        assertEquals(Http.Status.OK, result.status());
    }

    /**
     * authenticate the running test application
     */
    private void authenticate() {
    }

}
