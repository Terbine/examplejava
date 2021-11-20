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
public class DatasetServiceClient extends CoreHttpServiceClient implements DatasetService {

    /**
     * Constructor.
     *
     * @param httpClient An org.apache.http.client.HttpClient on which to make
     *                   requests.
     * @param baseUri    base URI for the service
     */
    public DatasetServiceClient(
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

    @Override
    public UUID getDatasetIdForMetadata(final String token,
                                        final UUID metadataId) throws URISyntaxException {
        Metadata metadata;
        try {
            String responseString = null;
            URI uri = new URIBuilder(this.getBaseUri())
                    .setPath("/api/metadata/" + metadataId)
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
        if (metadata.getDataset() != null && !metadata.getDataset().isEmpty()) {
            return metadata.getDataset().get(0).getId();
        } else {
            return null;
        }
    }

    @Override
    public List<Attachment> getAllDatasetsForMetadata(final String token,
                                                      final UUID datasetId,
                                                      final Integer pageNum,
                                                      final Integer pageSize) throws URISyntaxException {

        List<Attachment> list;
        try {
            String responseString = null;
            URI uri = new URIBuilder(this.getBaseUri())
                    .setPath("/api/dataset/" + datasetId + "/content")
                    .setParameter("pageNum", pageNum.toString())
                    .setParameter("pageSize", pageSize.toString())
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
                list = this.getObjMapper().readValue(responseString,
                        this.getObjMapper().getTypeFactory().constructCollectionType(
                                List.class, Attachment.class));
            }
        } catch (IOException ex) {
            log.error("Error calling REST service", ex);
            throw new RuntimeException("Error calling REST service", ex);
        }
        return list;
    }

    @Override
    public void downloadDatasetById(final String token,
                                    final UUID datasetId,
                                    final String outFilename) throws URISyntaxException {
        try {
            URI uri = new URIBuilder(this.getBaseUri())
                    .setPath("/api/dataset/content/" + datasetId + "/download")
                    .build();

            HttpGet request = new HttpGet(uri);

            request.addHeader("X-TRACKING-ID", UUID.randomUUID().toString());
            request.addHeader("authorization", "bearer " + token);

            HttpResponse response = this.getHttpClient().execute((HttpUriRequest) request);
            // just check if it is in 200 range
            if (response.getStatusLine().getStatusCode() > 299) {
                throw new RuntimeException("Error calling REST service, status " + response.getStatusLine().getStatusCode());
            } else {
                HttpEntity entity = response.getEntity();
                BufferedInputStream bis = new BufferedInputStream(entity.getContent());

                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(outFilename)));
                int inByte;
                while ((inByte = bis.read()) != -1) {
                    bos.write(inByte);
                }
                bis.close();
                bos.close();
            }
        } catch (IOException ex) {
            log.error("Error calling REST service", ex);
            throw new RuntimeException("Error calling REST service", ex);
        }
    }
}
