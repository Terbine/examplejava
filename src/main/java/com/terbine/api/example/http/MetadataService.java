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

import com.terbine.cabinet.model.metadata.Metadata;

import java.net.URISyntaxException;
import java.util.UUID;

/**
 * An interface that defines a HTTP client that can be used for
 * communicating with Terbine API Services
 */
@SuppressWarnings({"javadocs"})
public interface MetadataService extends CoreHttpService {
    Metadata insertMetadata(final String token, Metadata metadata) throws URISyntaxException;

    Metadata updateMetadata(final String token, Metadata metadata) throws URISyntaxException;

    Metadata getMetadata(final String token, final UUID id) throws URISyntaxException;
}
