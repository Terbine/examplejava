/*******************************************************************************
 * @preserve Copyright (c) 2017 TERBINE Inc. as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the express written consent of TERBINE Inc.
 * <p>
 * This material also contains proprietary and confidential information
 * of TERBINE Inc. and its suppliers, and may not be used by or
 * disclosed to any person, in whole or in part, without the prior written
 * consent of TERBINE Inc.
 ******************************************************************************/
package com.terbine.api.example.model.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @author brianeno
 */
@Slf4j
@NoArgsConstructor
@Getter
@Setter
@ToString(includeFieldNames = true)
@JsonRootName("metadataContinuousInfo")
public class MetadataContinuousInfo {

    @JsonProperty("id")
    private UUID id;

    private Integer type = MetadataType.FIXED.getValue();

    private Integer loadFrequency;

    private Integer loadFrequencyType = MetadataUpdateInterval.HOUR.getValue();

    private Integer updateInterval;

    private Integer updateIntervalType = MetadataUpdateInterval.HOUR.getValue();
}
