/**
 * @preserve Copyright (c) 2017 TERBINE as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the express written consent of TERBINE.
 * <p/>
 * This material also contains proprietary and confidential information of TERBINE
 * and its suppliers, and may not be used by or disclosed to any person, in
 * whole or in part, without the prior written consent of TERBINE.
 */
package com.terbine.api.example.http;

import com.terbine.cabinet.model.search.IndexedMetadataItem;
import com.terbine.cabinet.model.search.SearchQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author brianeno
 */
@Slf4j
public class SearchServiceClientTest extends CoreHttpServiceClientTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testBasicSearch() throws Exception {
        SearchService httpService = getSearchService();
        SearchQuery sq = new SearchQuery();
        String searchText = "wiper";
        sq.setText(searchText);
        List<IndexedMetadataItem> list = httpService.searchBasic(sq, 1, 10, SearchQuery.Sort.DATEADDED.name(),
                SearchQuery.SortOrder.ASC.name());
        assertNotNull(list);
        assertTrue(list.get(0).getTitle().toLowerCase().contains(searchText));
    }

    @Test
    public void testBasicSearchWithFilter() throws Exception {
        SearchService httpService = getSearchService();
        SearchQuery sq = new SearchQuery();
        String searchText = "wiper";
        sq.setText(searchText);
        Map<String, List<String>> filterMap = new HashMap<>();
        List<String> filterList = new ArrayList<>();
        filterList.add("silver");
        filterMap.put("grade", filterList);
        sq.setFilters(filterMap);
        List<IndexedMetadataItem> list = httpService.searchBasic(sq, 1, 10, SearchQuery.Sort.DATEADDED.name(),
                SearchQuery.SortOrder.ASC.name());
        assertNotNull(list);
        assertTrue(list.get(0).getTitle().toLowerCase().contains(searchText));
    }

    private SearchService getSearchService() {
        String baseUri = getProperty(BASEURL_PROP_NAME);
        SearchService httpService = new SearchServiceClient(getHttpClient(), baseUri);
        return httpService;
    }
}