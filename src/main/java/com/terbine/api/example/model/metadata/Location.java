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
import java.util.ArrayList;
import java.util.List;

/**
 * @author brianeno.
 */

@ToString(includeFieldNames = true)
@JsonRootName("location")
@Getter
@Setter
public class Location implements Serializable {

    private String latitude;

    private String longitude;

    private List<LatLonInfo> latLonInfo;

    private String altitude;

    private String radius;

    private Integer radiusUnit;

    private String radiusUnitName;

    private String address;

    private String city;

    private String stateTerritory;

    private String county;

    private String country;

    private Integer countryId;

    private String postalCode;

    private String sensorText;

    private String freeText;

    @JsonIgnore
    public void addLatLonInfo(LatLonInfo latLonInfo) {
        if (this.latLonInfo == null) {
            this.latLonInfo = new ArrayList<>();
        }
        this.latLonInfo.add(latLonInfo);
    }
}
