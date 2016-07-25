import models.*;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 *
 * Simple (JUnit) tests that can call all parts of a play app.
 * If you are interested in mocking a whole application, see the wiki for more details.
 *
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class BaseTest extends WithApplication {

    protected final int TIMEOUT = 20000;

    protected final String GET = "GET";
    protected final String POST = "POST";
    protected final String PUT = "PUT";
    protected final String PATCH = "PATCH";
    protected final String DELETE = "DELETE";

    protected final String USERNAME = "masteryoda";
    protected final String PASSWORD = "123456";

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



    /**
     * populate tables for testing
     */
    protected void populate() {
        User user1 = new User("masteryoda", "123456");
        User user2 = new User("lukeskywalker", "123456");
        User user3 = new User("obiwankenobi", "123456");
        User user4 = new User("anakinskywalker", "123456");
        User user5 = new User("rey", "123456");
        User user6 = new User("jangofett", "123456");
        user1.save();
        user2.save();
        user3.save();
        user4.save();
        user5.save();
        user6.save();

        Sale sale1 = new Sale("Toy", "Atlanta");
        Sale sale2 = new Sale("Car", "Georgia Tech");
        Sale sale3 = new Sale("Box", "Atlantic Station");
        Sale sale4 = new Sale("Stationery", "Atlanta");
        sale1.save();
        sale2.save();
        sale3.save();
        sale4.save();

        Role role1 = new Role(user1, sale1, Role.RoleEnum.saleAdmin);
        Role role2 = new Role(user2, sale1, Role.RoleEnum.seller);
        Role role3 = new Role(user3, sale1, Role.RoleEnum.cashier);
        Role role4 = new Role(user4, sale1, Role.RoleEnum.bookkeeper);
        Role role5 = new Role(user5, sale1, Role.RoleEnum.clerk);
        Role role6 = new Role(user1, sale2, Role.RoleEnum.saleAdmin);
        Role role7 = new Role(user2, sale2, Role.RoleEnum.saleAdmin);
        Role role8 = new Role(user1, sale3, Role.RoleEnum.saleAdmin);
        Role role9 = new Role(user1, sale4, Role.RoleEnum.saleAdmin);
        role1.save();
        role2.save();
        role3.save();
        role4.save();
        role5.save();
        role6.save();
        role7.save();
        role8.save();
        role9.save();

        List<Sale> sales = Sale.fetchAllSales();
        Item item1 = new Item("Gallardo", "Car", 100, 57.99);
        Item item2 = new Item("Murcielago", "Car", 100, 57.99);
        Item item3 = new Item("Aventador", "Car", 100, 57.99);
        Item item4 = new Item("Sesto Elemento", "Car", 100, 57.99);
        item1.setSale(sales.get(0));
        item2.setSale(sales.get(1));
        item3.setSale(sales.get(2));
        item4.setSale(sales.get(3));
        item1.save();
        item2.save();
        item3.save();
        item4.save();

        List<Transaction> transactions1 = new LinkedList<>();
        List<Transaction> transactions2 = new LinkedList<>();
        List<Transaction> transactions3 = new LinkedList<>();
        List<Transaction> transactions4 = new LinkedList<>();
        Transaction trans1 = new Transaction(5, item1.getPrice(), "buyer", "cash");
        Transaction trans2 = new Transaction(5, item2.getPrice(), "buyer", "cash");
        Transaction trans3 = new Transaction(5, item3.getPrice(), "buyer", "cash");
        Transaction trans4 = new Transaction(5, item4.getPrice(), "buyer", "cash");
        transactions1.add(trans1);
        transactions2.add(trans2);
        transactions3.add(trans3);
        transactions4.add(trans4);

        Receipt receipt1 = new Receipt();
        Receipt receipt2 = new Receipt();
        Receipt receipt3 = new Receipt();
        Receipt receipt4 = new Receipt();

        receipt1.setProfit(trans1.getProfit());
        receipt1.setUser(user1);
        receipt1.setSale(sale1);
        receipt1.setTransactions(transactions1);

        receipt2.setProfit(trans2.getProfit());
        receipt2.setUser(user2);
        receipt2.setSale(sale2);
        receipt2.setTransactions(transactions2);

        receipt3.setProfit(trans3.getProfit());
        receipt3.setUser(user3);
        receipt3.setSale(sale3);
        receipt3.setTransactions(transactions3);

        receipt4.setProfit(trans4.getProfit());
        receipt4.setUser(user4);
        receipt4.setSale(sale4);
        receipt4.setTransactions(transactions4);

        trans1.save();
        trans2.save();
        trans3.save();
        trans4.save();

        receipt1.save();
        receipt2.save();
        receipt3.save();
        receipt4.save();
    }

}
