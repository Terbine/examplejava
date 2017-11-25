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

import com.terbine.api.example.BaseApiTest;
import com.terbine.cabinet.app.AuthenticatedUser;
import com.terbine.cabinet.model.RefType;
import com.terbine.cabinet.model.domain.GicsSector;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author brianeno
 */
@Slf4j
public class CoreHttpServiceClientTest extends BaseApiTest {

    @Before
    public void setUp() throws Exception {
        loadProperties();
    }

    /**
     * This uses the following properies from unit_test.properties to login
     * user
     * password
     * baseuri
     * <p>
     * The response from the login is JSON string of type AuthenticatedUser.
     * <p>
     * The token field is to be used for future authenticated requests.
     * <p>
     * The userid field is the unique identifer for the user.
     * <p>
     * The orgid field is the unique identifer for the owning organization.
     * <p>
     * Sample JSON Response
     * <pre>
     *      (@code{"lastlogin":"2017-11-24T23:44:02Z","username":<user name>,"displayname":<user display name>",
     *      "token":<token>,
     *      "userid":<uuid for user>,
     *      "orgid":<uuid for organization>,
     *      "orgname":<org name>,"tokenlifeseconds":4200,"numberNotices":2,"tabs":["ACCOUNT","SUMMARY","FINANCE","LEGAL","WORKSPACE","METADATA","TECHNICAL","DATASETS","POLICIES"]})
     *  </pre>
     */
    @Test
    public void testLogin() throws Exception {
        String user = getProperty(USER_PROP_NAME);
        String password = getProperty(PASSWORD_PROP_NAME);
        CoreHttpService httpService = getCoreHttpService();
        AuthenticatedUser au = httpService.login(user, password);
        assertNotNull(au);
        assertNotNull(au.getToken());
    }

    @Test
    public void testDomainTypes() throws Exception {
        CoreHttpService httpService = getCoreHttpService();
        List<RefType> list = httpService.getDomainTypes();
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    public void testDomainSic() throws Exception {
        CoreHttpService httpService = getCoreHttpService();
        List<RefType> list = httpService.getDomain("siccode");
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    public void testDomainGic() throws Exception {
        CoreHttpService httpService = getCoreHttpService();
        List<GicsSector> list = httpService.getGicsCode();
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    private CoreHttpService getCoreHttpService() {
        String baseUri = getProperty(BASEURL_PROP_NAME);
        CoreHttpService httpService = new CoreHttpServiceClient(getHttpClient(), baseUri);
        return httpService;
    }
}