/**
 * @preserve Copyright (c) 2017 TERBINE as an unpublished
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
public class GicsIndustryGroup {
    private String id;
    private String sector;
    private String text;
    private List<GicsIndustry> industries = new ArrayList<>();

    public GicsIndustryGroup() {
    }

    @JsonIgnore
    public void addIndustry(GicsIndustry industry) {
        this.industries.add(industry);
    }

}
