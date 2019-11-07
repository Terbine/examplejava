/**
 * @preserve Copyright (c) 2015 Terbine Inc. as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the express written consent of Terbine Inc.
 * <p>
 * This material also contains proprietary and confidential information
 * of Terbine Inc. and its suppliers, and may not be used by or
 * disclosed to any person, in whole or in part, without the prior written
 * consent of Terbine Inc.
 */
package com.terbine.api.example.model.metadata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author brianeno.
 */
@ToString(includeFieldNames = true)
@JsonRootName("sensorInfo")
@Setter
@Getter
public class SensorInfo implements Serializable {

    protected CreateUpdateInfo createUpdateInfo;

    private UUID id;

    private Integer sourceNameId;

    private String sourceName;

    private Integer sourceTypeId;

    private String sourceType;

    private Integer type;

    private String typeName;

    private String manufacturer;

    private Integer manufacturerId;

    private String make;

    private String model;

    private String version;

    private String comment;

    @JsonIgnore
    private UUID datasetId;
}
