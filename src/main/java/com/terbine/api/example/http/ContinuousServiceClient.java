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

import com.terbine.api.example.model.ingest.Attachment;
import com.terbine.api.example.model.ingest.FileUploadInfo;
import com.terbine.api.example.model.metadata.Metadata;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.ContentBody;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
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
    public Attachment uploadDataToMetadata(final String token,
                                           final FileUploadInfo fileUploadInfo,
                                           byte[] content) throws URISyntaxException {
        Attachment attachment;
        try {
            URI uri = new URIBuilder(this.getBaseUri())
                    .setPath("/api/dataset/" + fileUploadInfo.getDatasetId() + "/content/upload/")
                    .build();

            String fileUploadInfoJson = this.getObjMapper().writeValueAsString(fileUploadInfo);
            ContentBody fileBody = new ByteArrayBody(content, fileUploadInfo.getFileName());
            HttpEntity entity = MultipartEntityBuilder
                    .create()
                    .addTextBody("info", fileUploadInfoJson)
                    .addPart("file", fileBody)
                    .build();

            HttpEntityEnclosingRequest request = new HttpPost(uri);
            request.setEntity(entity);

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
                log.info("Response from Posting Dataset. Code = " + httpResponseInfo.getResponseCode() + ","
                        + " Data = [" + responseString + "]");

                attachment = this.getObjMapper().readValue(responseString, Attachment.class);
            }
        } catch (IOException ex) {
            log.error("Error calling REST service", ex);
            throw new RuntimeException("Error calling REST service", ex);
        }
        return attachment;
    }
}
