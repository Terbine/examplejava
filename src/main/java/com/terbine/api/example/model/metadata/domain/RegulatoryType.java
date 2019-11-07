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
public enum RegulatoryType {
    NONE(149),
    GDPR(140),
    PRIVACYSHIELD(141),
    ITAR(142),
    UK_DPA(143),
    NULL(144) {
        @Override
        public String toString() {
            return "Null";
        }
    };

    // Lookup table
    private static final Map<Integer, RegulatoryType> LOOKUP = new HashMap<>();

    // Populate the lookup table on loading time
    static {
        for (RegulatoryType s : EnumSet.allOf(RegulatoryType.class))
            LOOKUP.put(s.getValue(), s);
    }

    @Getter
    private int value;

    RegulatoryType(int value) {
        this.value = value;
    }

    // This method can be used for reverse lookup purpose
    public static RegulatoryType get(Integer value) {
        if (LOOKUP.containsKey(value)) {
            return LOOKUP.get(value);
        } else {
            throw new IllegalArgumentException("Invalid RegulatoryType  of " + value + " provided");
        }
    }
}
