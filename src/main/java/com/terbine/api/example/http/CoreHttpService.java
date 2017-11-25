/**
 * @preserve Copyright (c) 2017 TERBINE as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the express written consent of TERBINE.
 * <p/>
 * This material also contains proprietary and confidential information
 * of TERBINE and its suppliers, and may not be used by or
 * disclosed to any person, in whole or in part, without the prior written
 * consent of TERBINE.
 */
package com.terbine.api.example.http;

import com.terbine.cabinet.app.AuthenticatedUser;
import com.terbine.cabinet.model.RefType;
import com.terbine.cabinet.model.domain.GicsSector;

import java.net.URISyntaxException;
import java.util.List;

/**
 * An interface that defines a HTTP client that can be used for
 * communicating with Terbine API Services
 */
@SuppressWarnings({"javadocs"})
public interface CoreHttpService {

    AuthenticatedUser login(String user, String password) throws URISyntaxException;

    List<RefType> getDomainTypes() throws URISyntaxException;

    List<RefType> getDomain(String domainName) throws URISyntaxException;

    List<GicsSector> getGicsCode() throws URISyntaxException;
}
