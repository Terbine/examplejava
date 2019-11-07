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

import java.io.Serializable;

/**
 * @author brianeno.
 */
@JsonRootName("domain")
@Getter
@Setter
public class Domain implements Serializable {

    private Integer id;

    @Getter
    private Integer type;

    @Getter
    private Integer parentId;

    @Getter
    private String name;

    @Getter
    private String description;

    @Getter
    private int enabled;

    @Getter
    private Integer sort;

    @Getter
    private Integer reviewed;

    public Domain() {

    }

    public Domain(final Integer id, final Integer type, final Integer parentId,
                  final String name, final String description, final int enabled) {
        this.id = id;
        this.type = type;
        this.parentId = parentId;
        this.name = name;
        this.description = description;
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Domain role = (Domain) o;

        if (!this.id.equals(role.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return this.name;
    }

    public Integer getId() {
        return this.id;
    }
}
