package controllers;

import com.avaje.ebeaninternal.server.lib.util.Str;
import com.google.gson.*;
import com.google.inject.Inject;
import models.Sale;
import models.User;
import play.Logger;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.suggestItems;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by yudawinata on 7/22/16.
 */
public class PriceSuggestionController extends Controller {
    @Inject
    FormFactory formFactory;

    public Result suggestPrice(String keyword, int saleId) {
        User user = Utils.getUserSession();
        List<Map<String, String>> suggestedItemsFetch = new LinkedList<>();
        try {
            String url = "http://svcs.sandbox.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsByKeywords&SERVICE-VERSION=1.9.0&SECURITY-APPNAME=YudaWina-FFFF00-SBX-909b46090-847616ae&RESPONSE-DATA-FORMAT=JSON&REST-PAYLOAD&keywords="
                    + URLEncoder.encode(keyword, "UTF-8")
                    + "&paginationInput.entriesPerPage=10";
            String result = requestGet(url);
            suggestedItemsFetch = parseFindItems(result);
        } catch (Exception e) {
            return redirect(routes.SalesController.allSales());
        }

        Sale sale = Sale.fetchById(saleId);

        double totalPrice = 0.0;
        for (Map<String, String> item : suggestedItemsFetch) {
            totalPrice += Double.parseDouble(item.get("price"));
        }
        double averagePrice = totalPrice / suggestedItemsFetch.size();
        String averagePriceStr = String.format("%.2f", averagePrice);
        return ok(suggestItems.render(user, suggestedItemsFetch, sale, averagePriceStr, keyword));
    }

    private String requestGet(String url) {
        String result = "";
        try {
            URLConnection connection = new URL(url).openConnection();
            InputStream response = connection.getInputStream();
            int i;
            while ((i = response.read()) != -1) {
                result += Character.toString((char) i);
            }
        } catch (Exception e) {
            result = null;
        }

        return result;
    }

    private List<Map<String, String>> parseFindItems(String json) {
        List<Map<String, String>> res = new LinkedList<>();
        Map<String, String> tempMap = new HashMap<>();
        try {
            JsonElement jsonElement = new JsonParser().parse(json);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonObject searchResult = jsonObject
                    .getAsJsonArray("findItemsByKeywordsResponse")
                    .get(0)
                    .getAsJsonObject()
                    .get("searchResult")
                    .getAsJsonArray()
                    .get(0)
                    .getAsJsonObject();
            JsonArray items = searchResult.get("item").getAsJsonArray();

            for (int i = 0; i < items.size(); i++) {
                tempMap = new HashMap<>();
                JsonObject item = items.get(i).getAsJsonObject();
                String title = item.get("title").getAsJsonArray().get(0).getAsString();
                String price = item.get("sellingStatus")
                        .getAsJsonArray()
                        .get(0)
                        .getAsJsonObject()
                        .get("currentPrice")
                        .getAsJsonArray()
                        .get(0)
                        .getAsJsonObject()
                        .get("__value__")
                        .getAsString();

                tempMap.put("title", title);
                tempMap.put("price", price);
                res.add(tempMap);
            }

//            Logger.error(items.get(0).getAsJsonObject().get("title").getAsJsonArray().get(0).getAsString());

        } catch (Exception e) {
            Logger.error("Failed to parse JSON");
        }

        return res;

    }

}
