/**
 * @preserve Copyright (c) 2021 TERBINE as an unpublished
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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.terbine.api.example.model.search.Searchable;
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
@JsonRootName("customList")
@ToString(includeFieldNames = true)
@Getter
@Setter
public class CustomList implements Searchable, Serializable {

    private UUID id;

    private UUID orgId;

    private String name;

    private Integer status = 1;

    private Integer type = 1;

    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonDefinitions.DATE_FORMAT_STD)
    @JsonProperty("dateUpdated")
    private DateTime dateUpdated;

    @JsonProperty("userCreated")
    private UUID userCreated;

    @JsonProperty("userUpdated")
    private UUID userUpdated;

    private Boolean isGlobal = true;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonDefinitions.DATE_FORMAT_STD)
    @JsonProperty("dateCreated")
    private DateTime dateCreated;

    private List<CustomListEntry> entries = new ArrayList<>();

    public CustomList() {
    }

    @JsonIgnore
    public void addCustomListEntry(CustomListEntry customListEntry) {
        this.entries.add(customListEntry);
    }
}