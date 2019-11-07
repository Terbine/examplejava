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

import com.terbine.api.example.model.search.IndexedMetadataItem;
import com.terbine.api.example.model.search.SearchQuery;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author brianeno
 * <p>
 * A REST client for the TERBINE REST API.
 */
@Slf4j
@SuppressWarnings({"javadocs"})
public class SearchServiceClient extends CoreHttpServiceClient implements SearchService {

    /**
     * Constructor.
     *
     * @param httpClient An org.apache.http.client.HttpClient on which to make
     *                   requests.
     * @param baseUri    base URI for the service
     */
    public SearchServiceClient(
            HttpClient httpClient,
            final String baseUri) {

        super(httpClient, baseUri);
    }

    @Override
    public List<IndexedMetadataItem> searchBasic(SearchQuery searchQuery,
                                                 Integer pageNum,
                                                 Integer pageSize,
                                                 String sort,
                                                 String order) throws URISyntaxException {
        List<IndexedMetadataItem> list;
        try {
            String responseString = null;
            URI uri = new URIBuilder(this.getBaseUri())
                    .setPath("/api/search/v2/metadata")
                    .setParameter("pageNum", pageNum.toString())
                    .setParameter("pageSize", pageSize.toString())
                    .setParameter("sort", sort)
                    .setParameter("order", order)
                    .build();

            HttpEntityEnclosingRequest request = new HttpPost(uri);

            String json = this.getObjMapper().writeValueAsString(searchQuery);

            StringEntity entity = new StringEntity(json);
            entity.setContentType(CONTENT_TYPE_JSON);
            request.setEntity(entity);
            HttpResponseInfo httpResponseInfo = this.getHttpClient().execute((HttpUriRequest) request, getResponseHandler());
            // just check if it is in 200 range
            if (httpResponseInfo.getResponseCode() > 299) {
                String errorString = httpResponseInfo.getResponseString();
                if (errorString == null || errorString.length() == 0) {
                    errorString = "Error during data publishing.";
                }
                log.error(errorString + " Status {}", httpResponseInfo.getResponseCode());
                throw new RuntimeException("Error calling REST service, status " + httpResponseInfo.getResponseCode());
            } else {
                responseString = httpResponseInfo.getResponseString();
                log.info("Response from REST service. Code = " + httpResponseInfo.getResponseCode() + ","
                        + " Data = [" + responseString + "]");
                list = this.getObjMapper().readValue(responseString,
                        this.getObjMapper().getTypeFactory().constructCollectionType(
                                List.class, IndexedMetadataItem.class));
            }
        } catch (IOException ex) {
            log.error("Error calling REST service", ex);
            throw new RuntimeException("Error calling REST service", ex);
        }
        return list;
    }
}
