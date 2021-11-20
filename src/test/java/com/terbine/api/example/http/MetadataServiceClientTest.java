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

import com.terbine.api.example.model.auth.AuthenticatedUser;
import com.terbine.api.example.model.metadata.Metadata;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author brianeno
 */
@Slf4j
public class MetadataServiceClientTest extends CoreHttpServiceClientTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testInsertMetadata() throws Exception {
        MetadataService httpService = getMetadataService();

        String json = loadSampleJson("insertmetadata.json");

        Metadata metadata = this.getObjectMapper().readValue(json, Metadata.class);

        AuthenticatedUser au = httpService.login(getProperty(USER_PROP_NAME), getProperty(PASSWORD_PROP_NAME));

        assertNotNull(au);

        metadata.setOrgId(UUID.fromString(au.getOrgid()));
        Metadata newMetadata = httpService.insertMetadata(au.getToken(), metadata);
        assertNotNull(newMetadata);
        // This will print the JSON for the newly inserted metadata
        String resultJson = this.getObjectMapper().writeValueAsString(newMetadata);
        System.out.println(resultJson);
    }

    @Test
    public void testUpdateMetadata() throws Exception {
        MetadataService httpService = getMetadataService();

        AuthenticatedUser au = httpService.login(getProperty(USER_PROP_NAME), getProperty(PASSWORD_PROP_NAME));

        assertNotNull(au);

        Metadata metadata = httpService.getMetadata(au.getToken(), UUID.fromString(getProperty(METADATA_ID_PROP)));

        metadata.getIdentifier().setExtId("Updated_ID");

        Metadata updMetadata = httpService.updateMetadata(au.getToken(), metadata);
        assertNotNull(updMetadata);
        assertEquals("Updated_ID", updMetadata.getIdentifier().getExtId());
    }

    @Test
    public void testGetMetadata() throws Exception {
        MetadataService httpService = getMetadataService();

        AuthenticatedUser au = httpService.login(getProperty(USER_PROP_NAME), getProperty(PASSWORD_PROP_NAME));

        assertNotNull(au);

        Metadata metadata = httpService.getMetadata(au.getToken(), UUID.fromString(getProperty(METADATA_ID_PROP)));

        assertNotNull(metadata);
    }

    private MetadataService getMetadataService() {
        String baseUri = getProperty(BASEURL_PROP_NAME);
        MetadataService httpService = new MetadataServiceClient(getHttpClient(), baseUri);
        return httpService;
    }
}