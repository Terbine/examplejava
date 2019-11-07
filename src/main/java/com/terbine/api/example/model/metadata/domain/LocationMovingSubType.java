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
public enum LocationMovingSubType {

    DEVICE_GPS(10004),
    SYSTEM_LEVEL(10005),
    ALGORITHM_GENERATED(10006);
    // Lookup table
    private static final Map<Integer, LocationMovingSubType> LOOKUP = new HashMap<>();

    // Populate the lookup table on loading time
    static {
        for (LocationMovingSubType s : EnumSet.allOf(LocationMovingSubType.class))
            LOOKUP.put(s.getValue(), s);
    }

    @Getter
    private int value;

    LocationMovingSubType(int value) {
        this.value = value;
    }

    // This method can be used for reverse lookup purpose
    public static LocationMovingSubType get(Integer value) {
        if (LOOKUP.containsKey(value)) {
            return LOOKUP.get(value);
        } else {
            throw new IllegalArgumentException("Invalid LocationType of " + value + " provided");
        }
    }

}
