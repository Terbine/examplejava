/**
 * @preserve Copyright (c) 2021 TERBINE as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the expressed written consent of TERBINE.
 * <p>
 * This material also contains proprietary and confidential information
 * of TERBINE and its suppliers, and may not be used by or
 * disclosed to any person, in whole or in part, without the prior written
 * consent of TERBINE.
 */
package com.terbine.api.example.model.continuous;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.terbine.api.example.util.CommonDefinitions;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author brianeno
 */
@ToString(includeFieldNames = true)
@JsonRootName("ContinuousRecord")
@Getter
@Setter
public class ContinuousRecord implements Serializable {

    private UUID accountId;

    private UUID metadataId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonDefinitions.DATE_FORMAT_STD)
    private DateTime entryTime;

    private UUID trackingId;

    private String fileExt;

    private UUID datasetId;

    private Integer type = ContinuousType.DBSTORAGE.getValue();

    private Integer status = ContinuousStatus.ACTIVE.getValue();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonDefinitions.DATE_FORMAT_STD)
    private DateTime dateCreated;

    private String content;

    public ContinuousRecord() {
    }
}
