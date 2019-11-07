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
@JsonRootName("category")
@Getter
@Setter
@Deprecated
public class Category implements Serializable {

    public static final String CATEGORY_TYPE = "CAT";
    public static final String TAG_TYPE = "TAG";
    private Integer Id;

    private String name;

    @JsonIgnore
    private UUID metadataId;

    @JsonIgnore
    private String type;

    @JsonIgnore
    private String typeName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return Id.equals(category.Id);
    }

    @Override
    public int hashCode() {
        return Id.hashCode();
    }
}
