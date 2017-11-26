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
import com.terbine.cabinet.model.Domain;
import com.terbine.cabinet.model.RefType;
import com.terbine.cabinet.model.domain.GicsSector;
import com.terbine.cabinet.model.geo.Country;
import com.terbine.cabinet.model.geo.State;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

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

    /**
     * RefType(id=1, name=Category, description=Category domain types for ingested data, enabled=1)
     RefType(id=2, name=Delivery, description=Delivery domain types for ingested data, enabled=1)
     RefType(id=3, name=Dataset, description=Dataset domain types for ingested data, enabled=1)
     RefType(id=4, name=Sensor, description=Sensor domain types for ingested data, enabled=1)
     RefType(id=5, name=Format, description=Format domain types for ingested data, enabled=1)
     RefType(id=6, name=Owner, description=Owner domain types for ingested data, enabled=1)
     RefType(id=7, name=Container, description=Container domain types for ingested data, enabled=1)
     RefType(id=8, name=Schema, description=Schema domain types for ingested data, enabled=1)
     RefType(id=10, name=Legal, description=Legal domain types for ingested data, enabled=1)
     RefType(id=11, name=Relation, description=Relation domain types for ingested data, enabled=1)
     RefType(id=12, name=Grading, description=Grading domain types for ingested data, enabled=1)
     RefType(id=13, name=Location, description=Location domain types for ingested data, enabled=1)
     RefType(id=14, name=Regulatory, description=Regulatory domain types for ingested data, enabled=1)

     * @throws Exception
     */
    @Test
    public void testGetDomainTypes() throws Exception {
        CoreHttpService httpService = getCoreHttpService();
        List<RefType> list = httpService.getDomainTypes();
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    public void testGetDomainById() throws Exception {
        CoreHttpService httpService = getCoreHttpService();
        List<Domain> list = httpService.getDomain(12);
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    public void testGetDomainByString() throws Exception {
        CoreHttpService httpService = getCoreHttpService();
        List<Domain> list = httpService.getDomain("grading");
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    public void testGetDomainSic() throws Exception {
        CoreHttpService httpService = getCoreHttpService();
        List<RefType> list = httpService.getSicsCode();
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    public void testGetDomainGic() throws Exception {
        CoreHttpService httpService = getCoreHttpService();
        List<GicsSector> list = httpService.getGicsCode();
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    public void testGetCountries() throws Exception {
        CoreHttpService httpService = getCoreHttpService();
        List<Country> list = httpService.getCountries();
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    public void testGetStates() throws Exception {
        CoreHttpService httpService = getCoreHttpService();

        List<Country> listC = httpService.getCountries();
        Country usa = listC.stream().filter(s -> s.getIsoCode().equals("US")).findAny().get();
        List<State> list = httpService.getStates(usa.getId().toString());
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    private CoreHttpService getCoreHttpService() {
        String baseUri = getProperty(BASEURL_PROP_NAME);
        CoreHttpService httpService = new CoreHttpServiceClient(getHttpClient(), baseUri);
        return httpService;
    }
}