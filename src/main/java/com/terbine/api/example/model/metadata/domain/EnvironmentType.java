/**
 * @preserve Copyright (c) 2018 Terbine as an unpublished
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
public enum EnvironmentType {
    ENVIRONMENT(1),
    INTERACTOR(2);

    private static final Map<Integer, EnvironmentType> LOOKUP = new HashMap<>();

    // Populate the lookup table on loading time
    static {
        for (EnvironmentType s : EnumSet.allOf(EnvironmentType.class))
            LOOKUP.put(s.getValue(), s);
    }

    @Getter
    private int value;

    EnvironmentType(int value) {
        this.value = value;
    }

    // This method can be used for reverse lookup purpose
    public static EnvironmentType get(Integer value) {
        if (LOOKUP.containsKey(value)) {
            return LOOKUP.get(value);
        } else {
            throw new IllegalArgumentException("Invalid EnvironmentType of " + value + " provided");
        }
    }

}
