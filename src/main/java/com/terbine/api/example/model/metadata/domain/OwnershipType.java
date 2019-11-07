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
package com.terbine.api.example.model.metadata.domain;

import lombok.Getter;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author brianeno.
 */
public enum OwnershipType {
    ORIGINATOR(1) {
        @Override
        public String toString() {
            return "Originator";
        }
    },
    OWNER(2) {
        @Override
        public String toString() {
            return "Owner";
        }
    };

    // Lookup table
    private static final Map<Integer, OwnershipType> LOOKUP = new HashMap<>();

    // Populate the lookup table on loading time
    static {
        for (OwnershipType s : EnumSet.allOf(OwnershipType.class))
            LOOKUP.put(s.getValue(), s);
    }

    @Getter
    private int value;

    OwnershipType(int value) {
        this.value = value;
    }

    // This method can be used for reverse lookup purpose
    public static OwnershipType get(Integer value) {
        if (LOOKUP.containsKey(value)) {
            return LOOKUP.get(value);
        } else {
            throw new IllegalArgumentException("Invalid OwnerType of " + value + " provided");
        }
    }
}
