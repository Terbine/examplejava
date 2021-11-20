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
@JsonRootName("aggregatedContinuousRecord")
@Getter
@Setter
public class AggregatedContinuousRecord implements Serializable {

    private UUID accountId;

    private UUID metadataId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonDefinitions.DATE_FORMAT_STD)
    private DateTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonDefinitions.DATE_FORMAT_STD)
    private DateTime endTime;

    private String fileExt;

    private Integer type = 1;

    private Integer numberRaw = 0;

    private String content;

    public AggregatedContinuousRecord() {
    }
}
