package com.mockcompany.webapp.controller;

import com.mockcompany.webapp.api.SearchReportResponse;
import com.mockcompany.webapp.model.ProductItem;
import com.mockcompany.webapp.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Management decided it is super important that we have lots of products that match the following terms.
 * So much so, that they would like a daily report of the number of products for each term along with the total
 * product count.
 */
@RestController
public class ReportController {

    /**
     * The people that wrote this code didn't know about JPA Spring Repository interfaces!
     */

    // An API used
    private final EntityManager entityManager;

    @Autowired
    public ReportController(EntityManager entityManager, SearchService searchService) {
        this.entityManager = entityManager;
    }

    @Autowired
    SearchService searchService;


    @GetMapping("/api/products/report")
    // creating method that runs the daily report and should return a search report response.
    public SearchReportResponse runReport() {
//        // initialise hashmap called hits. takes in search term and its count. It is instansiated so that it can be an argument for
//        // setting the searchTermHits
//        Map<String, Integer> hits = new HashMap<>();
//        // creating an instance of the SearchReportResponse class called response
//        SearchReportResponse response = new SearchReportResponse();
//        // setSearchTermHits method takes in the hits hashmap and is called on response
//        response.setSearchTermHits(hits);
//
//        // amount of items value assined to count variable
//        int count = searchService.displayAllItems().size();
//
//        // instantiated list called matchingIds and selects item.id from ProductItem where the item name is cool and description is cool
//        List<Number> matchingIds = new ArrayList<>();
//        matchingIds.addAll(
//                this.entityManager.createQuery("SELECT item.id from ProductItem item where item.name like '%cool%'").getResultList()
//        );
//        matchingIds.addAll(
//                this.entityManager.createQuery("SELECT item.id from ProductItem item where item.description like '%cool%'").getResultList()
//        );
//        matchingIds.addAll(
//                this.entityManager.createQuery("SELECT item.id from ProductItem item where item.name like '%Cool%'").getResultList()
//        );
//        matchingIds.addAll(
//                this.entityManager.createQuery("SELECT item.id from ProductItem item where item.description like '%cool%'").getResultList()
//        );
//
//        // List instantiated called counted
//        List<Number> counted = new ArrayList<>();
//
//        // loop through matchingIds list, if there is an id that is not found in the counted list, the id will be added to it
//        for (Number id: matchingIds) {
//            if (!counted.contains(id)) {
//                counted.add(id);
//            }
//        }
//
//        // add all items with item name cool and the number of those items into the searchTermHits hashmap, which is then called on response
//        response.getSearchTermHits().put("Cool", counted.size());
//
//        // the response count is set to the count of the items
//        response.setProductCount(count);
//
//        List<ProductItem> allItems = searchService.displayAllItems();
//        int kidCount = 0;
//        int perfectCount = 0;
//        Pattern kidPattern = Pattern.compile("(.*)[kK][iI][dD][sS](.*)");
//        for (ProductItem item : allItems) {
//            if (kidPattern.matcher(item.getName()).matches() || kidPattern.matcher(item.getDescription()).matches()) {
//                kidCount += 1;
//            }
//            if (item.getName().toLowerCase().contains("perfect") || item.getDescription().toLowerCase().contains("perfect")) {
//                perfectCount += 1;
//            }
//        }
//        response.getSearchTermHits().put("Kids", kidCount);
//
//        response.getSearchTermHits().put("Amazing", entityManager.createQuery("SELECT item FROM ProductItem item where lower(concat(item.name, ' - ', item.description)) like '%amazing%'").getResultList().size());
//
//        hits.put("Perfect", perfectCount);
//
//        return response;
//    }

        // test needs product count:
        int amount = searchService.displayAllItems().size();
        // Hashmap to include the search term and its count:
        HashMap<String, Integer> searchTermAndCount = new HashMap<>();
        // array of search terms:
        ArrayList<String> searchTerms = new ArrayList<>();
        searchTerms.add("Cool");
        searchTerms.add("Amazing");
        searchTerms.add("Perfect");
        searchTerms.add("Kids");
        // for loop through array of the terms:
        for (int i = 0; i < searchTerms.size(); i++) {
            searchTermAndCount.put(searchTerms.get(i), searchService.searchForItems(searchTerms.get(i)).size());
        }
        // instantiate new searchReportResponse
        SearchReportResponse response = new SearchReportResponse();
        // set product count:
        response.setProductCount(searchService.displayAllItems().size());
        response.setSearchTermHits(searchTermAndCount);

        return response;
    }
}
