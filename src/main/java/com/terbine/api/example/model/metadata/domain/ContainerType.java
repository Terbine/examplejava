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
public enum ContainerType {
    SYSTEM(70),
    SUSYSTEM(71);

    // Lookup table
    private static final Map<Integer, ContainerType> LOOKUP = new HashMap<>();

    // Populate the lookup table on loading time
    static {
        for (ContainerType s : EnumSet.allOf(ContainerType.class))
            LOOKUP.put(s.getValue(), s);
    }

    @Getter
    private int value;

    ContainerType(int value) {
        this.value = value;
    }

    // This method can be used for reverse lookup purpose
    public static ContainerType get(Integer value) {
        if (LOOKUP.containsKey(value)) {
            return LOOKUP.get(value);
        } else {
            throw new IllegalArgumentException("Invalid ContainerType of " + value + " provided");
        }
    }
}
