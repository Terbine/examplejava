/**
 * @preserve Copyright (c) 2021 TERBINE as an unpublished
 * work. Neither this material nor any portion hereof may be copied or
 * distributed without the expressed written consent of TERBINE.
 * <p>
 * This material also contains proprietary and confidential information
 * of TERBINE and its suppliers, and may not be used by or
 * disclosed to any person, in whole or in part, without the prior written
 * consent of TERBINE.
 */
package com.terbine.api.example.model.continuous;

import lombok.Getter;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

@Getter
public enum ContinuousType {

    DBSTORAGE(1),
    OBJECTSTORAGE(2);

    private static final Map<Integer, ContinuousType> LOOKUP = new HashMap<>();

    // Populate the lookup table on loading time
    static {
        for (ContinuousType s : EnumSet.allOf(ContinuousType.class))
            LOOKUP.put(s.getValue(), s);
    }

    private int value;

    ContinuousType(int value) {
        this.value = value;
    }

    // This method can be used for reverse lookup purpose
    public static ContinuousType get(Integer value) {
        if (LOOKUP.containsKey(value)) {
            return LOOKUP.get(value);
        } else {
            throw new IllegalArgumentException("Invalid ContinuousType type of " + value + " provided");
        }
    }

}
