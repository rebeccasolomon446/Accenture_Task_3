package com.mockcompany.webapp.api;

import java.util.Map;

public class SearchReportResponse {

    private Integer productCount; // the amount of products
    private Map<String, Integer> searchTermHits; // takes in the search term used to retrieve the product and the number of times it was used.

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Map<String, Integer> getSearchTermHits() {
        return searchTermHits;
    }

    public void setSearchTermHits(Map<String, Integer> searchTermHits) {
        this.searchTermHits = searchTermHits;
    }
}
