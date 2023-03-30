package com.mockcompany.webapp.service;

import com.mockcompany.webapp.data.ProductItemRepository;
import com.mockcompany.webapp.model.ProductItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    ProductItemRepository productItemRepository;

    @Autowired
    SearchService searchService;

    public List<ProductItem> displayAllItems() {
        return (List<ProductItem>) productItemRepository.findAll();
    }

    public List<ProductItem> searchForItems(String query) {

        Iterable<ProductItem> allItems = searchService.displayAllItems();
        List<ProductItem> itemList = new ArrayList<>();
        // This is a loop that the code inside will execute on each of the items from the database.
        // convert query to lowercase to make search case-insensitive

        String updatedQuery = query.toLowerCase();

        for (ProductItem item : allItems) {
            // Convert each item properties into a string using toString() method in ProductItem model, and transform them to lowercase
            String itemAsString = item.toString();
            String itemName = item.getName().toLowerCase();
            String itemDesc = item.getDescription().toLowerCase();

//             Transform query to lowercase
            String newQuery = query.toLowerCase();

            // if the query includes single quotes, remove the quotes and check to see if it is an exact match with an item name OR description,
            // if so, add to list of items
            if(itemName.contains(newQuery) || itemDesc.contains(newQuery)) {
                itemList.add(item);
            }

            if(query.startsWith("\"") && query.endsWith("\"")) {
                String queryWithNoQuotes = query.replaceAll("\"", "");
                if(queryWithNoQuotes.equals(item.getName()) || queryWithNoQuotes.equals(item.getDescription())) {
                    itemList.add(item);
                }
            }

        }
        return itemList;
    }



     ;




}
