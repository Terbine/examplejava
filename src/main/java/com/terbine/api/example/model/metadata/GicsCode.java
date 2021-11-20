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

@JsonRootName("gics")
@Getter
@Setter
public class GicsCode implements Serializable {

    private String Id;

    private String name;

    private String level = LEVEL.SUB_INDUSTRY.name();

    @JsonIgnore
    private UUID metadataId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GicsCode category = (GicsCode) o;

        return Id.equals(category.Id);
    }

    @Override
    public int hashCode() {
        return Id.hashCode();
    }

    public enum LEVEL {
        SECTOR,
        INDUSTRY_GROUP,
        INDUSTRY,
        SUB_INDUSTRY
    }
}
