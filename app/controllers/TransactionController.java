package controllers;

import com.google.inject.Inject;
import models.*;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

import java.util.List;


/**
 * This controller is used to handle HTTP requests regarding transactions
 */

public class TransactionController extends Controller {

    @Inject
    private FormFactory formFactory;

    /**
     * renders transaction page
     * @param saleId id of sale
     * @return transaction page
     */
    public Result renderTransactionPage(int saleId) {
        //TODO: handle invalid saleId
        User user = Utils.getUserSession();
        Sale sale = Sale.fetchById(saleId);
        List<Item> items = Item.fetchItemsBySale(sale);
        List<Transaction> transactions = Transaction.fetchTransactionsBySale(sale);
        return ok(transaction.render(user, sale, transactions, items));
    }


}