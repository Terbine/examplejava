/**
 * @preserve Copyright (c) 2021 TERBINE as an unpublished
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
public enum SourceIndicator {
    CROWDSOURCE(1),
    ENTERPRISE(2);

    private static final Map<Integer, SourceIndicator> LOOKUP = new HashMap<>();

    // Populate the lookup table on loading time
    static {
        for (SourceIndicator s : EnumSet.allOf(SourceIndicator.class))
            LOOKUP.put(s.getValue(), s);
    }

    @Getter
    private int value;

    SourceIndicator(int value) {
        this.value = value;
    }

    // This method can be used for reverse lookup purpose
    public static SourceIndicator get(Integer value) {
        if (LOOKUP.containsKey(value)) {
            return LOOKUP.get(value);
        } else {
            throw new IllegalArgumentException("Invalid LegalType of " + value + " provided");
        }
    }
}
