/**
 * @preserve Copyright (c) 2016 Terbine Inc. as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the express written consent of Terbine Inc.
 * <p>
 * This material also contains proprietary and confidential information
 * of Terbine Inc. and its suppliers, and may not be used by or
 * disclosed to any person, in whole or in part, without the prior written
 * consent of Terbine Inc.
 */
package com.terbine.api.example.model.ingest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.terbine.api.example.util.CommonDefinitions;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;

import java.util.UUID;

/**
 * @author brianeno
 */
@ToString(includeFieldNames = true)
@JsonRootName("attachment")
@Setter
@Getter
public class Attachment {

    private UUID id;

    private UUID metadataId;

    private UUID datasetId;

    private String externalId;

    private UUID organizationId;

    private Integer type = InputType.FILE.getValue();

    private Integer status = AttachmentStatus.NOT_PROCESSED.getValue();

    private String fileStoreLocation;

    private String fileExt;

    private Long size = 0L;

    private String fileHash;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonDefinitions.DATE_FORMAT_STD)
    private DateTime lastModifyTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonDefinitions.DATE_FORMAT_STD)
    private DateTime dateUploaded;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonDefinitions.DATE_FORMAT_STD)
    private DateTime dateStart;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonDefinitions.DATE_FORMAT_STD)
    private DateTime dateEnd;

    private Integer deleted = 0;

    private String originalName;

    private Integer numDownloads = 0;
}
