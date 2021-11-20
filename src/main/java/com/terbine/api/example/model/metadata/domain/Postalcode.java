/**
 * @preserve Copyright (c) 2021 TERBINE. as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the express written consent of TERBINE.
 * <p>
 * This material also contains proprietary and confidential information
 * of TERBINE. and its suppliers, and may not be used by or
 * disclosed to any person, in whole or in part, without the prior written
 * consent of TERBINE.
 */
package com.terbine.api.example.model.metadata.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @uathor brianeno
 */
@Getter
@Setter
public class Postalcode {
    private String postalcode;
    private String city;
    private String state;
    private Double latitude;
    private Double longitude;
    private Integer utcOffset;
    private Integer dst;

    public Postalcode() {
    }

    public Postalcode(final String postalcode,
                      final String city,
                      final String state,
                      final Double latitude,
                      final Double longitude,
                      final Integer utcOffset,
                      final Integer dst) {
        this.postalcode = postalcode;
        this.city = city;
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
        this.utcOffset = utcOffset;
        this.dst = dst;
    }
}
