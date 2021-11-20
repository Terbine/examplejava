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
import com.terbine.api.example.model.continuous.AggregatedContinuousRecord;
import com.terbine.api.example.model.continuous.ContinuousRecord;
import com.terbine.api.example.util.UUIDHelper;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author brianeno
 */
@Slf4j
public class ContinuousServiceClientTest extends CoreHttpServiceClientTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testGetContinuousRecordsSince() throws Exception {
        ContinuousService httpService = getContinuousService();
        assertNotNull(httpService);
        AuthenticatedUser au = getAuthenticateUser(httpService);
        DateTime since = DateTime.now(DateTimeZone.UTC).minusMonths(10);
        UUID metadataId = UUIDHelper.fromString(getProperty(METADATA_ID_PROP));
        List<ContinuousRecord> list = httpService.getContinuousRecordsSince(au.getToken(),
                 metadataId,
                since);

        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    public void testGetContinuousRecordsRangeWithContent() throws Exception {
        ContinuousService httpService = getContinuousService();
        assertNotNull(httpService);
        AuthenticatedUser au = getAuthenticateUser(httpService);
        DateTime from = DateTime.now(DateTimeZone.UTC).minusMonths(10);
        DateTime to = DateTime.now(DateTimeZone.UTC);
        UUID metadataId = UUIDHelper.fromString(getProperty(METADATA_ID_PROP));
        AggregatedContinuousRecord aggregatedContinuousRecord = httpService.getContinuousRecordsRangeWithContent(au.getToken(),
                metadataId,
                from, to);

        assertNotNull(aggregatedContinuousRecord);
    }

    private ContinuousService getContinuousService() {
        String baseUri = getProperty(BASEURL_PROP_NAME);
        ContinuousService httpService = new ContinuousServiceClient(getHttpClient(), baseUri);
        return httpService;
    }

    private AuthenticatedUser getAuthenticateUser(ContinuousService httpService) throws URISyntaxException {
        ContinuousServiceClient client = (ContinuousServiceClient) httpService;
        AuthenticatedUser au = client.login(getProperty(USER_PROP_NAME), getProperty(PASSWORD_PROP_NAME));
        return au;
    }
}