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
package com.terbine.api.example.model.metadata.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @uathor brianeno
 */
@Getter
@Setter
@Deprecated
public class IsicDivision {
    private String id;
    private String section;
    private String text;
    private List<IsicGroup> groups = new ArrayList<>();

    public IsicDivision() {
    }

    @JsonIgnore
    public void addGroup(IsicGroup isicGroup) {
        this.groups.add(isicGroup);
    }

}
