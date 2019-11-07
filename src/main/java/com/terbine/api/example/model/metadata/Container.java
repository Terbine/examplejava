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
@JsonRootName("container")
@Getter
@Setter
public class Container implements Serializable {

    protected CreateUpdateInfo createUpdateInfo;

    //TODO REMOVE
    private Integer legalType;
    private String latitude;
    private String longitude;
    private String address;
    private String altitude;
    private String legalTypeName;
    //TODO remove end

    private UUID id;
    private Integer type;

    private String typeName;

    private UUID parentId;

    private String extId;

    private Integer locationCategory;

    private String locationCategoryName;

    private Integer locationType;

    private String locationTypeName;

    private Integer locationSubType;

    private String locationSubTypeName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonDefinitions.DATE_FORMAT_STD)
    private DateTime startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonDefinitions.DATE_FORMAT_STD)
    private DateTime endDate;

    private Integer nameId;

    private String name;

    private String description;

    private Location location;

    private List<Location> locations;

    @JsonIgnore
    private UUID metadataId;

    public void addLocation(Location location) {
        if (this.locations == null) {
            this.locations = new ArrayList<>();
        }
        this.locations.add(location);
    }
}
