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
@JsonRootName("customListEntry")
@Setter
@Getter
public class CustomListEntry implements Serializable {

    private UUID listId;

    private String id;

    private String name;

    private String description;

    private int enabled = 1;

    public CustomListEntry() {
    }

    public CustomListEntry(final UUID listId, final String id, String name, String description, int enabled) {
        this.listId = listId;
        this.id = id;
        this.name = name;
        this.description = description;
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomListEntry entry = (CustomListEntry) o;
        if (!this.listId.equals(entry.listId)) return false;

        if (!this.id.equals(entry.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
