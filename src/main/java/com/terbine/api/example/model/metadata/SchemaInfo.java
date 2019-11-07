/**
 * @preserve Copyright (c) 2015 TERBINE as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the express written consent of TERBINE.
 * <p>
 * This material also contains proprietary and confidential information
 * of TERBINE and its suppliers, and may not be used by or
 * disclosed to any person, in whole or in part, without the prior written
 * consent of TERBINE.
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
@JsonRootName("schemaInfo")
@Getter
@Setter
public class SchemaInfo implements Serializable {

    protected CreateUpdateInfo createUpdateInfo;
    private UUID id;
    private Integer format;
    private String formatName;
    private Integer type;
    private String typeName;
    private String property;
    private String body;

    private String unitOfMeasure;

    private Integer unitOfMeasureId;

    @JsonIgnore
    private UUID datasetId;
}
