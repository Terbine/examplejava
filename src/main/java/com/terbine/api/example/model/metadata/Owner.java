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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author brianeno.
 */
@ToString(includeFieldNames = true)
@JsonRootName("owner")
@Getter
@Setter
public class Owner implements Serializable {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonDefinitions.DATE_FORMAT_STD)
    protected DateTime startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonDefinitions.DATE_FORMAT_STD)
    protected DateTime endDate;

    protected CreateUpdateInfo createUpdateInfo;

    private UUID id;

    private UUID ownerId;

    private String ownerName;

    private Integer type;

    private String typeName;

    private String comment;

    @JsonIgnore
    private UUID metadataId;

    private List<String> isicCode = new ArrayList<>();
}
