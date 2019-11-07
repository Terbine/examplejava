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

import com.terbine.api.example.model.ingest.Attachment;
import com.terbine.api.example.model.ingest.FileUploadInfo;

import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

/**
 * An interface that defines a HTTP client that can be used for
 * communicating with Terbine API Services
 */
@SuppressWarnings({"javadocs"})
public interface DatasetService extends CoreHttpService {
    Attachment uploadDataToMetadata(final String token,
                                    final FileUploadInfo fileUploadInfo,
                                    byte[] fileBytes) throws URISyntaxException;

    UUID getDatasetIdForMetadata(final String token,
                                 final UUID metadataId) throws URISyntaxException;

    List<Attachment> getAllDatasetsForMetadata(final String token,
                                               final UUID datasetId,
                                               final Integer pageNum,
                                               final Integer pageSize) throws URISyntaxException;

    void downloadDatasetById(final String token,
                             final UUID datasetId,
                             final String outFilename) throws URISyntaxException;

}
