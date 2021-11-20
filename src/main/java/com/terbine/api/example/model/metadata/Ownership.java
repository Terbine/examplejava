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
import java.util.ArrayList;
import java.util.List;

/**
 * @author brianeno.
 */
@ToString(includeFieldNames = true)
@JsonRootName("ownership")
@Getter
@Setter
public class Ownership implements Serializable {

    private List<Entity> originators;
    private List<Entity> owners;

    public void addOriginator(Entity entity) {
        if (this.originators == null) {
            this.originators = new ArrayList<>();
        }
        this.originators.add(entity);
    }

    public void addOwners(Entity entity) {
        if (this.owners == null) {
            this.owners = new ArrayList<>();
        }
        this.owners.add(entity);
    }
}
