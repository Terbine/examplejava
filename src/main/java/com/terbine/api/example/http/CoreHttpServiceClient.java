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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.terbine.api.example.model.Domain;
import com.terbine.api.example.model.RefType;
import com.terbine.api.example.model.auth.AuthenticatedUser;
import com.terbine.api.example.model.auth.Login;
import com.terbine.api.example.model.metadata.domain.Country;
import com.terbine.api.example.model.metadata.domain.GicsSector;
import com.terbine.api.example.model.metadata.domain.State;
import com.terbine.api.example.util.CommonDefinitions;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author brianeno
 * <p>
 * A REST client for the TERBINE REST API.
 */
@Slf4j
@SuppressWarnings({"javadocs"})
public class CoreHttpServiceClient implements CoreHttpService {

    protected final static DateFormat DATEFORMAT = new SimpleDateFormat(CommonDefinitions.DATE_FORMAT_STD);

    protected static final String CONTENT_TYPE_JSON = "application/json";
    protected static final String CONTENT_TYPE_MULTIPART = "multipart/form-data";


    @Getter
    private final HttpClient httpClient;
    @Getter
    private final String baseUri;
    @Getter
    private final ObjectMapper objMapper;
    @Getter
    private final ResponseHandler<HttpResponseInfo> responseHandler;
    @Getter
    private DateTimeFormatter fmt = ISODateTimeFormat.dateTime();

    /**
     * Constructor.
     *
     * @param httpClient An org.apache.http.client.HttpClient on which to make
     *                   requests.
     * @param baseUri    base URI for the service
     */
    public CoreHttpServiceClient(
            HttpClient httpClient,
            final String baseUri) {

        if (httpClient == null) {
            throw new IllegalArgumentException("httpClient is null");
        }

        if (StringUtils.isEmpty(baseUri)) {
            throw new IllegalArgumentException("baseUri is null or empty");
        }

        this.objMapper = new ObjectMapper();
        this.objMapper.registerModule(new JodaModule());

        // Store the client for later
        this.httpClient = httpClient;
        this.baseUri = baseUri;
        this.responseHandler = (final HttpResponse response) -> {
            return new HttpResponseInfo(getResponseAsString(response.getEntity()),
                    response.getStatusLine().getStatusCode());
        };
    }

