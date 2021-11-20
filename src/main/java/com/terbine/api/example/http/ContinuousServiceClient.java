/**
 * @preserve Copyright (c) 2021 TERBINE as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the express written consent of TERBINE.
 * <p/>
 * This material also contains proprietary and confidential information of TERBINE
 * and its suppliers, and may not be used by or disclosed to any person, in
 * whole or in part, without the prior written consent of TERBINE.
 */
package com.terbine.api.example.http;

import com.terbine.api.example.model.continuous.AggregatedContinuousRecord;
import com.terbine.api.example.model.continuous.ContinuousRecord;
import com.terbine.api.example.util.CommonDefinitions;
import com.terbine.api.example.util.UUIDHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author brianeno
 * <p>
 * A REST client for the TERBINE REST API.
 */
@Slf4j
@SuppressWarnings({"javadocs"})
public class ContinuousServiceClient extends CoreHttpServiceClient implements ContinuousService {

    final static DateTimeFormatter formatter = DateTimeFormat.forPattern(CommonDefinitions.DATE_FORMAT_STD);

    /**
     * Constructor.
     *
     * @param httpClient An org.apache.http.client.HttpClient on which to make
     *                   requests.
     * @param baseUri    base URI for the service
     */
    public ContinuousServiceClient(
            HttpClient httpClient,
            final String baseUri) {

        super(httpClient, baseUri);
    }

    @Override
    public List<ContinuousRecord> getContinuousRecordsSince(final String token,
                                                            final UUID metadataId,
                                                            DateTime dateSince) throws URISyntaxException {
        List<ContinuousRecord> list = new ArrayList<>();
        try {
            String dateSinceStr = formatter.print(dateSince);
            URI uri = new URIBuilder(this.getBaseUri())
                    .setPath("/api/continuous/metadata/" + metadataId + "/since/" + dateSinceStr)
                    .build();


            HttpGet request = new HttpGet(uri);

            request.addHeader("X-TRACKING-ID", UUID.randomUUID().toString());
            request.addHeader("authorization", "bearer " + token);

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
                String responseString = httpResponseInfo.getResponseString();
                log.info("Response from Retrieving Continuous Feed Info. Code = " + httpResponseInfo.getResponseCode() + ","
                        + " Data = [" + responseString + "]");

                list = this.getObjMapper().readValue(responseString, this.getObjMapper().getTypeFactory().constructCollectionType(
                        List.class, ContinuousRecord.class));
            }
        } catch (IOException ex) {
            log.error("Error calling REST service", ex);
            throw new RuntimeException("Error calling REST service", ex);
        }
        return list;
    }

    @Override
    public AggregatedContinuousRecord getContinuousRecordsRangeWithContent(final String token,
                                                                           final UUID metadataId,
                                                                           DateTime dateSinceFrom,
                                                                           DateTime dateSinceTo) throws URISyntaxException {
        AggregatedContinuousRecord aggregatedContinuousRecord;
        try {
            String dateSinceFromStr = formatter.print(dateSinceFrom);
            String dateSinceToStr = formatter.print(dateSinceTo);
            URI uri = new URIBuilder(this.getBaseUri())
                    .setPath("/api/continuous/metadata/" + metadataId + "/" + dateSinceFromStr + "/to/" + dateSinceToStr)
                    .build();


            HttpGet request = new HttpGet(uri);

            request.addHeader("X-TRACKING-ID", UUID.randomUUID().toString());
            request.addHeader("authorization", "bearer " + token);
            request.addHeader("X-APIKEY", UUIDHelper.getUUID().toString());
            request.addHeader("X-SOURCE-AGENT", "EXAMPLEAPI");

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
                String responseString = httpResponseInfo.getResponseString();
                log.info("Response from Retrieving Aggregated Continuous Record. Code = " + httpResponseInfo.getResponseCode() + ","
                        + " Data = [" + responseString + "]");

                aggregatedContinuousRecord = this.getObjMapper().readValue(responseString, AggregatedContinuousRecord.class);
            }
        } catch (IOException ex) {
            log.error("Error calling REST service", ex);
            throw new RuntimeException("Error calling REST service", ex);
        }
        return aggregatedContinuousRecord;
    }
}
