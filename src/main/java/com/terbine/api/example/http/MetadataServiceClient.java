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

import com.terbine.cabinet.model.metadata.Metadata;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 * @author brianeno
 * <p>
 * A REST client for the TERBINE REST API.
 */
@Slf4j
@SuppressWarnings({"javadocs"})
public class MetadataServiceClient extends CoreHttpServiceClient implements MetadataService {

    /**
     * Constructor.
     *
     * @param httpClient An org.apache.http.client.HttpClient on which to make
     *                   requests.
     * @param baseUri    base URI for the service
     */
    public MetadataServiceClient(
            HttpClient httpClient,
            final String baseUri) {

        super(httpClient, baseUri);
    }

    @Override
    public Metadata insertMetadata(final String token, Metadata metadata) throws URISyntaxException {
        Metadata newMetadata;
        try {
            String responseString = null;
            URI uri = new URIBuilder(this.getBaseUri())
                    .setPath("/api/metadata")
                    .build();

            HttpEntityEnclosingRequest request = new HttpPost(uri);

            request.addHeader("X-TRACKING-ID", UUID.randomUUID().toString());
            request.addHeader("authorization", "bearer " + token);

            String json = this.getObjMapper().writeValueAsString(metadata);

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
                newMetadata = this.getObjMapper().readValue(responseString, Metadata.class);
            }
        } catch (IOException ex) {
            log.error("Error calling REST service", ex);
            throw new RuntimeException("Error calling REST service", ex);
        }
        return newMetadata;
    }

    @Override
    public Metadata updateMetadata(final String token, Metadata metadata) throws URISyntaxException {
        Metadata newMetadata;
        try {
            String responseString = null;
            URI uri = new URIBuilder(this.getBaseUri())
                    .setPath("/api/metadata")
                    .build();

            HttpEntityEnclosingRequest request = new HttpPut(uri);

            request.addHeader("X-TRACKING-ID", UUID.randomUUID().toString());
            request.addHeader("authorization", "bearer " + token);

            String json = this.getObjMapper().writeValueAsString(metadata);

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
                newMetadata = this.getObjMapper().readValue(responseString, Metadata.class);
            }
        } catch (IOException ex) {
            log.error("Error calling REST service", ex);
            throw new RuntimeException("Error calling REST service", ex);
        }
        return newMetadata;
    }

    @Override
    public Metadata getMetadata(final String token, final UUID id) throws URISyntaxException {

        Metadata metadata;
        try {
            String responseString = null;
            URI uri = new URIBuilder(this.getBaseUri())
                    .setPath("/api/metadata/" + id)
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
                responseString = httpResponseInfo.getResponseString();
                log.info("Response from REST service. Code = " + httpResponseInfo.getResponseCode() + ","
                        + " Data = [" + responseString + "]");
                metadata = this.getObjMapper().readValue(responseString, Metadata.class);
            }
        } catch (IOException ex) {
            log.error("Error calling REST service", ex);
            throw new RuntimeException("Error calling REST service", ex);
        }
        return metadata;
    }
}
