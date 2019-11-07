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

import lombok.Getter;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author brianeno.
 */
public enum LocationFixedSubType {
    ADDRESS(10001),
    GPS_COORDINATE(10002),
    LAT_LON(10003),
    BOUNDEDBOX(10007),
    POLYGON(10008);

    // Lookup table
    private static final Map<Integer, LocationFixedSubType> LOOKUP = new HashMap<>();

    // Populate the lookup table on loading time
    static {
        for (LocationFixedSubType s : EnumSet.allOf(LocationFixedSubType.class))
            LOOKUP.put(s.getValue(), s);
    }

    @Getter
    private int value;

    LocationFixedSubType(int value) {
        this.value = value;
    }

    // This method can be used for reverse lookup purpose
    public static LocationFixedSubType get(Integer value) {
        if (LOOKUP.containsKey(value)) {
            return LOOKUP.get(value);
        } else {
            throw new IllegalArgumentException("Invalid LocationType of " + value + " provided");
        }
    }

}
