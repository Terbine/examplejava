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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.terbine.api.example.util.CommonDefinitions;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author brianeno.
 */
@ToString(includeFieldNames = true)
@JsonRootName("regulatory")
@Getter
@Setter
public class Regulatory implements Serializable {

    protected CreateUpdateInfo createUpdateInfo;

    private UUID id;

    private Integer type;

    private String typeName;

    private String comment;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonDefinitions.DATE_FORMAT_STD)
    private DateTime startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonDefinitions.DATE_FORMAT_STD)
    private DateTime endDate;

    @JsonIgnore
    private UUID metadataId;
}
