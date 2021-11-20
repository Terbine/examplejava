/**
 * @preserve Copyright (c) 2021 TERBINE as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the express written consent of TERBINE.
 * <p/>
 * This material also contains proprietary and confidential information
 * of TERBINE and its suppliers, and may not be used by or
 * disclosed to any person, in whole or in part, without the prior written
 * consent of TERBINE.
 */
package com.terbine.api.example.http;

import com.terbine.api.example.model.continuous.AggregatedContinuousRecord;
import com.terbine.api.example.model.continuous.ContinuousRecord;
import org.joda.time.DateTime;

import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

/**
 * An interface that defines a HTTP client that can be used for
 * communicating with Terbine API Services
 */
@SuppressWarnings({"javadocs"})
public interface ContinuousService extends CoreHttpService {
    List<ContinuousRecord> getContinuousRecordsSince(final String token,
                                                     final UUID metadataId,
                                                     DateTime dateSince) throws URISyntaxException;

    AggregatedContinuousRecord getContinuousRecordsRangeWithContent(final String token,
                                                                    final UUID metadataId,
                                                                    DateTime dateSinceFrom,
                                                                    DateTime dateSinceTo) throws URISyntaxException;
}
