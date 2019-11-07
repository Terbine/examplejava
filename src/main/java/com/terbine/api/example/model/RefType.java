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
package com.terbine.api.example.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author brianeno.
 */
@ToString(includeFieldNames = true)
@JsonRootName("referenceType")
@Getter
@Setter
public class RefType implements Serializable {

    private Integer id;

    private String name;

    private String description;

    private int enabled;

    public RefType() {

    }

    public RefType(final Integer id, String name, String description, int enabled) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RefType role = (RefType) o;

        if (!this.id.equals(role.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
