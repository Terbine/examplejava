/**
 * @preserve Copyright (c) 2021 TERBINE as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the express written consent of TERBINE.
 * <p/>
 * This material also contains proprietary and confidential information of TERBINE
 * and its suppliers, and may not be used by or disclosed to any person, in
 * whole or in part, without the prior written consent of TERBINE.
 */
package com.terbine.api.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author brianeno
 */
@Slf4j
public class BaseApiTest {

    protected String METADATA_ID_PROP = "metadataId";
    public final static String USER_PROP_NAME = "user";
    public final static String PASSWORD_PROP_NAME = "password";
    public final static String BASEURL_PROP_NAME = "baseUri";
    private static Properties properties;

    private ObjectMapper objectMapper;

    @SneakyThrows(IOException.class)
    protected static Properties loadProperties() {
        properties = new Properties();
        String propName = "unit-test.properties";
        @Cleanup InputStream is = BaseApiTest.class.getClassLoader().getResourceAsStream(propName);
        properties.load(is);
        return properties;
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    protected HttpClient getHttpClient() {

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);

        return HttpClients.custom().setConnectionManager(cm).build();
    }

    public String loadSampleJson(String fileName) {
        String fileNameFull = "sample/" + fileName;
        String outStr = null;
        try {
            @Cleanup InputStream is = getClass().getClassLoader().getResourceAsStream(fileNameFull);
            outStr = IOUtils.toString(is);
        } catch (IOException ex) {
            log.error("Error loading " + fileName, ex);
        }
        return outStr;
    }

    protected ObjectMapper getObjectMapper() {
        if (this.objectMapper == null) {
            this.objectMapper = new ObjectMapper();
            this.objectMapper.registerModule(new JodaModule());
        }
        return this.objectMapper;
    }
}