    @Override
    public AuthenticatedUser login(final String user, final String password) throws URISyntaxException {
        AuthenticatedUser au = null;
        try {
            String responseString = null;
            URI uri = new URIBuilder(this.baseUri)
                    .setPath("/api/auth/login")
                    .build();
            HttpEntityEnclosingRequest request = new HttpPost(uri);

            Login login = new Login(user, password);
            String json = this.objMapper.writeValueAsString(login);

            StringEntity entity = new StringEntity(json);
            entity.setContentType(CONTENT_TYPE_JSON);
            request.setEntity(entity);

            HttpResponseInfo httpResponseInfo = this.httpClient.execute((HttpUriRequest) request, responseHandler);
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
                au = this.objMapper.readValue(responseString, AuthenticatedUser.class);
            }
        } catch (IOException ex) {
            log.error("Error calling REST service", ex);
            throw new RuntimeException("Error calling REST service", ex);
        }
        return au;
    }

    @Override
    public List<RefType> getDomainTypes() throws URISyntaxException {

        List<RefType> domainTypes = null;
        try {
            URI uri = new URIBuilder(this.baseUri)
                    .setPath("/api/domain/type")
                    .build();

            HttpGet request = new HttpGet(uri);
            request.setHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON);

            HttpResponseInfo httpResponseInfo = this.httpClient.execute((HttpUriRequest) request, responseHandler);
            // just check if it is in 200 range
            if (httpResponseInfo.getResponseCode() > 299) {
                String errorString = httpResponseInfo.getResponseString();
                if (errorString == null || errorString.length() == 0) {
                    errorString = "Error calling REST service";
                }
                log.error(errorString + " Status {}", httpResponseInfo.getResponseCode());
                throw new RuntimeException(errorString + " " + httpResponseInfo.getResponseCode());
            } else {
                String responseString = httpResponseInfo.getResponseString();
                log.info("Response from REST service. Code = " + httpResponseInfo.getResponseCode() + ","
                        + " Data = [" + responseString + "]");
                domainTypes = this.objMapper.readValue(responseString,
                        this.objMapper.getTypeFactory().constructCollectionType(
                                List.class, RefType.class));
            }
        } catch (IOException ex) {
            log.error("Error calling REST service", ex);
            throw new RuntimeException("Error calling REST service", ex);
        }
        return domainTypes;
    }

    @Override
    public List<Domain> getDomain(final String domainName) throws URISyntaxException {

        List<Domain> domainTypes = null;
        try {
            URI uri = new URIBuilder(this.baseUri)
                    .setPath("/api/domain/" + domainName)
                    .build();

            HttpGet request = new HttpGet(uri);
            request.setHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON);

            HttpResponseInfo httpResponseInfo = this.httpClient.execute((HttpUriRequest) request, responseHandler);
            // just check if it is in 200 range
            if (httpResponseInfo.getResponseCode() > 299) {
                String errorString = httpResponseInfo.getResponseString();
                if (errorString == null || errorString.length() == 0) {
                    errorString = "Error during registering collector complete.";
                }
                log.error(errorString + " Status {}", httpResponseInfo.getResponseCode());
                throw new RuntimeException(errorString + " " + httpResponseInfo.getResponseCode());
            } else {
                String responseString = httpResponseInfo.getResponseString();
                log.info("Response from Posting Data. Code = " + httpResponseInfo.getResponseCode() + ","
                        + " Data = [" + responseString + "]");
                domainTypes = this.objMapper.readValue(responseString,
                        this.objMapper.getTypeFactory().constructCollectionType(
                                List.class, Domain.class));
            }
        } catch (IOException ex) {
            log.error("Error calling core TERBINE service", ex);
            throw new RuntimeException("Error calling core TERBINE service", ex);
        }
        return domainTypes;
    }

    @Override
    public List<Domain> getDomain(final Integer domainId) throws URISyntaxException {

        List<Domain> domainTypes = null;
        try {
            URI uri = new URIBuilder(this.baseUri)
                    .setPath("/api/domain/" + domainId)
                    .build();

            HttpGet request = new HttpGet(uri);
            request.setHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON);

            HttpResponseInfo httpResponseInfo = this.httpClient.execute((HttpUriRequest) request, responseHandler);
            // just check if it is in 200 range
            if (httpResponseInfo.getResponseCode() > 299) {
                String errorString = httpResponseInfo.getResponseString();
                if (errorString == null || errorString.length() == 0) {
                    errorString = "Error during registering collector complete.";
                }
                log.error(errorString + " Status {}", httpResponseInfo.getResponseCode());
                throw new RuntimeException(errorString + " " + httpResponseInfo.getResponseCode());
            } else {
                String responseString = httpResponseInfo.getResponseString();
                log.info("Response from Posting Data. Code = " + httpResponseInfo.getResponseCode() + ","
                        + " Data = [" + responseString + "]");
                domainTypes = this.objMapper.readValue(responseString,
                        this.objMapper.getTypeFactory().constructCollectionType(
                                List.class, Domain.class));
            }
        } catch (IOException ex) {
            log.error("Error calling core TERBINE service", ex);
            throw new RuntimeException("Error calling core TERBINE service", ex);
        }
        return domainTypes;
    }

    @Override
    public List<RefType> getSicsCode() throws URISyntaxException {

        List<RefType> domainTypes = null;
        try {
            URI uri = new URIBuilder(this.baseUri)
                    .setPath("/api/domain/siccode")
                    .build();

            HttpGet request = new HttpGet(uri);
            request.setHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON);

            HttpResponseInfo httpResponseInfo = this.httpClient.execute((HttpUriRequest) request, responseHandler);
            // just check if it is in 200 range
            if (httpResponseInfo.getResponseCode() > 299) {
                String errorString = httpResponseInfo.getResponseString();
                if (errorString == null || errorString.length() == 0) {
                    errorString = "Error during registering collector complete.";
                }
                log.error(errorString + " Status {}", httpResponseInfo.getResponseCode());
                throw new RuntimeException(errorString + " " + httpResponseInfo.getResponseCode());
            } else {
                String responseString = httpResponseInfo.getResponseString();
                log.info("Response from Posting Data. Code = " + httpResponseInfo.getResponseCode() + ","
                        + " Data = [" + responseString + "]");
                domainTypes = this.objMapper.readValue(responseString,
                        this.objMapper.getTypeFactory().constructCollectionType(
                                List.class, RefType.class));
            }
        } catch (IOException ex) {
            log.error("Error calling core TERBINE service", ex);
            throw new RuntimeException("Error calling core TERBINE service", ex);
        }
        return domainTypes;
    }

    @Override
    public List<GicsSector> getGicsCode() throws URISyntaxException {

        List<GicsSector> domainTypes = null;
        try {
            URI uri = new URIBuilder(this.baseUri)
                    .setPath("/api/domain/giccode")
                    .build();

            HttpGet request = new HttpGet(uri);
            request.setHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON);

            HttpResponseInfo httpResponseInfo = this.httpClient.execute((HttpUriRequest) request, responseHandler);
            // just check if it is in 200 range
            if (httpResponseInfo.getResponseCode() > 299) {
                String errorString = httpResponseInfo.getResponseString();
                if (errorString == null || errorString.length() == 0) {
                    errorString = "Error during registering collector complete.";
                }
                log.error(errorString + " Status {}", httpResponseInfo.getResponseCode());
                throw new RuntimeException(errorString + " " + httpResponseInfo.getResponseCode());
            } else {
                String responseString = httpResponseInfo.getResponseString();
                log.info("Response from Posting Data. Code = " + httpResponseInfo.getResponseCode() + ","
                        + " Data = [" + responseString + "]");
                domainTypes = this.objMapper.readValue(responseString,
                        this.objMapper.getTypeFactory().constructCollectionType(
                                List.class, GicsSector.class));
            }
        } catch (IOException ex) {
            log.error("Error calling core TERBINE service", ex);
            throw new RuntimeException("Error calling core TERBINE service", ex);
        }
        return domainTypes;
    }

    private String getResponseAsString(HttpEntity entity) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(entity.getContent()), 65728);
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException ex) {
            log.error("Error retrieving content for REST service", ex);
        }
        return sb.toString();
    }

    @Override
    public List<Country> getCountries() throws URISyntaxException {

        List<Country> list = null;
        try {
            URI uri = new URIBuilder(this.baseUri)
                    .setPath("/api/domain/country")
                    .build();

            HttpGet request = new HttpGet(uri);
            request.setHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON);

            HttpResponseInfo httpResponseInfo = this.httpClient.execute((HttpUriRequest) request, responseHandler);
            // just check if it is in 200 range
            if (httpResponseInfo.getResponseCode() > 299) {
                String errorString = httpResponseInfo.getResponseString();
                if (errorString == null || errorString.length() == 0) {
                    errorString = "Error during registering collector complete.";
                }
                log.error(errorString + " Status {}", httpResponseInfo.getResponseCode());
                throw new RuntimeException(errorString + " " + httpResponseInfo.getResponseCode());
            } else {
                String responseString = httpResponseInfo.getResponseString();
                log.info("Response from Posting Data. Code = " + httpResponseInfo.getResponseCode() + ","
                        + " Data = [" + responseString + "]");
                list = this.objMapper.readValue(responseString,
                        this.objMapper.getTypeFactory().constructCollectionType(
                                List.class, Country.class));
            }
        } catch (IOException ex) {
            log.error("Error calling core TERBINE service", ex);
            throw new RuntimeException("Error calling core TERBINE service", ex);
        }
        return list;
    }

    @Override
    public List<State> getStates(final String country) throws URISyntaxException {

        List<State> list = null;
        try {
            URI uri = new URIBuilder(this.baseUri)
                    .setPath("/api/domain/country/" + country + "/state")
                    .build();

            HttpGet request = new HttpGet(uri);
            request.setHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_JSON);

            HttpResponseInfo httpResponseInfo = this.httpClient.execute((HttpUriRequest) request, responseHandler);
            // just check if it is in 200 range
            if (httpResponseInfo.getResponseCode() > 299) {
                String errorString = httpResponseInfo.getResponseString();
                if (errorString == null || errorString.length() == 0) {
                    errorString = "Error during registering collector complete.";
                }
                log.error(errorString + " Status {}", httpResponseInfo.getResponseCode());
                throw new RuntimeException(errorString + " " + httpResponseInfo.getResponseCode());
            } else {
                String responseString = httpResponseInfo.getResponseString();
                log.info("Response from Posting Data. Code = " + httpResponseInfo.getResponseCode() + ","
                        + " Data = [" + responseString + "]");
                list = this.objMapper.readValue(responseString,
                        this.objMapper.getTypeFactory().constructCollectionType(
                                List.class, State.class));
            }
        } catch (IOException ex) {
            log.error("Error calling core TERBINE service", ex);
            throw new RuntimeException("Error calling core TERBINE service", ex);
        }
        return list;
    }
}
